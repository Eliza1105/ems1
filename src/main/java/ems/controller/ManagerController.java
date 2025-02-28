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

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }


    @GetMapping(value = "/managerList")
    public String findAll(Model model){
        model.addAttribute("managers", managerService.findAll());
        return "managerList";
    }

    @GetMapping(value ="/createManager")
    public String createManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "createManager";
    }

    @PostMapping("/createManager")
    public String createManager(@RequestParam("file") MultipartFile file,
                @ModelAttribute("newEmployer") Manager manager) {
            String fileName = file.getOriginalFilename();
            if (!fileName.equals("")){
                File uploadedDirectory = new File("src/main/resources/static/img/");
                try {
                    file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                    manager.setLink("/img/" + fileName);

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
