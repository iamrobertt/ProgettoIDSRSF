package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUserCommand extends Command <User>{


    public RegisterUserCommand(User user, User newUser) {
        super(user, newUser);
    }


    @Override
    public List<UserRole> getNeededAuthorization() {
        return new ArrayList<>(Arrays.stream(UserRole.values()).toList());
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        // TODO: aggiungi logica
    }

}
