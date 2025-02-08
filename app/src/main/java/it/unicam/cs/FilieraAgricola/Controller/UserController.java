package it.unicam.cs.FilieraAgricola.Controller;

import it.unicam.cs.FilieraAgricola.DTO.UserDTO;
import it.unicam.cs.FilieraAgricola.Manager.UserManager;

import it.unicam.cs.FilieraAgricola.User.User;

import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManager userManager;


    @PostMapping("/insertUser")
    public String insertUser(@RequestBody UserDTO userDTO) {

        ControllerUtility controllerUtility = new ControllerUtility();
        User user1 = controllerUtility.convertToUser(userDTO);

        UserRole userRole = UserRole.SELLER;

        User user = new User(
                1,
                "ciao",
                "ciao",
                "ciao",
                "ciao",
                "123456",
                userRole,
                UserState.AUTHENTICATED
        );

        this.userManager.registerUserRequest(user, user1);
        return "caio";
    }


}
