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

/**
 * Контролер для керування користувачами.
 * Обробляє запити, пов’язані зі створенням, отриманням і автентифікацією користувачів.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Конструктор контролера, що приймає сервіс користувачів.
     *
     * @param userService сервіс для обробки логіки користувачів
     */
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * Отримує список усіх користувачів.
     *
     * @return список користувачів у тілі відповіді
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Повертає користувача за його ідентифікатором.
     *
     * @param id унікальний ідентифікатор користувача
     * @return обгортка Optional з користувачем або порожня, якщо не знайдено
     */
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Реєструє нового користувача.
     *
     * @param user об’єкт користувача з даними
     * @return повідомлення про створення користувача
     */
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userService.saveUser(user);
        return "User created";
    }

    /**
     * Створює нового користувача з перевіркою наявності пароля.
     *
     * @param user об’єкт користувача з даними
     * @return відповідь зі статусом створення або помилки
     */
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must not be empty");
            }
            userService.saveUser(user);
            return ResponseEntity.ok("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    /**
     * Перевіряє правильність пароля користувача.
     *
     * @param request запит з ім’ям користувача та паролем
     * @return повідомлення про результат перевірки
     */
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
