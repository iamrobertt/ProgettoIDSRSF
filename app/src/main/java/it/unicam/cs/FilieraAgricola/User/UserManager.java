package it.unicam.cs.FilieraAgricola.User;


import it.unicam.cs.FilieraAgricola.CheckStrategy.AuthenticateUserCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;


public class UserManager {

    private static final UserManager instance = null;

    private UserManager() {}

    public static UserManager getInstance() {
        if(instance == null) return new UserManager();

        return instance;
    }

    public void authenticateUserRequest(String userEmail, String password) {
        CheckStrategy<User> strategy = new AuthenticateUserCheckStrategy(userEmail,password);

        if(!strategy.validate())
            throw new IllegalArgumentException("User non valid");
    }

    public void requestUserRequest(User user) {}

    public void newRoleRequest(User use, UserRole newRole) {}


}
