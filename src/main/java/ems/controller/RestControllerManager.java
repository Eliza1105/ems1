package ems.controller;

import ems.entity.Manager;
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
public class RestControllerManager {
    @Autowired
    private ManagerService managerService;
    @GetMapping(value = "/managerList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Manager> findAll() {
        return managerService.findAll();
    }

    @PostMapping("/createManager")
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        Manager createdManager = managerService.save(manager);
        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    @DeleteMapping("/managerDelete/{id}")
    public ResponseEntity<Map<String, String>> deleteManager(@PathVariable Integer id) {
        boolean isDeleted = managerService.deleteManager(id);
        Map<String, String> response = new HashMap<>();

        if (isDeleted) {
            response.put("message", "Менеджер успешно удалён");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Менеджер не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
