package ems.service;

import ems.entity.Employer;
import ems.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
