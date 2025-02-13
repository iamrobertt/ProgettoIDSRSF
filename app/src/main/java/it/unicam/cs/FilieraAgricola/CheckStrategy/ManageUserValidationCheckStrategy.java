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
    public boolean validate(User user, UserValidationState userValidationState) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                user.getUserState().equals(UserState.WAITING_FOR_VALIDATION) &&
                (userValidationState.equals(UserValidationState.ACCEPTED) ||
                        userValidationState.equals(UserValidationState.DENIED));
    }

}
