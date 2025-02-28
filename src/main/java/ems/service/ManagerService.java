package ems.service;

import ems.entity.Employer;
import ems.entity.Manager;
import ems.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    public List<Manager> findAll(){ return managerRepository.findAll();}

    public Manager save(Manager manager){return managerRepository.save(manager);}

    public void deleteById(Integer id){
        managerRepository.deleteById(id);
    };

    public Manager findById(Integer id){
        return managerRepository.findById(id).get();
    }
}
