package com.example.crm.service.impl.deposit;

import com.example.crm.dto.DepositDto;
import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Status;
import com.example.crm.exception.DepositAlreadyClosedException;
import com.example.crm.exception.DepositNotFoundException;
import com.example.crm.exception.UserNotFoundException;
import com.example.crm.exception.ValidationException;
import com.example.crm.repository.DepositRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.deposit.ValidationDeposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ValidationDepositImpl implements ValidationDeposit {
    private final DepositRepository depositRepository;
    private final PersonRepository personRepository;

    @Override
    public void validateBalance(Double amount, Double balance) {
        List<String> errors = new ArrayList<>();

        if (balance == null || amount > balance) {
            errors.add("Недостаточно средств");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    @Override
    public Deposit findDepositAndValidateStatus(DepositDto depositDto) {
        Deposit depositDb = depositRepository.findById(depositDto.getId())
                .orElseThrow(DepositNotFoundException::new);

        if (depositDb.getStatus() == Status.CLOSED) {
            throw new DepositAlreadyClosedException();
        }
        return depositDb;
    }

    public List<Deposit> validateClientAndDeposits(Long clientId) {
        if (!personRepository.existsById(clientId)) {
            throw new UserNotFoundException();
        }

        List<Deposit> deposits = depositRepository.findAllByDepositHolderId(clientId);;
        if (deposits.isEmpty()) {
            throw new DepositNotFoundException();
        }
        return deposits;
    }
}
