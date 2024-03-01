package com.Antra.RestRewards.controller;

import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.vo.Points;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.Antra.RestRewards.vo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
//@Api(value = "Rewards", description = "REST API for Rewards", tags={"Rewards"})
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

//    @ApiOperation(value = "Get points by user id for past months")
    @GetMapping("/{userId}")
    public ResponseEntity<Points> getPointsByUserIdPastMonths(@PathVariable("userId") long userId, @RequestParam(required = false, defaultValue = "3") int months) {
        Points points = new Points(userId, transactionService.getPastMonthsPointsTotalByuserID(userId, months));
     
        System.out.println("points: " + points.getPoints());
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addPointsByUserId(@Validated @RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("BAD", HttpStatus.BAD_REQUEST);
    }






}
