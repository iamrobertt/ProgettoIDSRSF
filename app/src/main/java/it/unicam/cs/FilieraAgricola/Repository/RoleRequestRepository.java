package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.User.RoleRequest;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRequestRepository extends JpaRepository<RoleRequest, Long> {

    @Transactional
    @Modifying
    @Query("DELETE RoleRequest rr WHERE rr.userID = ?1 AND rr.userRole = ?2")
    void removeRoleRequest(Long userID, UserRole role);

}
