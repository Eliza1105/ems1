package ems.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return new MyUserDetails(userRepository.findByUsername(username));
    }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User findById(Long id){return userRepository.findById(id).get();}

    public User save(User user){return userRepository.save(user);}
}
