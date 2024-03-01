package com.Antra.RestRewards.controller;

import com.Antra.RestRewards.domain.dto.ErrorResponseDTO;
import com.Antra.RestRewards.exception.InvalidRequestException;
import com.Antra.RestRewards.exception.rewards.RewardsCalculationException;
import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.domain.vo.Points;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Get a user's points for the past months. Default is 3 months.")
    @GetMapping("/{userId}")
    public ResponseEntity<Points> getPointsByUserIdPastMonths(@PathVariable("userId") long userId, @RequestParam(required = false, defaultValue = "3") int months) {
        if(userId < 0) {
            throw new InvalidRequestException("Invalid user id.");
        }
        if(months < 0 || months > 12) {
            throw new InvalidRequestException("Invalid months");
        }
        Points points = transactionService.getPastMonthsPointsTotalByuserID(userId, months);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @Operation(summary = "Get all rewards for a user.")
    @GetMapping("/all/{userId}")
    public ResponseEntity<Points> getAllPointsByUserId(@PathVariable("userId") long userId) {
        if(userId < 0) {
            throw new InvalidRequestException("Invalid user id.");
        }
        Points points = transactionService.getAllPointsByUserId(userId);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }



//  TODO: Implement this when RewardsEntity is setup
    @ExceptionHandler(RewardsCalculationException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }






}
