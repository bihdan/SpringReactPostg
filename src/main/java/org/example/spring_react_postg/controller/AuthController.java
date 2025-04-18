package org.example.spring_react_postg.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.validation.Valid;
import java.util.stream.Collectors;



import org.example.spring_react_postg.model.ERole;
import org.example.spring_react_postg.model.Role;
import org.example.spring_react_postg.model.User;
import org.example.spring_react_postg.payload.request.LoginRequest;
import org.example.spring_react_postg.payload.request.SignupRequest;
import org.example.spring_react_postg.payload.response.JwtResponse;
import org.example.spring_react_postg.payload.response.MessageResponse;
import org.example.spring_react_postg.repository.RoleRepository;
import org.example.spring_react_postg.repository.UserRepository;
import org.example.spring_react_postg.security.jwt.JwtUtils;
import org.example.spring_react_postg.security.service.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Контролер автентифікації та реєстрації користувачів.
 * Обробляє запити на вхід до системи та створення нового користувача.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /** Менеджер аутентифікації Spring Security. */
    @Autowired
    AuthenticationManager authenticationManager;

    /** Репозиторій для доступу до користувачів. */
    @Autowired
    UserRepository userRepository;

    /** Репозиторій для доступу до ролей. */
    @Autowired
    RoleRepository roleRepository;

    /** Компонент для хешування паролів. */
    @Autowired
    PasswordEncoder encoder;

    /** Утиліта для створення JWT-токенів. */
    @Autowired
    JwtUtils jwtUtils;

    /**
     * Аутентифікація користувача.
     *
     * @param loginRequest об'єкт із полями логіну та пароля
     * @return JWT токен та інформація про користувача, якщо автентифікація пройшла успішно
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Реєстрація нового користувача.
     *
     * @param signUpRequest об'єкт із полями імені користувача, email, пароля та списком ролей
     * @return повідомлення про успішну реєстрацію або помилку, якщо користувач уже існує
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Створення нового облікового запису користувача
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRole(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
