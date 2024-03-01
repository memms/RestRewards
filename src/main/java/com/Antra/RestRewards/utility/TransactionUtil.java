package com.Antra.RestRewards.utility;

import com.Antra.RestRewards.entity.TransactionEntity;
import com.Antra.RestRewards.vo.Transaction;

public class TransactionUtil {

    public static int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * (int) (amount - 100);
            points += 50;
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }

    public static Transaction convertFromEntity(TransactionEntity transactionEntity){
        return new Transaction(
                transactionEntity.getId(),
                transactionEntity.getUserId(),
                transactionEntity.getDate(),
                transactionEntity.getAmount()
        );
    }
}
