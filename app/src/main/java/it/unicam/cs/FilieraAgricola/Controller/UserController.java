package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.User.User;

import it.unicam.cs.FilieraAgricola.User.UserRole;
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
    private UserUtility userRepository;


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
        User user = this.userRepository.getUser(userEmail);
        System.out.println("User: " + userEmail);
        //this.userManager.newRoleRequest(newUserRole);
    }



}
