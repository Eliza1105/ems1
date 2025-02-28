package ems.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping("/userList")
    @PreAuthorize("hasRole('ADMIN')")
    public String userList(Model model) {
        List<User> users= userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id); //
        return "redirect:/userList";
    }

    @GetMapping(value ="/user_save/{id}")
    public String updateUser(@PathVariable Long id, User user, Model model) {
        model.addAttribute("user", userService.findById(id));
        System.out.println(user.toString());
        return "user_save";
    }
    @PostMapping("/user_save")
    public String updateUser(@ModelAttribute User user, Model model) {
        System.out.println(user.toString());
        User userDb = userService.findById(user.getId());
        System.out.println(userDb.toString());

        userDb.setUsername(user.getUsername());
        userDb.setRoles(user.getRoles());

        userService.save(userDb);
        return "redirect:/userList";
    }

}
