package com.Antra.RestRewards.service;

import com.Antra.RestRewards.vo.Transaction;
import org.springframework.stereotype.Service;

public interface TransactionService {

    Integer getPastMonthsPointsTotalByuserID(long userID, int months);

    Transaction addTransaction(Transaction transaction);
}
