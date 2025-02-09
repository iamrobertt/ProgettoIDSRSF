package it.unicam.cs.FilieraAgricola.User;


import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUtility {

    @Autowired
    private UserRepository userRepository;

    public static boolean checkUserInfo(User user) {
        return false;
    }

    public static boolean checkExistUser(User user) {

        return false;
    }

    public User checkCredentials(String email, String password) {
        return this.userRepository.findByUserEmailAndUserPassword(email, password).orElse(null);
    }

    public boolean isUserAuthenticated(User user) {
        return user.getUserState().equals(UserState.AUTHENTICATED);
    }

    public static boolean checkExistRole(UserRole role) { return false;}




}
