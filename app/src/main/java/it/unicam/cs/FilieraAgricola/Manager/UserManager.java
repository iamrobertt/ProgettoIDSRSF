package it.unicam.cs.FilieraAgricola.Manager;


import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.Exception.InsufficientUserAuthorizationException;
import it.unicam.cs.FilieraAgricola.JWT.JWTService;
import it.unicam.cs.FilieraAgricola.Repository.RoleRequestRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
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
    private ManageUserValidationCheckStrategy manageUserValidationCheckStrategy;
    @Autowired
    private ManageUserRequestRoleCheckStrategy manageUserRequestRoleCheckStrategy;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRequestRepository roleRequestRepository;

    public String authenticateUserRequest(User user, String userPassword) {

        if (!this.authenticateUserCheckStrategy.validate(user,userPassword))
            throw new IllegalArgumentException("User not valid");

        Command<User> authenticateUserCommand = new AuthenticateUserCommand(user, user, this.jwtService);

        if(!authenticateUserCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to authenticate user");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(authenticateUserCommand);
        invoker.invoke();
        AuthenticateUserCommand authenticateUserCommandCast = (AuthenticateUserCommand) authenticateUserCommand;
        return authenticateUserCommandCast.getJwtToken();
    }


    public void registerUserRequest(User user, User userToRegister) {

        if(!this.registerUserCheckStrategy.validate(user, userToRegister))
            throw new IllegalArgumentException("User not valid");

        Command<User> registerUserCommand = new RegisterUserCommand(user,userToRegister, this.userRepository);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(registerUserCommand);
        invoker.invoke();

    }


    public void newRoleRequest(User user, UserRole newRole) {

        if(!this.roleRequestCheckStrategy.validate(user, newRole))
            throw new IllegalArgumentException("Request not valid");

        Command<UserRole> roleRequestCommand = new RoleRequestCommand(user, newRole, this.roleRequestRepository);

        if(!roleRequestCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to ask for a new role");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(roleRequestCommand);
        invoker.invoke();
    }

    public void manageUserValidation(User userValidator ,User userToValidate, UserValidationState userValidationState) {

        if(!this.manageUserValidationCheckStrategy.validate(userToValidate, userValidationState))
            throw new IllegalArgumentException("Request not valid");

        Command<UserValidationState> manageUserValidationCommand = new ManageUserValidationCommand(userToValidate, userValidationState, userValidator, this.userRepository);

        if(!manageUserValidationCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to manage user validation");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(manageUserValidationCommand);
        invoker.invoke();
    }

    public void manageRequestRole(User userValidator, User userToUpdate, UserValidationState userValidationState) {

        if(!this.manageUserRequestRoleCheckStrategy.validate(userToUpdate, userValidationState))
            throw new IllegalArgumentException("Request not valid");

        Command<UserValidationState> manageRequestRoleCommand = new ManageRequestRoleCommand(userToUpdate, userValidationState, userValidator, this.userRepository, this.roleRequestRepository);

        if(!manageRequestRoleCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to manage role requests");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(manageRequestRoleCommand);
        invoker.invoke();
    }

}
