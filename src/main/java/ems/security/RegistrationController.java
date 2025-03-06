package ems.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Access;
import java.util.Map;

// Controller for handling user registration and login
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/login")
// Method for displaying login page
    public String login(User user, Model model){
        model.addAttribute("user", new User());
        return "login";
    }

   @GetMapping("/registration")
// Method for displaying the registration page
   public String registr(User user) {
       return "registration";
   }
    @PostMapping("/registration") // Method for handling user registration
    public String registration(@ModelAttribute User user) {
        System.out.println("====="+user.toString()); // Output user information to console (for debugging)
        User userFromDb = userRepository.findByUsername(user.getUsername()); // Check if a user with this name exists

        //  user.setRoles(Role.ROLE_USER); // Set the user role
        user.setRoles(Role.ROLE_ADMIN); //Set admin role (if needed)
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // Encrypt password before saving
        System.out.println("====="+user.toString()); // Display user information after setting password
        userRepository.save(user);

        return "redirect:/login";
    }
}


