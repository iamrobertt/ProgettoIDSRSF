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

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkUserInfo(user))
            throw new IllegalArgumentException("Error retrieving user information.");

        if (!this.userUtility.checkExistUser(user))
            throw new IllegalArgumentException("User does not exist.");

        if (!this.userUtility.checkUserIsValidated(user))
            throw new IllegalArgumentException("User is not validated.");

        if (!this.userUtility.checkExistRole(newUserRole))
            throw new IllegalArgumentException("New role not found.");

        if (this.userUtility.checkIsSameRole(user, newUserRole))
            throw new IllegalArgumentException("You already have this role.");

        if (this.userUtility.checkExistRoleRequest(user))
            throw new IllegalArgumentException("You already have this role request.");

        return true;
    }

}
