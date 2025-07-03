package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleRequestCheckStrategy  implements CheckStrategy<UserRole>{

    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(User user, UserRole newUserRole) {
        return this.userUtility.checkUserInfo(user) &&
                this.userUtility.checkExistUser(user) &&
                this.userUtility.checkUserIsValidated(user) &&
                this.userUtility.checkExistRole(newUserRole) &&
                !this.userUtility.checkIsSameRole(user, newUserRole) &&
                !this.userUtility.checkExistRoleRequest(user);
    }

}
