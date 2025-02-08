package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.stereotype.Component;

@Component
public class RoleRequestCheckStrategy  implements CheckStrategy<UserRole>{

    @Override
    public boolean validate(User user, UserRole userRole) {
        return UserUtility.checkUserInfo(user) &&
                UserUtility.checkExistUser(user) &&
                UserUtility.checkExistRole(userRole);
    }

}
