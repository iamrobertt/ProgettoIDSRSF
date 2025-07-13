package it.unicam.cs.FilieraAgricola.User;


import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Repository.RoleRequestRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserUtility {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRequestRepository roleRequestRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public User getUser(String email) {
        return this.userRepository.findByUserEmail(email);
    }

    public boolean checkUserInfo(User user) {
        return user != null && user.getUserID() != 0;
    }

    public boolean checkUserInfoForLoading(User user) {
        return user != null;
    }

    public boolean checkExistUser(User user) {
        Optional<User> userToSearch = this.userRepository.findById(user.getUserID());
        return userToSearch.isPresent();
    }

    public boolean verifyUser(User user, String userPassword) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), userPassword));
        return authentication.isAuthenticated();
    }

    public boolean checkExistRole(UserRole role) {
        for (UserRole existingRole : UserRole.values()) {
            if (existingRole == role)
                return true;
        }
        return false;
    }

    public boolean checkExistRoleRequest(User user) {
        Optional<RoleRequest> requestToSearch = this.roleRequestRepository.findById(user.getUserID());
        return requestToSearch.isPresent();
    }

    public boolean checkUserIsValidated(User user) {
        return user.getUserState().equals(UserState.VALIDATED);
    }

    public boolean checkIsSameRole(User user, UserRole role) {
        return user.getUserRole().equals(role);
    }


}
