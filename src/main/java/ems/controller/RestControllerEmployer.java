package ems.controller;

import ems.entity.Employer;
import ems.service.EmployerService;
import ems.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestControllerEmployer {
    @Autowired
    private EmployerService employerService;
    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/employerList",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employer> findAll() {
        return employerService.findAll();
    }

    @PostMapping("/createEmployer")
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        Employer createdEmployer = employerService.save(employer);
        return new ResponseEntity<>(createdEmployer, HttpStatus.CREATED);
    }

    @PutMapping("/employerSave/{id}")
    public ResponseEntity<Employer> updateEmployee(@PathVariable Integer id, @RequestBody Employer updatedEmployee) {
        Employer employee = employerService.updateEmployee(id, updatedEmployee);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/employerDelete/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Integer id) {
        boolean isDeleted = employerService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();

        if (isDeleted) {
            response.put("message", "Работник успешно удалён");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Работник не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
