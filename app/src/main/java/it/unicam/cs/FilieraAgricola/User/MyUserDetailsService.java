package it.unicam.cs.FilieraAgricola.User;

import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmail(userEmail);

        if (user == null)
            throw new UsernameNotFoundException(userEmail);

        return new UserPrincipal(user);
    }

}
