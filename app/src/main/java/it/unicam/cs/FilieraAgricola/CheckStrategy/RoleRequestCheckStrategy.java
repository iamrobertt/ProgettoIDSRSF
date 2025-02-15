package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleRequestCheckStrategy  implements CheckStrategy<UserRole>{

    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(User user, UserRole userRole) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                user.getUserState().equals(UserState.VALIDATED) &&
                this.userUtility.checkExistRole(userRole) &&
                !user.getUserRole().equals(userRole) &&
                !this.userUtility.checkExistRoleRequest(user);
    }

}
