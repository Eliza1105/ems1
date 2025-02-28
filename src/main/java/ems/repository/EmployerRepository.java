package ems.repository;


import ems.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    List<Employer> findByNameContainingIgnoreCase(String name);
    List<Employer> findByStatusContainingIgnoreCase(String status);
    List<Employer> findByDepartmentContainingIgnoreCase(String department);
    List<Employer> findByManagerId(Integer manager);

    List<Employer> findByManagerNameContainingIgnoreCase(String managerName);

}
