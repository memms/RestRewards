package com.Antra.RestRewards.service;

import com.Antra.RestRewards.vo.Points;
import com.Antra.RestRewards.vo.Transaction;
import org.springframework.stereotype.Service;

public interface TransactionService {

    Points getPastMonthsPointsTotalByuserID(long userID, int months);

    Transaction addTransaction(Transaction transaction);

    Points getAllPointsByUserId(long userId);
}
