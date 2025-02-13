package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
import it.unicam.cs.FilieraAgricola.User.UserState;

import java.util.List;

public class ManageUserValidationCommand extends Command<UserValidationState>{


    private final UserRepository userRepository;

    public ManageUserValidationCommand(User user, UserValidationState userValidationState, UserRepository userRepository) {
        super(user, userValidationState);
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
        if (this.item.equals(UserValidationState.ACCEPTED))
            this.userRepository.updateUserState(UserState.VALIDATED, this.user.getUserID());

    }

}
