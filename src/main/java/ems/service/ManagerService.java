package ems.service;

import ems.entity.Employer;
import ems.entity.Manager;
import ems.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for managing Manager entities
@Service
public class ManagerService {
    // Repository for interaction with managers' data
    @Autowired
    private ManagerRepository managerRepository;
    // Get a list of all managers
    public List<Manager> findAll(){ return managerRepository.findAll();}

    public Manager save(Manager manager){return managerRepository.save(manager);} // Save new or update existing manager
    public void deleteById(Integer id){
        managerRepository.deleteById(id);
    }; // Delete manager by id

    public Manager findById(Integer id){
        return managerRepository.findById(id).get();
    } // Find manager by id. Manager is assumed to exist
}
