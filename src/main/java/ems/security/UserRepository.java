package ems.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Repository for working with the User entity. This interface provides methods for performing database
//operations related to users.
//It inherits standard methods from JpaRepository, such as saving, deleting and searching for users.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); //Finds a user by username.
}


