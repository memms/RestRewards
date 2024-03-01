package com.Antra.RestRewards.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Getter
@Setter
public final class Transaction {

    private final Long id;

    @NotNull(message = "User Id is required")
    @Min(value = 1, message = "User Id should be greater than 0")
    private final Long userId;

    //    Throws: HttpMessageNotReadableException.class

    @NotNull(message = "Date is required")
    private final LocalDate date;


    @Min(value = 0, message = "Amount should be greater than 0")
    @NotNull(message = "Amount is required")
    private final Double amount;
}
