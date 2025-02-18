package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCheckStrategy implements CheckStrategy<User> {

    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User user, User user1) {

        if(user1 == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkUserInfoForLoading(user1))
            throw new IllegalArgumentException("Error retrieving user information.");

        if (this.userUtility.checkExistUser(user1))
            throw new IllegalArgumentException("User already exists.");

        return true;
    }

}
