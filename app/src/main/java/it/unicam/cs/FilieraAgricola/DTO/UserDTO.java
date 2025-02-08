package it.unicam.cs.FilieraAgricola.DTO;

import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import lombok.Data;
import lombok.Generated;

import java.util.List;

//TODO da rivedere come fare
@Data
public class UserDTO {

    private long userID;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
    private int companyVATNumber;
    //TODO verifica questo come convertire
    private List<UserRole> userRole;
    private UserState userState;
}
