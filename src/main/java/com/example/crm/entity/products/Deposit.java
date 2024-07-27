package com.example.crm.entity.products;

import com.example.crm.entity.Person;
import com.example.crm.enums.Currency;
import com.example.crm.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Double interestRate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String depositNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Person depositHolder;
}
