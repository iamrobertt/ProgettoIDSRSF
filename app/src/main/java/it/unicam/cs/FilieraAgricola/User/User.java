package it.unicam.cs.FilieraAgricola.User;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "user")
//@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private long userID;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
    private int companyVATNumber;
    private List<UserRole> userRole;
    private UserState userState;

    public User(
            long userID,
            String userName,
            String userSurname,
            String userEmail,
            String userPassword,
            int companyVATNumber,
            List <UserRole> userRole,
            UserState userState
    ) {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.companyVATNumber = companyVATNumber;
        this.userRole = userRole;
        this.userState = userState;
    }
}
