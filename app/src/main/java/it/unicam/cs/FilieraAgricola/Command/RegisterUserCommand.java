package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserCommand extends Command <User>{


    public RegisterUserCommand(User user, User newUser) {
        super(user, newUser);
    }


    //todo rivedi
    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return this.user.getUserRole().contains(UserRole.SELLER);
    }

    @Override
    public void execute() {
        // TODO: aggiungi logica
    }

}
