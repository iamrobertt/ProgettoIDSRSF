package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;

public class RegisterUserCheckStrategy implements CheckStrategy {

    private final User user;

    public RegisterUserCheckStrategy(User user) {
        this.user = user;
    }

    @Override
    public boolean validate() {
        return UserUtility.checkUserInfo(user) && !UserUtility.checkExistUser(user);
    }

}
