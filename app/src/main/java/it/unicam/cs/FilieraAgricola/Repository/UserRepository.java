package it.unicam.cs.FilieraAgricola.Repository;


import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
