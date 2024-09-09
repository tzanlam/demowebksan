package no.modal.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import no.modal.entity.Account;
import no.modal.entity.constants.AccountStatus;
import no.modal.entity.constants.Role;
import no.validation.Account.UniqueEmail;
import no.validation.Account.UniqueUsername;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static no.ultils.MethodConvertTime.convertDate;

@Data
public class AccountRequest {
    @NotNull(message = "username khong the de trong")
    @UniqueUsername()
    private String username;
    @NotNull
    private String password;
    private String fullname;
    @UniqueEmail()
    private String email;
    @NotNull
    private int phoneNumber;
    private String birthDate;
    private String idCard;
    private String address;
    private String role;
    private String status;
    private String createdDate;
    private String modifiedDate;

    public Account asAccount(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Account account = new Account();
        account.setUsername(this.username);
        account.setPassword(encoder.encode(this.password));
        account.setFullname(this.fullname);
        account.setEmail(this.email);
        account.setPhoneNumber(this.phoneNumber);
        account.setBirthDate(convertDate(this.birthDate));
        account.setIdCard(this.idCard);
        account.setAddress(this.address);
        account.setRole(Role.USER);
        account.setStatus(AccountStatus.ACTIVE);
        if (this.createdDate == null|| this.createdDate.isEmpty()) {
            account.setCreatedDate(convertDate(LocalDate.now().toString()));
        }else {
            account.setCreatedDate(convertDate(this.createdDate));
        }
        if (this.modifiedDate == null|| this.modifiedDate.isEmpty()) {
            account.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        else {
            account.setModifiedDate(convertDate(this.modifiedDate));
        }
        return account;
    }

    public Account updateAccount(Account account){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(this.password));
        account.setFullname(this.fullname);
        account.setEmail(this.email);
        account.setPhoneNumber(this.phoneNumber);
        account.setBirthDate(convertDate(this.birthDate));
        account.setIdCard(this.idCard);
        account.setAddress(this.address);
        if (this.modifiedDate == null|| this.modifiedDate.isEmpty()) {
            account.setModifiedDate(convertDate(LocalDate.now().toString()));
        }
        return account;
    }
}
