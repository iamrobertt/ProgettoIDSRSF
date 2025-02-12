package it.unicam.cs.FilieraAgricola.User;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Table(name = "\"user\"")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "company_vat_number")
    private String companyVATNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state")
    private UserState userState;

    //@OneToMany(mappedBy = "user")
    //private List<Product> products;


    public User (){}

    public User(
            long userID,
            String userName,
            String userSurname,
            String userEmail,
            String userPassword,
            String companyVATNumber,
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
