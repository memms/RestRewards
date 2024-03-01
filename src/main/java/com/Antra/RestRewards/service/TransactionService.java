package com.Antra.RestRewards.service;

import com.Antra.RestRewards.domain.dto.TransactionDTO;
import com.Antra.RestRewards.domain.vo.Points;
import com.Antra.RestRewards.domain.vo.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    Transaction getTransactionById(long transactionId);

    Points getPastMonthsPointsTotalByuserID(long userID, int months);

    Transaction addTransaction(TransactionDTO transactionDTO);

    Points getAllPointsByUserId(long userId);

    List<Transaction> getAllByUserId(long userId);

    Transaction updateTransaction(long transactionId, TransactionDTO transactionDTO);

    void deleteTransactionById(long transactionId);

    Transaction updateTransactionByFields(long transactionId, Map<String, Object> fields);
//    Transaction updateTransactionByFields(Transaction transaction);
}
