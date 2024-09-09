package no.controller;

import no.modal.DTO.AccountDTO;
import no.modal.entity.Account;
import no.modal.request.AccountRequest;
import no.services.account.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("Account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("getAllAccount/{page}/{size}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllAccount(@PathVariable int page, @PathVariable int size) {
        Page<Account> accountPage = accountService.findAll(page, size);
        List<AccountDTO> accountDTOList = accountPage.getContent().stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> getAccountById(@PathVariable long id) {
        return new ResponseEntity<>(modelMapper.map(accountService.findById(id), AccountDTO.class), HttpStatus.OK);
    }

    @PutMapping("updateAccount/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> updateAccount(@PathVariable long id, @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(modelMapper.map(accountService.updateAccount(accountRequest, id), AccountDTO.class), HttpStatus.OK);
    }

    @GetMapping("deleteAccount/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> deleteAccount(@PathVariable long id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }
}
