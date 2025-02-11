package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;

import java.util.List;

public class GuestUtility {

    public static boolean checkExistParticipants (User user,Event event) {
        if(user == null || event == null)
            return false;

        return true;
    }
}
