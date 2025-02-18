package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUserRequestRoleCheckStrategy implements CheckStrategy<UserValidationState> {
    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User userToUpdate, UserValidationState userValidationState ) {

        if(userToUpdate == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkUserInfo(userToUpdate))
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkExistUser(userToUpdate))
            throw new IllegalArgumentException("User does not exist.");

        if (!this.userUtility.checkUserIsValidated(userToUpdate))
            throw new IllegalArgumentException("User is not validated.");

        if (!this.userUtility.checkExistRoleRequest(userToUpdate))
            throw new IllegalArgumentException("Role request not found.");

        if(!userValidationState.equals(UserValidationState.ACCEPTED) &&
                !userValidationState.equals(UserValidationState.DENIED))
            throw new IllegalArgumentException("New validation state does not exist.");

        return true;
    }
}
