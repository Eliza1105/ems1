package ems.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controller for managing users in the system.
//This class handles HTTP requests related to users, including retrieving a list of users,
//deleting users, and updating user information. The controller uses the service layer to perform
//business logic.
@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping("/userList") //Method for displaying a list of users.
    public String userList(Model model) {
        List<User> users= userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/delete_user/{id}")//Method for deleting a user by their ID.
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/userList";
    }

    @GetMapping(value ="/user_save/{id}")//Method for displaying the user update form.
    public String updateUser(@PathVariable Long id, User user, Model model) {
        model.addAttribute("user", userService.findById(id));
        System.out.println(user.toString());
        return "user_save";
    }
    @PostMapping("/user_save") //Method for handling user information updates.
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


