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

        if (user == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkUserInfo(user))
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkExistUser(user))
            throw new IllegalArgumentException("User does not exist.");

        if (!this.userUtility.checkUserIsValidated(user))
            throw new IllegalArgumentException("User is not validated.");

        if (!this.userUtility.verifyUser(user, userPassword))
            throw new IllegalArgumentException("User credentials are incorrect.");

        return true;
    }
}
