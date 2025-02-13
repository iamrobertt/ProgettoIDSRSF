package it.unicam.cs.FilieraAgricola.User;


import it.unicam.cs.FilieraAgricola.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class UserUtility {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public User getUser(String email) {
        return this.userRepository.findByUserEmail(email);
    }

    public  boolean checkUserInfo(User user) {
        return false;
    }

    public static boolean checkExistUser(User user) {

        return false;
    }

    public boolean verifyUser(User user, String userPassword) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), userPassword));

        return authentication.isAuthenticated();

    }

    public static boolean checkExistRole(UserRole role) { return false;}




}
