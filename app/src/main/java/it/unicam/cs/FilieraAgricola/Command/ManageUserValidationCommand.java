package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserValidationState;
import it.unicam.cs.FilieraAgricola.User.UserState;

import java.util.List;

public class ManageUserValidationCommand extends Command<UserValidationState>{

    private final User userValidator;
    private final UserRepository userRepository;

    public ManageUserValidationCommand(User userToValidate, UserValidationState userValidationState, User userValidator, UserRepository userRepository) {
        super(userToValidate, userValidationState);
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        return List.of(UserRole.VALIDATOR);
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.userValidator.getUserRole());
    }

    @Override
    public void execute() {
        if (this.item.equals(UserValidationState.ACCEPTED))
            this.userRepository.updateUserState(UserState.VALIDATED, this.user.getUserID());

    }

}
