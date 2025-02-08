package it.unicam.cs.FilieraAgricola.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private long userID;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
    private int companyVATNumber;
    private String userRole;
    private String userState;
}
