package ems.repository;

import ems.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for working with the Manager entity
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
