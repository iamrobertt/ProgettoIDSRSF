package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUserCheckStrategy implements CheckStrategy<String> {

    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(User user, String userPassword) {
        return this.userUtility.verifyUser(user, userPassword);
    }
}
