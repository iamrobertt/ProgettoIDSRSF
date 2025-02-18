package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;

import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ControllerUtility controllerUtility;

    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(@RequestBody UserDTO userDTO) {

        User user = controllerUtility.convertToUser(userDTO);
        try {
            this.userManager.registerUserRequest(user, user);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("User registered successfully.");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam String email, @RequestParam String password) {
        User user = this.userUtility.getUser(email);
        String jwt;

        if (user == null)
            throw new IllegalArgumentException("User Not Found");

        try {
             jwt = this.userManager.authenticateUserRequest(user, password);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("User authenticated successfully:\n "+jwt);

    }

    @PostMapping("/requestRole")
    public ResponseEntity<String> requestNewRole(@RequestParam String role) {

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserRole newUserRole = UserRole.valueOf(role);
        User user = this.userUtility.getUser(userEmail);

        try {
            this.userManager.newRoleRequest(user, newUserRole);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("New Role Request created successfully.");
    }

    @PostMapping("/manageUserValidation")
    public ResponseEntity<String> manageUserValidation(@RequestParam long userID, @RequestParam String validationState) {

        UserValidationState userValidationState = UserValidationState.valueOf(validationState);

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userValidator = this.userUtility.getUser(userEmail);

        User userToValidate = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            this.userManager.manageUserValidation(userValidator, userToValidate, userValidationState);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("User validation status updated successfully.");
    }

    @PostMapping("/manageRequestRole")
    public ResponseEntity<String> manageRequestRole(@RequestParam long userID, @RequestParam String validationState) {

        UserValidationState userValidationState = UserValidationState.valueOf(validationState);
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userValidator = this.userUtility.getUser(userEmail);

        User userToUpdate = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            this.userManager.manageRequestRole(userValidator, userToUpdate, userValidationState);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("User role updated successfully.");
    }

}
