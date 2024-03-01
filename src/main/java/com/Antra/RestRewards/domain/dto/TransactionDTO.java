package com.Antra.RestRewards.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TransactionDTO {

    @NotNull(message = "User Id is required")
    @Min(value = 1, message = "User Id should be greater than 0")
    private Long userId;

//  Throws: HttpMessageNotReadableException.class
    @PastOrPresent(message = "Date should be in the past or present")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date is required")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Min(value = 0, message = "Amount should be greater than 0")
    @NotNull(message = "Amount is required")
    private Double amount;
}
