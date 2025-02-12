package it.unicam.cs.FilieraAgricola.Repository;


import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);

    @Query("SELECT u FROM User u WHERE u.userEmail = ?1 AND u.userPassword = ?2")
    Optional<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);

    @Transactional
    @Modifying
    @Query("Update User u SET u.userState = ?1 WHERE u.userEmail = ?2")
    void updateUserState(UserState userState, String userEmail);

    @Transactional
    @Modifying
    @Query("Update User u SET u.userRole = ?1 WHERE u.userEmail = ?2")
    void updateUserRole(UserRole userRole, String userEmail);

}
