package no.controller;

import no.modal.DTO.AccountDTO;
import no.modal.request.AccountRequest;
import no.services.account.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("noAuth")
public class UnAuthController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody String username, @RequestBody String password) {
        return ResponseEntity.ok(accountService.login(username, password));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(modelMapper.map(accountService.createAccount(accountRequest), AccountDTO.class), HttpStatus.CREATED);
    }

}
