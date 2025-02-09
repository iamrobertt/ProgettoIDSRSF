package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUserCheckStrategy implements CustomAuthenticateCheckStrategy<String, String> {

    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(String userEmail, String userPassword) {
        User user = this.userUtility.checkCredentials(userEmail, userPassword);
        if(user == null)
            return false;
        return !this.userUtility.isUserAuthenticated(user);
    }
}
