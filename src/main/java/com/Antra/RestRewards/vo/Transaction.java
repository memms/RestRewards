package com.Antra.RestRewards.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Getter
@Setter
public final class Transaction {

    private final Long id;

    @NotNull
    private final Long userId;

    @NotNull
    private final LocalDate date;

    @Min(0)
    @NotNull
    private final Double amount;
}
