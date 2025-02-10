package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticateUserCommand  extends Command <User>{

    private final UserRepository userRepository;

    public AuthenticateUserCommand(User user, User userToAuthenticate, UserRepository userRepository) {
        super(user, userToAuthenticate);
        this.userRepository =  userRepository;
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
        this.userRepository.updateUserState(UserState.AUTHENTICATED, this.item.getUserEmail());
    }


}
