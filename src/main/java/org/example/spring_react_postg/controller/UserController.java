package org.example.spring_react_postg.controller;

import org.example.spring_react_postg.model.User;
import org.example.spring_react_postg.payload.request.LoginRequest;
import org.example.spring_react_postg.repository.UserRepository;
import org.example.spring_react_postg.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userService.saveUser(user);
        return "User created";
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must not be empty");
            }
            userService.saveUser(user);
            return ResponseEntity.ok("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }


    @PostMapping("/checkPassword")
    public String checkPassword(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                return "Пароль дійсний";
            } else {
                return "Неправильний пароль";
            }
        } else {
            return "Користувача не знайдено";
        }
    }


}



//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
//        if (userService.existsByUsername(userDTO.getUsername())) {
//            return ResponseEntity.badRequest().body("Username is already taken!");
//        }
//
//        if (userService.existsByEmail(userDTO.getEmail())) {
//            return ResponseEntity.badRequest().body("Email is already in use!");
//        }
//
//        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
//        userService.save(user);
//
//        String jwt = jwtUtils.generateJwtToken(user);
//        return ResponseEntity.ok(new JwtResponse(jwt));
//    }
//
//    @PostMapping("/users/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//        return ResponseEntity.ok(new JwtResponse(jwt));
//    }
