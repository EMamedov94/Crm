package com.example.crm.controllers;

import com.example.crm.dto.DepositDto;
import com.example.crm.service.deposit.DepositService;
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
    public ResponseEntity<Object> openNewDeposit(@RequestBody DepositDto depositDto,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(depositService.openNewDeposit(depositDto));
    }

    @PostMapping("/closeDeposit")
    public ResponseEntity<Object> closeDeposit(@RequestBody DepositDto depositDto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(depositService.closeDeposit(depositDto));
    }
}
