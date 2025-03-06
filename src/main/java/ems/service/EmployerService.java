package ems.service;

import ems.entity.Employer;
import ems.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
// Service class for managing Employer entities
public class EmployerService {
    // Repository for interaction with employee data

    @Autowired
    private  EmployerRepository employerRepository;
    public List<Employer> findAll(){ return employerRepository.findAll();}  // Get a list of all workers

    public Employer save(Employer employer){return employerRepository.save(employer);} // Save new or update existing employee
    public void deleteById(Integer id){
        employerRepository.deleteById(id);
    }; // Delete employee by ID
    public Employer findById(Integer id){
        return employerRepository.findById(id).get();
    } // Find employee by ID . It is assumed that the worker exists
   public List<Employer> filterByName(String name) {
       return employerRepository.findByNameContainingIgnoreCase(name);
   } // Filter employees by name (case insensitive)
    public List<Employer> filterByStatus(String status) {
        return employerRepository.findByStatusContainingIgnoreCase(status);
    } // Filter workers by status (case insensitive)
    public List<Employer> filterByDepartment(String department) {
        return employerRepository.findByDepartmentContainingIgnoreCase(department);
    }// Filter employees by department (case insensitive)
    public List<Employer> filterByManagerId(Integer manager) {
        return employerRepository.findByManagerId(manager);
    } // Filter employees by manager ID
    public List<Employer> filterByManagerName(String managerName) {
        return employerRepository.findByManagerNameContainingIgnoreCase(managerName);
    } // Filter employees by manager name (case insensitive)


    //below are the methods created for testing the application using RestController and Postman
    private List<Employer> employers = new ArrayList<>();

    public Employer createEmployer(Employer employer) {
        employers.add(employer);
        return employer;
    }
  public Employer updateEmployee(Integer id, Employer updatedEmployee) {
        Optional<Employer> existingEmployeeOpt = employerRepository.findById(id);
        if (existingEmployeeOpt.isPresent()) {
            Employer existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setStatus(updatedEmployee.getStatus());
            return employerRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

    public boolean deleteEmployee(Integer id) {
        if (employerRepository.existsById(id)) {
            employerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
