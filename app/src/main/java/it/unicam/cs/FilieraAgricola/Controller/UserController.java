package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;

import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public void authenticateUser(@RequestParam String email,@RequestParam String password) {
        this.userManager.authenticateUserRequest(email, password);
    }

    @PostMapping("/requestRole")
    public void requestNewRole(@RequestParam String role) {

        UserRole newUserRole = UserRole.valueOf(role);
        //this.userManager.newRoleRequest(user, newUserRole);
    }


}
