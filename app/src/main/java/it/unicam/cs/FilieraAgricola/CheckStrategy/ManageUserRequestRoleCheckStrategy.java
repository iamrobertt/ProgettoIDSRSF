package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUserRequestRoleCheckStrategy implements CheckStrategy<UserValidationState> {
    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User user, UserValidationState userValidationState ) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                user.getUserState().equals(UserState.VALIDATED) &&
                this.userUtility.checkExistRoleRequest(user) &&
                (userValidationState.equals(UserValidationState.ACCEPTED) ||
                        userValidationState.equals(UserValidationState.DENIED));
    }
}
