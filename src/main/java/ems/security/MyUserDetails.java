package ems.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// A class implementing the UserDetails interface to represent user data
public class MyUserDetails implements UserDetails {
    private User user; // User object containing information about the user


    // Constructor that accepts a User object
    public MyUserDetails(User user) {
        this.user = user;
    }
    @Override
// Method for getting user access rights
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(); // List of access rights
        authorities.add(new SimpleGrantedAuthority(user.getRoles().name())); // Adding a user role
        System.out.println(authorities); // Output access rights to console (for debugging)
        return authorities;
    }
    @Override
// Method to indicate if an account has expired
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // Method to indicate whether the account is blocked
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
// Method to indicate whether the credentials have expired
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // Method to indicate whether an account is enabled
    public boolean isEnabled() {
        return true;
    }

    @Override // Method for getting user password
    public String getPassword() {
        return user.getPassword();
    }

    @Override // Method for getting user name
    public String getUsername() {
        return user.getUsername();
    }

}
