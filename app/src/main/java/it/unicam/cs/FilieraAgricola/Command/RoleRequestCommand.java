package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.RoleRequestRepository;
import it.unicam.cs.FilieraAgricola.User.RoleRequest;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleRequestCommand extends Command <UserRole>{

    private final RoleRequestRepository roleRequestRepository;

    public RoleRequestCommand(User user, UserRole userRole, RoleRequestRepository roleRequestRepository) {
        super(user, userRole);
        this.roleRequestRepository = roleRequestRepository;
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
        RoleRequest roleRequest = new RoleRequest(user,this.item);
        this.roleRequestRepository.save(roleRequest);
    }
}
