package ems.controller;

import ems.entity.Employer;
import ems.entity.Manager;
import ems.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


// Controller for managing actions related to managers (Manager)
@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/") // Display the main page
    public String index(){
        return "index";
    }


    @GetMapping(value = "/managerList")// Получить список всех менеджеров и отобразить их на странице
    public String findAll(Model model){
        model.addAttribute("managers", managerService.findAll());
        return "managerList";
    }

    @GetMapping(value ="/createManager") // Display the form for creating a new manager
    public String createManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "createManager";
    }

    @PostMapping("/createManager") // Handle the creation of a new manager
    public String createManager(@RequestParam("file") MultipartFile file,
                @ModelAttribute("newEmployer") Manager manager) {
            String fileName = file.getOriginalFilename(); // Get the name of the file being downloaded
            if (!fileName.equals("")){
                File uploadedDirectory = new File("src/main/resources/static/img/");
                try {
                    // Save the file to the specified directory
                    file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                    manager.setLink("/img/" + fileName);
// Set a link to the uploaded image
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                manager.setLink("/image/not-found.jpg");
            }
            managerService.save(manager);
        return "redirect:/managerList";
    }
}
