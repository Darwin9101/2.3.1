package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users") // Указываем общий путь для контроллера
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // Убедитесь, что этот шаблон существует
    }

    @PostMapping
    public String createUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.insertUser(name, email, password);
        return "redirect:/users"; // Перенаправление после успешного создания
    }

    @PostMapping("/update") // Маппинг для обновления пользователя
    public String updateUser(@RequestParam String name, @RequestParam String email,
                             @RequestParam String password, @RequestParam String nameOriginal) {
        userService.updateUser(name, email, password, nameOriginal);
        return "redirect:/users"; // Перенаправление после успешного обновления
    }

    @PostMapping("/delete") // Маппинг для удаления пользователя
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return "redirect:/users"; // Перенаправление после успешного удаления
    }
}