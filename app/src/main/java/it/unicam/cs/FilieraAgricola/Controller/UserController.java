package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManager userManager;


    @PostMapping("/insertUser")
    public void insertUser(@RequestBody UserDTO userDTO) {

        ControllerUtility controllerUtility = new ControllerUtility();
        User user = controllerUtility.convertToUser(userDTO);

        this.userManager.registerUserRequest(user, user);
    }

    @PostMapping("/authenticate")
    public void authenticateUser(@RequestParam String email,
                                 @RequestParam String password
    ) {
        this.userManager.authenticateUserRequest(email, password);
    }


}
