package no.modal.reponse;

import lombok.Data;
import no.modal.entity.Account;

@Data
public class AuthReponse {
    private String token;
    private String type="Bearer ";
    private long id;
    private String username;
    private String email;

    public AuthReponse(String token, Account user){
        this.token = token;
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
