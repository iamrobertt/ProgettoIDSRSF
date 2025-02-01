package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class RoleRequestCommand extends Command <User>{

    private UserRole userRole;

    public RoleRequestCommand(User user, User newUser, UserRole userRole) {
        super(user, newUser);

        this.userRole = userRole;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(userRole);
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
