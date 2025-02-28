package ems.service;

import ems.entity.Employer;
import ems.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployerService {

    @Autowired
    private  EmployerRepository employerRepository;
    public List<Employer> findAll(){ return employerRepository.findAll();}

    public Employer save(Employer employer){return employerRepository.save(employer);}

    public void deleteById(Integer id){
        employerRepository.deleteById(id);
    };

    public Employer findById(Integer id){
        return employerRepository.findById(id).get();
    }
   public List<Employer> filterByName(String name) {
       return employerRepository.findByNameContainingIgnoreCase(name);
   }
    public List<Employer> filterByStatus(String status) {
        return employerRepository.findByStatusContainingIgnoreCase(status);
    }
    public List<Employer> filterByDepartment(String department) {
        return employerRepository.findByDepartmentContainingIgnoreCase(department);
    }
    public List<Employer> filterByManagerId(Integer manager) {
        return employerRepository.findByManagerId(manager);
    }
    public List<Employer> filterByManagerName(String managerName) {
        return employerRepository.findByManagerNameContainingIgnoreCase(managerName);
    }
}
