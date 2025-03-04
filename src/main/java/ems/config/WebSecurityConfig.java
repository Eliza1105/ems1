package ems.config;

import ems.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



// Web application security configuration
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enabling annotations for method-level access control
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserService userService; // Service for working with users

        @Override // Setting up URL access rules
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/","/registration").permitAll() // Allow access to the main page and registration page
                    .and() .headers().frameOptions().sameOrigin()// Allow same-origin frames to be displayed
                    .and()
                    .authorizeRequests()
                    .antMatchers( "/", "/login", "/img/**").permitAll() // Allow access to login pages and images
                    .antMatchers("/admin/**","/userList","/userEdit", "/delete_user/{id}", "/createEmployer", "/createManager", "/employerSave", "/employerSave/{id}").hasRole("ADMIN") // Access for administrators
                    .antMatchers("/user/**", "/employerList", "/managerList").hasAnyRole("USER", "ADMIN") // Access for users and administrators
                    .antMatchers("/registration").not().fullyAuthenticated() // Deny registration access for authenticated users
                    .anyRequest().authenticated() // All other requests require authentication
                    .and()
                    .formLogin()
                    .loginPage("/login") // Specify login page
                    .loginProcessingUrl("/login")// Specify URL for login processing
                    .defaultSuccessUrl("/") // Redirect after successful login
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                   .logoutSuccessUrl("/");// Redirect after exit

        }
      @Override
    // Setting up the authentication manager
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    // Installing the user service and password encoder
        }



    @Bean // Bin for password encoder
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } // Using BCrypt to Encrypt Passwords

    @Bean // Bin for user service
    public UserDetailsService userDetailsService(){
        return new UserService();
    } // Return the UserService instance

}