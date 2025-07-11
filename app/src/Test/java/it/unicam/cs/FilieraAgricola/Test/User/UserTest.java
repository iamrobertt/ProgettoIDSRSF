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

    public UserTest(
            long userID,
            String userName,
            String userSurname,
            String userEmail,
            String userPassword,
            String companyVatNumber,
            UserRole userRole,
            UserState userState
    ) {


    }

    @Test
    public void registerUser(){

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED),new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED) ));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(-1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED),new User(-1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED),new User(1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.VALIDATED),new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.VALIDATED)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION) ));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(-1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),new User(-1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),new User(1, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION)));

        assertThrows(IllegalArgumentException.class, () -> registerUserCheckStrategy.validate(new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION),new User(1L, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION)));
    }

    @Test
    public void authenticateUser(){
        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), "d"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "","","",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), "d"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), "e"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), ""));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "","e",UserRole.GENERIC_USER,UserState.VALIDATED), ""));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "","e",UserRole.GENERIC_USER,UserState.VALIDATED), "e"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), "d"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "","","",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), "d"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), "e"));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), ""));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), ""));

        assertThrows(IllegalStateException.class, () -> authenticateUserCheckStrategy.validate(new User(1l, "a","b","c",
                "","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), "e"));
    }

    @Test
    public void userRequestRole(){

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.VALIDATED), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.VALIDATED), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.VALIDATED), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.WAITING_FOR_VALIDATION), UserRole.GENERIC_USER));

        assertThrows(IllegalStateException.class, () -> roleRequestCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.WAITING_FOR_VALIDATION), UserRole.GENERIC_USER));
    }

    @Test
    public void manageUserRequestRole(){

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserRequestRoleCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));
    }

    @Test
    public void manageUserValidation(){

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.VALIDATED), UserValidationState.ACCEPTED ));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(1l, "","","",
                "","",UserRole.GENERIC_USER,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(-1l, "a","b","c",
                "d","e",null,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

        assertThrows(IllegalStateException.class, () -> manageUserValidationCheckStrategy.validate(new User(1l, "","","",
                "","",null,UserState.WAITING_FOR_VALIDATION), UserValidationState.DENIED));

    }

}
