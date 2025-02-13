package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.JWT.JWTService;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AuthenticateUserCommand  extends Command <User>{



    private final JWTService jwtService;

    public AuthenticateUserCommand(User user, User userToAuthenticate, JWTService jwtService) {
        super(user, userToAuthenticate);

        this.jwtService = jwtService;
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
        String jwt = jwtService.generateToken(this.item);
        System.out.println("JWT: " + jwt);
    }


}
