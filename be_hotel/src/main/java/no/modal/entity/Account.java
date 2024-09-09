package no.modal.entity;

import jakarta.persistence.*;
import lombok.Data;
import no.modal.entity.constants.AccountStatus;
import no.modal.entity.constants.Role;

import java.util.Date;

@Table(name = "`account`")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = false)
    private int phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;
}
