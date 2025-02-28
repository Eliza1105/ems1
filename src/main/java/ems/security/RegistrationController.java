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

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/login")
    public String login(User user, Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        System.out.println("====="+user.toString());
        //  User userFromDb = userRepo.findByUsername(user.getUsername());

        user.setRoles(Role.USER);
        //user.setRoles(Role.ADMIN);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println("====="+user.toString());
        userRepository.save(user);

        return "redirect:/login";
    }
}
