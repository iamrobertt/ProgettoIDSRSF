package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRoleRequestValidationState;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageRequestRoleCheckStrategy implements CheckStrategy<UserRoleRequestValidationState> {

    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User user, UserRoleRequestValidationState userRoleRequestValidationState) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                user.getUserState().equals(UserState.WAITING_FOR_VALIDATION) &&
                (userRoleRequestValidationState.equals(UserRoleRequestValidationState.ACCEPTED) ||
                        userRoleRequestValidationState.equals(UserRoleRequestValidationState.DENIED));
    }

}
