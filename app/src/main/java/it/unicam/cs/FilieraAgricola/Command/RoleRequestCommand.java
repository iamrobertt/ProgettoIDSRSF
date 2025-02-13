package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleRequestCommand extends Command <UserRole>{

    private final UserRepository userRepository;


    public RoleRequestCommand(User user, UserRole userRole, UserRepository userRepository) {
        super(user, userRole);
        this.userRepository = userRepository;
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
        this.userRepository.updateUserRole(this.item,user.getUserID());
    }
}
