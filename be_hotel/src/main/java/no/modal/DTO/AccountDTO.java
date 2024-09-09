package no.modal.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String idCard;
    private String address;
    private String role;
    private String status;
    private Date createdDate;
    private Date modifiedDate;
}
