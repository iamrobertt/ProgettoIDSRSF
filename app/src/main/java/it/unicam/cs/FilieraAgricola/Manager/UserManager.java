package it.unicam.cs.FilieraAgricola.Manager;


import it.unicam.cs.FilieraAgricola.CheckStrategy.AuthenticateUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RegisterUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RoleRequestCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.Command;
import it.unicam.cs.FilieraAgricola.Command.CommandInvoker;
import it.unicam.cs.FilieraAgricola.Command.RegisterUserCommand;
import it.unicam.cs.FilieraAgricola.Command.RoleRequestCommand;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import org.springframework.beans.factory.annotation.Autowired;


public class UserManager {

    @Autowired
    private AuthenticateUserCheckStrategy authenticateUserCheckStrategy;
    @Autowired
    private RegisterUserCheckStrategy registerUserCheckStrategy;
    @Autowired
    private RoleRequestCheckStrategy roleRequestCheckStrategy;


    public void authenticateUserRequest(User user, String userEmail, String userPassword) {

        if(!this.authenticateUserCheckStrategy.validate(user, userEmail, userPassword))
            throw new IllegalArgumentException("User non valid");

        // TODO: implementa strategia per l'accesso
    }


    //TODO RIVEDI
    public void requestUserRequest(User user, User userToRegister) {

        if(!this.registerUserCheckStrategy.validate(user, userToRegister))
            throw new IllegalArgumentException("User non valid");

        // TODO: rivedi
        Command<User> registerUserCommand = new RegisterUserCommand(user,null);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(registerUserCommand);
        invoker.invoke();

    }


    public void newRoleRequest(User user, UserRole newRole) {

        if(!this.roleRequestCheckStrategy.validate(user, newRole))
            throw new IllegalArgumentException("Request non valid");

        Command<User> roleRequestCommand = new RoleRequestCommand(user, null, newRole);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(roleRequestCommand);
        invoker.invoke();
    }


}
