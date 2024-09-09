package no.services.account;

import no.configuaration.jwtSecurity.JwtTokenUltils;
import no.modal.entity.Account;
import no.modal.entity.constants.AccountStatus;
import no.modal.reponse.AuthReponse;
import no.modal.request.AccountRequest;
import no.repositoty.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private JwtTokenUltils jwtTokenUltils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> findAll(int page, int size) {
        return accountRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account createAccount(AccountRequest form) {
        return accountRepository.save(new AccountRequest().asAccount());
    }

    @Override
    public Account updateAccount(AccountRequest form, long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            form.updateAccount(account);
            accountRepository.save(account);
            return account;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteAccount(long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setStatus(AccountStatus.INACTIVE);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public AuthReponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = accountRepository.findByUsername(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUltils.generateToken(userDetails);
        AuthReponse authReponse = new AuthReponse(token, account);
        return authReponse;
    }
}
