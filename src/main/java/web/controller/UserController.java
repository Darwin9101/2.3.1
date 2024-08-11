package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email,
                             @RequestParam String password) {
        User user = userService.getUserByID(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/users"; // Перенаправление после успешного обновления
    }

    @PostMapping("/delete") // Маппинг для удаления пользователя
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return "redirect:/users"; // Перенаправление после успешного удаления
    }
}