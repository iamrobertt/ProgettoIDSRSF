package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserRoleRequestValidationState;
import it.unicam.cs.FilieraAgricola.User.UserState;

import java.util.List;

public class ManageRoleRequestCommand extends Command<UserRoleRequestValidationState>{


    private final UserRepository userRepository;

    public ManageRoleRequestCommand(User user, UserRoleRequestValidationState userRoleRequestValidationState, UserRepository userRepository) {
        super(user, userRoleRequestValidationState);
        this.userRepository = userRepository;
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
        if (this.item.equals(UserRoleRequestValidationState.ACCEPTED))
            this.userRepository.updateUserState(UserState.VALIDATED, this.user.getUserID());

    }

}
