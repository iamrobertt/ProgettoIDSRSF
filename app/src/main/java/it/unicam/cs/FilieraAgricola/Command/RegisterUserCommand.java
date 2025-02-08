package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUserCommand extends Command <User>{

    private final UserRepository userRepository;

    public RegisterUserCommand(User user, User newUser, UserRepository userRepository) {
        super(user, newUser);
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
        this.userRepository.save(this.item);
    }

}
