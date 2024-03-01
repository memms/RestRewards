package com.Antra.RestRewards.service.impl;

import com.Antra.RestRewards.entity.TransactionEntity;
import com.Antra.RestRewards.repository.TransactionRepository;
import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.utility.TransactionUtil;
import com.Antra.RestRewards.vo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Integer getPastMonthsPointsTotalByuserID(long userID, int months) {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(months);
        List<TransactionEntity> transactions = transactionRepository.findAllByUserIdAndDateAfterOrderByDateDesc(userID, threeMonthsAgo);
        Logger.getLogger("Transactions").info("Transactions: " + transactions);

        int totalPoints = 0;
        for(TransactionEntity transaction : transactions) {
            totalPoints += TransactionUtil.calculatePoints(transaction.getAmount());
        }
        return totalPoints;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = transactionRepository.save(new TransactionEntity(transaction.getUserId(), transaction.getAmount(), transaction.getDate()));
        return TransactionUtil.convertFromEntity(transactionEntity);
    }


}
