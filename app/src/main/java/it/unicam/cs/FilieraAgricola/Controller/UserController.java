package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;

import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserUtility userUtility;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/insertUser")
    public void insertUser(@RequestBody UserDTO userDTO) {

        ControllerUtility controllerUtility = new ControllerUtility();
        User user = controllerUtility.convertToUser(userDTO);

        this.userManager.registerUserRequest(user, user);
    }

    @PostMapping("/authenticate")
    public void authenticate(@RequestParam String email, @RequestParam String password) {
        User user = this.userUtility.getUser(email);
        if (user == null)
            throw new IllegalArgumentException("User Not Found");
        this.userManager.authenticateUserRequest(user, password);
    }

    @PostMapping("/requestRole")
    public void requestNewRole(@RequestParam String role) {

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserRole newUserRole = UserRole.valueOf(role);
        User user = this.userUtility.getUser(userEmail);
        this.userManager.newRoleRequest(user, newUserRole);
    }

    @PostMapping("/manageUserValidation")
    public void manageUserValidation(@RequestParam long userID, @RequestParam String validationState) {

        UserValidationState userValidationState = UserValidationState.valueOf(validationState);

        User user = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        this.userManager.manageUserValidation(user, userValidationState);
    }

    @PostMapping("/manageRequestRole")
    public void manageRequestRole(@RequestParam long userID, @RequestParam String validationState, @RequestParam String newUserRole) {

        UserRole userRole = UserRole.valueOf(newUserRole);

        UserValidationState userValidationState = UserValidationState.valueOf(validationState);

        User user = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        this.userManager.manageRequestRole(user, userValidationState, userRole);
    }


}
