package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUserValidationCheckStrategy implements CheckStrategy<UserValidationState> {

    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User userToValidate, UserValidationState userValidationState) {

        if(userToValidate == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkUserInfo(userToValidate))
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkExistUser(userToValidate))
            throw new IllegalArgumentException("User does not exist.");

        if (!userToValidate.getUserState().equals(UserState.WAITING_FOR_VALIDATION))
            throw new IllegalArgumentException("User is already validated.");

        if(!userValidationState.equals(UserValidationState.ACCEPTED) &&
                !userValidationState.equals(UserValidationState.DENIED))
            throw new IllegalArgumentException("New validation state does not exist.");

        return true;

    }

}
