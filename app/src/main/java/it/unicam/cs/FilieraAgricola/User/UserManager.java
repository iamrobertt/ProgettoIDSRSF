package it.unicam.cs.FilieraAgricola.User;


import it.unicam.cs.FilieraAgricola.CheckStrategy.AuthenticateUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RegisterUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.RoleRequestCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.Command;
import it.unicam.cs.FilieraAgricola.Command.CommandInvoker;
import it.unicam.cs.FilieraAgricola.Command.RegisterUserCommand;
import it.unicam.cs.FilieraAgricola.Command.RoleRequestCommand;


public class UserManager {

    private static final UserManager instance = null;

    private UserManager() {}

    public static UserManager getInstance() {
        if(instance == null) return new UserManager();

        return instance;
    }

    public void authenticateUserRequest(String userEmail, String password) {
        CheckStrategy AuthenticateUserCheckStrategy = new AuthenticateUserCheckStrategy(userEmail,password);

        if(!AuthenticateUserCheckStrategy.validate())
            throw new IllegalArgumentException("User non valid");

        // TODO: implementa strategia per l'accesso
    }

    public void requestUserRequest(User user, UserRole userRole) {
        CheckStrategy registerUserCheckStrategy = new RegisterUserCheckStrategy(user);

        if(!registerUserCheckStrategy.validate())
            throw new IllegalArgumentException("User non valid");

        // TODO: ricontrolla
        Command<User> registerUserCommand = new RegisterUserCommand(user,null, userRole);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(registerUserCommand);
        invoker.invoke();

    }


    public void newRoleRequest(User user, UserRole newRole) {
        CheckStrategy roleRequestStrategy = new RoleRequestCheckStrategy(user, newRole);

        if(!roleRequestStrategy.validate())
            throw new IllegalArgumentException("Request non valid");

        Command<User> roleRequestCommand = new RoleRequestCommand(user, null, newRole);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(roleRequestCommand);
        invoker.invoke();
    }


}
