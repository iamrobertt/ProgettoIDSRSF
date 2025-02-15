package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCheckStrategy implements CheckStrategy<User> {

    @Autowired
    UserUtility userUtility;

    @Override
    public boolean validate(User user, User user1) {
        return !this.userUtility.checkUserInfo(user1) &&
                !this.userUtility.checkExistUser(user1);
    }

}
