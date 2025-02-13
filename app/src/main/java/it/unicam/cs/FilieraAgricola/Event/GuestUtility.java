package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestUtility {

    @Autowired
    private UserRepository userRepository;

    public  boolean checkExistParticipants (User user,Event event) {
        if(user == null || event == null)
            return false;

        return this.userRepository.findById(user.getUserID()).isPresent();
    }
}
