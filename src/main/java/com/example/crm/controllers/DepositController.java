package com.example.crm.controllers;

import com.example.crm.entity.products.Deposit;
import com.example.crm.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @PostMapping("/openNewDeposit")
    public ResponseEntity<Object> openNewDeposit(@RequestBody Deposit deposit,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(depositService.openNewDeposit(deposit));
    }
}
