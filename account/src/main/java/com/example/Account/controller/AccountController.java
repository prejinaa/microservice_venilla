package com.example.Account.controller;
import com.example.Account.dto.AccountCreationRequest;
import com.example.Account.dto.AccountResponse;
import com.example.Account.exception.CustomerNotFoundException;
import com.example.Account.exception.MerchantNotFoundException;
import com.example.Account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping()
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountCreationRequest accountCreationRequest){

           AccountResponse accountResponse= accountService.createAccount(accountCreationRequest);
           return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    ResponseEntity<AccountResponse> getAccountById(@PathVariable Long accountId){

        AccountResponse account=  accountService.getAccountById(accountId);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/merchant/{merchantId}")
    ResponseEntity<List<AccountResponse>>getAccountByMerchantId (@PathVariable Long merchantId)throws MerchantNotFoundException {
                     List< AccountResponse> accountResponse=  accountService.getAccountByMerchantId(merchantId);
                     return new ResponseEntity<>(accountResponse,HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<AccountResponse>>getAccountByCustomerId(@PathVariable Long customerId) throws CustomerNotFoundException {
        List< AccountResponse> accountResponse=  accountService.getAccountByCustomerById(customerId);
        return new ResponseEntity<>(accountResponse,HttpStatus.OK);
    }

    @DeleteMapping("{accountId}")
       ResponseEntity<?>deleteAccount(Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
