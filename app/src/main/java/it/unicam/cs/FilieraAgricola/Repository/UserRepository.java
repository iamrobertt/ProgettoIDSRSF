package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);

    @Transactional
    @Modifying
    @Query("Update User u SET u.userState = ?1 WHERE u.userID = ?2")
    void updateUserState(UserState userState, Long userID);

    @Transactional
    @Modifying
    @Query("Update User u SET u.userRole = ?1 WHERE u.userID = ?2")
    void updateUserRole(UserRole userRole, Long userID);

}
