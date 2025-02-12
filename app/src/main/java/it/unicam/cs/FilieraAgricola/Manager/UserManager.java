package it.unicam.cs.FilieraAgricola.Manager;


import it.unicam.cs.FilieraAgricola.CheckStrategy.AuthenticateUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RegisterUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RoleRequestCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.JWT.JWTService;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private AuthenticateUserCheckStrategy authenticateUserCheckStrategy;
    @Autowired
    private RegisterUserCheckStrategy registerUserCheckStrategy;
    @Autowired
    private RoleRequestCheckStrategy roleRequestCheckStrategy;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    public void authenticateUserRequest(User user, String userPassword) {


        if (!this.authenticateUserCheckStrategy.validate(user,userPassword))
            throw new UsernameNotFoundException("User not found or already authenticated");

        Command<User> authenticateUserCommand = new AuthenticateUserCommand(user, user, this.userRepository, this.jwtService);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(authenticateUserCommand);
        invoker.invoke();
    }


    public void registerUserRequest(User user, User userToRegister) {

        if(!this.registerUserCheckStrategy.validate(user, userToRegister))
            throw new IllegalArgumentException("User non valid");

        Command<User> registerUserCommand = new RegisterUserCommand(user,userToRegister, this.userRepository);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(registerUserCommand);
        invoker.invoke();

    }


    public void newRoleRequest(User user, UserRole newRole) {

        if(!this.roleRequestCheckStrategy.validate(user, newRole))
            throw new IllegalArgumentException("User not found or new role not available");

//        Command<User> roleRequestCommand = new RoleRequestCommand(user, newRole, this.userRepository);
//
//        CommandInvoker invoker = new CommandInvoker();
//
//        invoker.setCommand(roleRequestCommand);
//        invoker.invoke();
    }


}
