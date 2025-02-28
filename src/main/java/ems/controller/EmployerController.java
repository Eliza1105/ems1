package ems.controller;

import ems.entity.Employer;
import ems.entity.Manager;
import ems.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployerController {
    @Autowired
    private EmployerService employerService;

    @GetMapping(value = "/employerList")
    public String findAll(Model model){
        model.addAttribute("employers", employerService.findAll());
        return "employerList";
    }

    @GetMapping(value ="/createEmployer")
    public String createEmployer(Model model) {
        model.addAttribute("employer", new Employer());
        model.addAttribute("manager", new Manager());
        return "createEmployer";
    }

    @PostMapping("/createEmployer")
    public String createEmployer(@RequestParam("file") MultipartFile file,
                                 @ModelAttribute("newEmployer") Employer employer) {
        String fileName = file.getOriginalFilename();
        if (!fileName.equals("")){
            File uploadedDirectory = new File("src/main/resources/static/img/");
            try {
                file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                employer.setLink("/img/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            employer.setLink("/image/not-found.jpg");
        }

        employerService.save(employer);
        return "redirect:/employerList";
    }

    @GetMapping("/deleteEmployer/{id}")
    public String deleteEmployer(@PathVariable Integer id) {
        employerService.deleteById(id); //
        return "redirect:/employerList";
    }

    @GetMapping(value ="/employerSave/{id}")
    public String showUpdateForm(@PathVariable Integer id, Employer employer, Model model) {
        model.addAttribute("employer", employerService.findById(id));
        System.out.println(employer.toString());
        return "employerSave";
    }
    @PostMapping("/employerSave")
    public String updateFood(@ModelAttribute Employer employer,  @RequestParam("file") MultipartFile file,  Model model) {
        System.out.println(employer.toString());
        Employer employerDb = employerService.findById(employer.getId());
        System.out.println(employerDb.toString());
        String fileName = file.getOriginalFilename();
        if (!fileName.equals("")){
            File uploadedDirectory = new File("src/main/resources/static/img/");
            try {
                file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                employer.setLink("/img/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
        {
            employer.setLink("/image/not-found.jpg");
        }
        employerDb.setName(employer.getName());
        employerDb.setStatus(employer.getStatus());
        employerDb.setDepartment(employer.getDepartment());
      //  employerDb.setMarinades(employer.getMarinades());
        employerService.save(employerDb);
       // model.addAttribute("foods", marinadeService.findAll());
        return "redirect:/employerList";
    }
    @PostMapping("/filter")
    public String filterEmployersByName(@RequestParam String name, Model model) {
        List<Employer> employers = employerService.filterByName(name);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
    @PostMapping("/filter2")
    public String filterEmployersByStatus(@RequestParam String status, Model model) {
        List<Employer> employers = employerService.filterByStatus(status);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
    @PostMapping("/filter3")
    public String filterEmployersByDepartment(@RequestParam String department, Model model) {
        List<Employer> employers = employerService.filterByDepartment(department);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
    @PostMapping("/filter4")
    public String filterEmployersByManagerId(@RequestParam Integer manager, Model model) {
        List<Employer> employers = employerService.filterByManagerId(manager);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
    @PostMapping("/filter5")
    public String filterEmployersByManagerName(@RequestParam String managerName, Model model) {
        List<Employer> employers = employerService.filterByManagerName(managerName);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
    //добавить фильтры : по имени сотрудника- ok, статусу сотрудника - ok, имени менеджера и
    //названиям отделов
}
