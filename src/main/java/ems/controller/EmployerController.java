package ems.controller;

import ems.entity.Employer;
import ems.entity.Manager;
import ems.service.EmployerService;
import ems.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Controller for managing employee related actions (Employer)
@Controller
public class EmployerController {
    @Autowired
    private EmployerService employerService;
    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/employerList")
    public String findAll(Model model){
        model.addAttribute("employers", employerService.findAll());
        return "employerList";
    } // Get a list of all workers and display them on the page. Returns the name of the template to display

    @GetMapping(value ="/createEmployer")
    public String createEmployer(Model model) {
        model.addAttribute("employer", new Employer());
        model.addAttribute("manager", new Manager());
        return "createEmployer";
    }// Display the form to create a new employee

    @PostMapping("/createEmployer") // Process the creation of a new worker
    public String createEmployer(@RequestParam("file") MultipartFile file,
                                 @ModelAttribute("newEmployer") Employer employer) {
        String fileName = file.getOriginalFilename();
        if (!fileName.equals("")){
            File uploadedDirectory = new File("src/main/resources/static/img/");
            try {
                file.transferTo(new File(uploadedDirectory.getAbsolutePath() + "/" + fileName));
                employer.setLink("/img/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException(e); // Handling I/O errors
            }
        }
        else
        {
            employer.setLink("/image/not-found.jpg"); // Set default image link
        }

        employerService.save(employer);
        return "redirect:/employerList";
    }

    @GetMapping("/deleteEmployer/{id}") // Delete employee by ID
    public String deleteEmployer(@PathVariable Integer id) {
        employerService.deleteById(id);
        return "redirect:/employerList";
    }

    @GetMapping(value ="/employerSave/{id}") // Display a form to update employee information
    public String showUpdateForm(@PathVariable Integer id, Employer employer, Model model) {
        model.addAttribute("employer", employerService.findById(id));
        System.out.println(employer.toString());
        return "employerSave";
    }
    @PostMapping("/employerSave") // Process the employee information update
    public String updateFood(@ModelAttribute Employer employer,  @RequestParam("file") MultipartFile file,  Model model) {
        System.out.println(employer.toString());
        Employer employerDb = employerService.findById(employer.getId()); // Get an existing worker
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
        // Updating employee information
        employerDb.setName(employer.getName());
        employerDb.setStatus(employer.getStatus());
        employerDb.setDepartment(employer.getDepartment());
      employerDb.setManager(employer.getManager());
        employerService.save(employerDb);
       model.addAttribute("employers", managerService.findAll());
        return "redirect:/employerList";
    }
    @PostMapping("/filter") // Filter workers by name
    public String filterEmployersByName(@RequestParam String name, Model model) {
        List<Employer> employers = employerService.filterByName(name);
// Add filtered workers to the model
        model.addAttribute("employers", employers);
        return "employerList";
    }
    @PostMapping("/filter2") // Filter workers by status
    public String filterEmployersByStatus(@RequestParam String status, Model model) {
        List<Employer> employers = employerService.filterByStatus(status);
        model.addAttribute("employers", employers);
        return "employerList";
    }
    @PostMapping("/filter3") // Filter workers by department
    public String filterEmployersByDepartment(@RequestParam String department, Model model) {
        List<Employer> employers = employerService.filterByDepartment(department);
        model.addAttribute("employers", employers);
        return "employerList";
    }
    @PostMapping("/filter4") // Filter workers by manger ID
    public String filterEmployersByManagerId(@RequestParam Integer manager, Model model) {
        List<Employer> employers = employerService.filterByManagerId(manager);
        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }
  /*  @PostMapping("/filter5") // Filter workers by manager's name
    public String filterEmployersByManagerName(@RequestParam String managerName, Model model) {
        List<Employer> employers = employerService.filterByManagerName(managerName);

        model.addAttribute("employers", employers);
        return "employerList"; // Возвращаем имя шаблона
    }

   */
}
