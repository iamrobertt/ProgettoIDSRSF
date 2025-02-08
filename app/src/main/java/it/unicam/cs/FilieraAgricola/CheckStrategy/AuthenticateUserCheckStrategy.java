package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUserCheckStrategy implements CustomCheckStrategy<String, String> {


    @Override
    public boolean validate(User user , String userEmail, String userPassword) {
        // TODO : aggiungi getUser(),isAuthenticated()
        return UserUtility.checkCredentials(userEmail, userPassword);
    }
}
