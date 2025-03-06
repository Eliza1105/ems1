package ems.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//Service for managing users in the system. This class implements the UserDetailsService interface
// and provides methods for performing operations with users, such as loading a user by name,
// deleting, searching, and saving users. The service uses a repository to interact with the database.
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;

    @Override //Loads a user by username.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return new MyUserDetails(userRepository.findByUsername(username));
    }
    //Deletes a user by their ID.
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
//Finds a user by their ID.
    public User findById(Long id){return userRepository.findById(id).get();}

 //Saves or updates information about a user.
    public User save(User user){return userRepository.save(user);}
}


