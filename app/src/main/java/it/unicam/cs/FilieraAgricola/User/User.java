package it.unicam.cs.FilieraAgricola.User;

public class User {

    private int userID;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
    private int companyVATNumber;
    private UserRole userRole;
    private UserState userState;

    public User(
            int userID,
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

    public int getUserID() { return userID;}
    public String getUserName() { return userName;}
    public String getUserSurname() { return userSurname;}
    public String getUserEmail() { return userEmail;}
    public String getUserPassword() { return userPassword;}
    public int getCompanyVATNumber() { return companyVATNumber;}
    public UserRole getUserRole() { return userRole;}
    public UserState getUserState() { return userState;}

    public void setUserID(int userID) { this.userID = userID;}
    public void setUserName(String userName) { this.userName = userName;}
    public void setUserSurname(String userSurname) { this.userSurname = userSurname;}
    public void setUserEmail(String userEmail) { this.userEmail = userEmail;}
    public void setUserPassword(String userPassword) { this.userPassword = userPassword;}
    public void setCompanyVATNumber(int companyVATNumber) { this.companyVATNumber = companyVATNumber;}
    public void setUserRole(UserRole userRole) { this.userRole = userRole;}
    public void setUserState(UserState userState) { this.userState = userState;}
}
