package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.RoleRequestRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.*;

import java.util.List;
import java.util.Optional;

public class ManageRequestRoleCommand extends Command<UserValidationState>{

    private final UserRepository userRepository;
    private final RoleRequestRepository roleRequestRepository;

    public ManageRequestRoleCommand(User user, UserValidationState userValidationState, UserRepository userRepository, RoleRequestRepository roleRequestRepository) {
        super(user, userValidationState);
        this.userRepository = userRepository;
        this.roleRequestRepository = roleRequestRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        return List.of(UserRole.VALIDATOR);
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        if (this.item.equals(UserValidationState.ACCEPTED)) {
            Optional<RoleRequest> roleRequest = this.roleRequestRepository.findById(user.getUserID());
            this.userRepository.updateUserRole(roleRequest.get().getUserRole(), roleRequest.get().getUserID());
            this.roleRequestRepository.removeRoleRequest(roleRequest.get().getUserID(),roleRequest.get().getUserRole());
        }

    }
}
