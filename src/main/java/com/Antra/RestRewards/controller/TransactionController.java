package com.Antra.RestRewards.controller;

import com.Antra.RestRewards.domain.dto.TransactionDTO;
import com.Antra.RestRewards.exception.InvalidRequestException;
import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.domain.vo.Transaction;
import com.Antra.RestRewards.utility.TransactionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

//    @Autowired
//    private ObjectMapper mapper;

    @Operation(summary = "Get all transactions by user id.")
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(long userId) {
        if(userId < 0) {
            throw new InvalidRequestException("Invalid user id.");
        }
        List<Transaction> transactions = transactionService.getAllByUserId(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @Operation(summary = "Add a transaction to a user's account.")
    @PostMapping("/")
    public ResponseEntity<Transaction> addTransactionByUserId(@Validated @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction1 = transactionService.addTransaction(transactionDTO);
        return new ResponseEntity<>(transaction1, HttpStatus.OK);
    }

    @Operation(summary = "Update a transaction by transaction id.")
    @PutMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransactionByTransactionId(@PathVariable("transactionId") long transactionId, @Validated @RequestBody TransactionDTO transactionDTO) {
        if(transactionId < 0) {
            throw new InvalidRequestException("Invalid transaction id.");
        }
        Transaction transaction = transactionService.updateTransaction(transactionId, transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Operation(summary = "Delete a transaction by transaction id.")
    @ApiResponse(responseCode = "204", description = "Transaction deleted.")
    @DeleteMapping("/{transactionId}")
    public ResponseEntity deleteTransactionByTransactionId(@PathVariable("transactionId") long transactionId) {
        if(transactionId < 0) {
            throw new InvalidRequestException("Invalid transaction id.");
        }
        transactionService.deleteTransactionById(transactionId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Patch a transaction by transaction id.")
    @PatchMapping("/{transactionId}")
    public ResponseEntity<Transaction> patchTransactionByTransactionId(@PathVariable("transactionId") long transactionId, @RequestBody Map<String, Object> fields) {
        if (fields.isEmpty()) {
            throw new InvalidRequestException("Invalid fields.");
        }
        if(transactionId < 0) {
            throw new InvalidRequestException("Invalid transaction id.");
        }
        return new ResponseEntity<>(transactionService.updateTransactionByFields(transactionId, fields), HttpStatus.OK);
    }



//    TODO: Research JsonPatch and fix.
//    @Operation(summary = "Patch a transaction by transaction id.")
//    @PatchMapping("/{transactionId}")
//    public ResponseEntity<Transaction> patchTransactionByTransactionId(@PathVariable("transactionId") long transactionId, @RequestBody JsonPatch patch) {
//        try{
//            Transaction transaction = transactionService.getTransactionById(transactionId);
//            Transaction transactionPatched = applyPatchToTransaction(patch, transaction);
//            return new ResponseEntity<>(transactionService.updateTransactionByFields(transactionPatched), HttpStatus.OK);
//        } catch (JsonPatchException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private Transaction applyPatchToTransaction(JsonPatch patch, Transaction targetTransaction) throws JsonPatchException {
//        JsonNode patched = patch.apply(mapper.convertValue(targetTransaction, JsonNode.class));
//        return mapper.convertValue(patched, Transaction.class);
//    }
}
