package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUserRequestRoleCheckStrategy implements CustomCheckStrategy<UserValidationState, UserRole> {
    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User user, UserValidationState userValidationState, UserRole userRole) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                this.userUtility.checkExistRole(userRole) &&
                (userValidationState.equals(UserValidationState.ACCEPTED) ||
                        userValidationState.equals(UserValidationState.DENIED));
    }
}
