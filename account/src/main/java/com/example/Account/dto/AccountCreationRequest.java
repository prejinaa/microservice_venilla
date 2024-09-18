package com.example.Account.dto;

import com.example.Account.model.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record AccountCreationRequest (

    @NotBlank(message = "Account Number must  required")
    String accountNumber,

    @NotNull(message = "Balance must me required")
    Double balance,

    @Enumerated(EnumType.STRING)
    AccountType accountType,

    Long merchantId,

    Long customerId
)
  {
  }
