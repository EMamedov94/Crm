package com.example.crm.controllers;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;
import com.example.crm.service.deposit.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("deposits")
@RequiredArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @PostMapping("/openNewDeposit")
    public ResponseEntity<Deposit> openNewDeposit(@RequestBody DepositDto depositDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(depositService.openNewDeposit(depositDto));
    }

    @PostMapping("/closeDeposit")
    public ResponseEntity<Deposit> closeDeposit(@RequestBody DepositDto depositDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(depositService.closeDeposit(depositDto));
    }

    // Расчет и отображение суммы потерянных % если депозит закрывается раньше времени
    @GetMapping("/calculateClosureAmount")
    public ResponseEntity<Double> calculateClosureAmount(@RequestBody DepositDto depositDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(depositService.calculateClosureAmount(depositDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Deposit>> findDepositsByClientId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(depositService.findDepositsByClientId(id));
    }
}
