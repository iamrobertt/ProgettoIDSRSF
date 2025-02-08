package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleRequestCommand extends Command <User>{

    private UserRole userRole;

    public RoleRequestCommand(User user, User newUser, UserRole userRole) {
        super(user, newUser);

        this.userRole = userRole;
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
