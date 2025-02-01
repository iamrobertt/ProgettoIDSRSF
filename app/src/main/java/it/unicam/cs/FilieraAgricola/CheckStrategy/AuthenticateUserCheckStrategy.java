package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.UserUtility;
import it.unicam.cs.FilieraAgricola.User.User;

public class AuthenticateUserCheckStrategy implements CheckStrategy {

    private final String userEmail;
    private final String userPassword;


    public AuthenticateUserCheckStrategy(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Override
    public boolean validate() {
        // TODO : aggiungi getUser(),isAuthenticated()
        return UserUtility.checkCredentials(userEmail, userPassword);
    }
}
