package ems.repository;


import ems.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for working with the Employer entity
@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    List<Employer> findByNameContainingIgnoreCase(String name); // Find a list of employees by name (case insensitive)
    List<Employer> findByStatusContainingIgnoreCase(String status); // Find a list of employees by status (case insensitive)
    List<Employer> findByDepartmentContainingIgnoreCase(String department);  // Find a list of employees by department (case insensitive)
    List<Employer> findByManagerId(Integer manager);  // Find a list of employees by manager ID

    List<Employer> findByManagerNameContainingIgnoreCase(String managerName); // Find a list of employees by manager name (case insensitive)

}
