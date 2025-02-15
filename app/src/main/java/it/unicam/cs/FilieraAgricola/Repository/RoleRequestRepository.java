package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.User.RoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRequestRepository extends JpaRepository<RoleRequest, Long> {

}
