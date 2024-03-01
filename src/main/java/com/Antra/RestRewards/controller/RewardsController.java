package com.Antra.RestRewards.controller;

import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.vo.Points;
import com.Antra.RestRewards.vo.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Get a user's points for the past months. Default is 3 months.")
    @GetMapping("/{userId}")
    public ResponseEntity<Points> getPointsByUserIdPastMonths(@PathVariable("userId") long userId, @RequestParam(required = false, defaultValue = "3") int months) {
        Points points = transactionService.getPastMonthsPointsTotalByuserID(userId, months);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @Operation(summary = "Add a transaction to a user's account.")
    @PostMapping("/")
    public ResponseEntity<String> addPointsByUserId(@Validated @RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary = "Get all rewards for a user.")
    @GetMapping("/all/{userId}")
    public ResponseEntity<Points> getAllPointsByUserId(@PathVariable("userId") long userId) {
        Points points = transactionService.getAllPointsByUserId(userId);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }






}
