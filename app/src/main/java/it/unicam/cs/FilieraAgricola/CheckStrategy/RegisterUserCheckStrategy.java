package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCheckStrategy implements CheckStrategy<User> {


    @Override
    public boolean validate(User user, User user1) {
        return UserUtility.checkUserInfo(user) && !UserUtility.checkExistUser(user);
    }

}
