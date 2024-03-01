package com.Antra.RestRewards.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "h_transaction")
public class TransactionEntity {

    public TransactionEntity(Long userId, Double amount, LocalDate date) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

//  TODO: Change this to userEntity later to join User and Transaction Tables.
    @Column(name = "userID")
    private Long userId;

    @Column
    private Double amount;

//    Transaction Date
    @Column
    private LocalDate date;

}
