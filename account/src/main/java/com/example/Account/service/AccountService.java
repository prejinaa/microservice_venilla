package com.example.Account.service;
import com.example.Account.dto.AccountCreationRequest;
import com.example.Account.dto.AccountResponse;
import com.example.Account.exception.CustomerNotFoundException;
import com.example.Account.exception.MerchantNotFoundException;
import com.example.Account.model.Account;
import com.example.Account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService{
    private final AccountRepository accountRepository;

    public AccountResponse createAccount(AccountCreationRequest creationRequest){
        Account account=new Account();
        account.setAccountNumber(creationRequest.accountNumber());
        account.setAccountType(creationRequest.accountType());
        account.setBalance(creationRequest.balance());
        account.setMerchantId(creationRequest.merchantId());
        account.setCustomerId(creationRequest.customerId());
        accountRepository.save(account);
        return new AccountResponse(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getAccountType(),
                account.getCreateDate()

        );
    }
    public AccountResponse getAccountById(Long accountId) throws RuntimeException {
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new RuntimeException("Account with this id not Found"));
        return new AccountResponse(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getAccountType(),
                account.getCreateDate()

        );
    }
    public List<AccountResponse> getAccountByMerchantId(Long merchantId) throws MerchantNotFoundException {

        List<Account>accounts=accountRepository.findByMerchantId(merchantId);
        if (accounts.isEmpty()) {
            throw new MerchantNotFoundException(merchantId);
        }
        return accounts.stream()
                .map(a -> new AccountResponse(
                        a.getAccountId(),
                        a.getAccountNumber(),
                        a.getBalance(),
                        a.getAccountType(),

                        a.getCreateDate()

                        ))
                .collect(Collectors.toList());


    }
    public List<AccountResponse>getAccountByCustomerById(Long customerId) throws CustomerNotFoundException {

        List<Account>accounts=accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
          return accounts.stream()
                  .map(account -> new AccountResponse(
                          account.getAccountId(),
                          account.getAccountNumber(),
                          account.getBalance(),
                          account.getAccountType(),

                          account.getCreateDate()

                  ))
                  .collect(Collectors.toList());
    }
    public void deleteAccount(Long accountId) throws RuntimeException{
            if(!accountRepository.existsById(accountId)){
                throw new RuntimeException("Account Not found");
            }
              accountRepository.deleteById(accountId);

    }



}
