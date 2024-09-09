package no.services.account;

import no.modal.entity.Account;
import no.modal.reponse.AuthReponse;
import no.modal.request.AccountRequest;
import org.springframework.data.domain.Page;

public interface AccountService {
    // get
    Page<Account> findAll(int page, int size);
    Account findById(long id);
    // create
    Account createAccount(AccountRequest form);
    // update
    Account updateAccount(AccountRequest form, long id);
    // delete
    boolean deleteAccount(long id);
    // login
    AuthReponse login(String username, String password);
}
