package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserUtility;

public class RoleRequestCheckStrategy  implements CheckStrategy{
    private final User user;
    private final UserRole userRole;

    public RoleRequestCheckStrategy(User user, UserRole userRole) {
        this.user = user;
        this.userRole = userRole;
    }

    @Override
    public boolean validate() {
        return UserUtility.checkUserInfo(user) &&
                UserUtility.checkExistUser(user) &&
                UserUtility.checkExistRole(userRole);
    }

}
