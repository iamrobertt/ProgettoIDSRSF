package it.unicam.cs.FilieraAgricola.Test.User;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.User.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest extends User {

    @Autowired
    private UserUtility userUtility;

    private RegisterUserCheckStrategy registerUserCheckStrategy;
    private AuthenticateUserCheckStrategy authenticateUserCheckStrategy;
    private RoleRequestCheckStrategy roleRequestCheckStrategy;
    private ManageUserValidationCheckStrategy manageUserValidationCheckStrategy;
    private ManageUserRequestRoleCheckStrategy manageUserRequestRoleCheckStrategy;
    private User user;



    @Test
    public void registerUserStrategyTest(){

        User user = new User(1,"Shaz","Khan","porva@prova.prova","juventus12345","1234567",UserRole.SELLER,UserState.VALIDATED);

        // mi da errore perchè l'utente è gia registrato
        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(user,user));

        // imposto id 0 per controllare che salti il controllo su user info
        user.setUserID(0);
        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(user,user));

        // mi aspetto un errore poichè il campo non può essere nullo
        user.setUserID(3);
        user.setUserEmail(null);
        user.setUserPassword(null);
        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(user,user));

        // mi aspetto che fallisca per user name vuoto
        user.setUserID(4);
        user.setUserState(UserState.WAITING_FOR_VALIDATION);
        user.setUserName(null);
        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(user,user));

    }

    @Test
    public void authenticateUser(){

        User user = new User (3,"Rober","Necula","porva@prova.prova1","juventus12345","1234567",UserRole.SELLER,UserState.VALIDATED);

        // mi aspetto un errore perchè l'utente è validato correttamente ma la password è errata
        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(user,"Juventus12345"));

        // mi aspetto che fallisca poichè la password è cprretta ma l'utente è in attesa di essere validato
        user.setUserState(UserState.WAITING_FOR_VALIDATION);
        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(user,"juventus12345"));

        // mi aspetto che fallisca poichè la password impostata è nulla
        user.setUserPassword(null);
        user.setUserState(UserState.VALIDATED);
        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(user,"juventus12345"));

        // mi aspetto che fallisca poichè l'utente non è registrato
        user.setUserID(4);
        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(user,"juventus12345"));

    }

    @Test
    public void userRequestRole(){

        User user = new User(17,"Shaz","Khan","porva1@prova.prova","$2a$12$PyPaZxIDC6eJCaFGElxSS.SwGQx2rpYq9XiKdjJu5t5eHuUtkuZbO","1234567",UserRole.CUSTOMER,UserState.VALIDATED);

        // mi aspetto un errore percè non è stato passato nessun utente
        assertThrows(IllegalArgumentException.class, () -> roleRequestCheckStrategy.validate(null,UserRole.CUSTOMER));

        // mi aspetto un errore perchè l'utente non è stato ancora convalidato
        user.setUserState(UserState.WAITING_FOR_VALIDATION);
        assertThrows(IllegalArgumentException.class, () -> roleRequestCheckStrategy.validate(user,UserRole.GENERIC_USER));

        // mi aspetto un errore perchè l'utente sta richiedendo come nuovo ruolo, lo stesso ruolo
        assertThrows(IllegalArgumentException.class, () -> roleRequestCheckStrategy.validate(user,UserRole.CUSTOMER));

        // mi aspetto un errore perchè l'utente ha un campo info nullo
        user.setUserSurname(null);
        assertThrows(IllegalArgumentException.class, () -> roleRequestCheckStrategy.validate(user,UserRole.CUSTOMER));

        // mi aspetto un essore poiche l'user id non esiste
        user.setUserID(0);
        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(user,UserRole.GENERIC_USER));

    }

    @Test
    public void manageUserRequestRole(){

        User user = new User (3,"Rober","Necula","porva@prova.prova1","juventus12345","1234567",UserRole.SELLER,UserState.VALIDATED);

        // mi aspetto un errore poichè l'utente è nullo
        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(null,UserValidationState.ACCEPTED));

        // mi aspetto un errore poichè lo stato di validazione non esiste
        user.setUserState(UserState.WAITING_FOR_VALIDATION);
        assertThrows(IllegalArgumentException.class, () -> manageUserRequestRoleCheckStrategy.validate(user,null));

        // mi aspetto un errore perchè le info dell'utente sono nulle
        user.setUserSurname(null);
        assertThrows(IllegalArgumentException.class, () -> manageUserRequestRoleCheckStrategy.validate(user,UserValidationState.DENIED));

        // mi aspetto un errore poichè l'user id non esiste
        user.setUserID(6);
        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(user,UserValidationState.ACCEPTED));

    }

    @Test
    public void manageUserValidation(){
        User user = new User (3,"Rober","Necula","porva@prova.prova1","juventus12345","1234567",UserRole.SELLER,UserState.VALIDATED);

        // mi aspetto un errore perchè l'utente è nullo
        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(null,UserValidationState.ACCEPTED));

        // mi aspetto un errore perchè lo stato di validazione non esiste
        assertThrows(IllegalArgumentException.class, () -> manageUserValidationCheckStrategy.validate(user,null));

        // mi aspetto un errore poichè il ruiolo dell'utente non esiste
        user.setUserRole(null);
        assertThrows(IllegalArgumentException.class, () -> manageUserValidationCheckStrategy.validate(user,UserValidationState.DENIED));

        // mi aspetto un errore poichè le info sono nulle
        user.setUserName(null);
        assertThrows(IllegalArgumentException.class, () -> manageUserValidationCheckStrategy.validate(user,null));

        // mi aspetto un errore perchè l'utente non esiste
        user.setUserID(5);
        assertThrows(IllegalArgumentException.class, () -> manageUserValidationCheckStrategy.validate(user,UserValidationState.ACCEPTED));

    }

}
