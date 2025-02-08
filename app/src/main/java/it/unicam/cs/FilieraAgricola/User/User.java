package it.unicam.cs.FilieraAgricola.User;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private long userID;

    @Column(name = "username")
    private String userName;

    @Column(name = "usersurname")
    private String userSurname;

    @Column(name = "useremail")
    private String userEmail;

    @Column(name = "userpassword")
    private String userPassword;

    @Column(name = "companyvatnumber")
    private int companyVATNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "userrole")
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "userstate")
    private UserState userState;


    public User (){}

    public User(
            long userID,
            String userName,
            String userSurname,
            String userEmail,
            String userPassword,
            int companyVATNumber,
            UserRole userRole,
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
