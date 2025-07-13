package it.unicam.cs.FilieraAgricola.User;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "role_request")
@Data
@Entity
public class RoleRequest {

    @Id
    @Column(name = "user_id")
    private long userID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    public RoleRequest(){}

    public RoleRequest(User user, UserRole userRole) {
        this.user = user;
        this.userRole = userRole;
        this.userID = user.getUserID();
    }

}
