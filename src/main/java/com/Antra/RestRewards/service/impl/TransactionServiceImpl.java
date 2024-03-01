package com.Antra.RestRewards.service.impl;

import com.Antra.RestRewards.domain.dto.TransactionDTO;
import com.Antra.RestRewards.domain.entity.TransactionEntity;
import com.Antra.RestRewards.exception.InvalidRequestException;
import com.Antra.RestRewards.exception.transaction.InvalidTransactionException;
import com.Antra.RestRewards.exception.transaction.TransactionNotFoundException;
import com.Antra.RestRewards.repository.TransactionRepository;
import com.Antra.RestRewards.service.TransactionService;
import com.Antra.RestRewards.utility.TransactionUtil;
import com.Antra.RestRewards.domain.vo.Points;
import com.Antra.RestRewards.domain.vo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction getTransactionById(long transactionId) {
        return TransactionUtil.convertFromEntity(transactionRepository.findById(transactionId).orElse(null));
    }

    @Override
    public Points getPastMonthsPointsTotalByuserID(long userID, int months) {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(months);
        List<TransactionEntity> transactions = transactionRepository.findAllByUserIdAndDateAfterOrderByDateDesc(userID, threeMonthsAgo);
//      TODO: Delete this once UserEntity is set up. Right now this determines if user exists.
        if(transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user " + userID + " in the past " + months + " months.");
        }
        int totalPoints = 0;
        for(TransactionEntity transaction : transactions) {
            totalPoints += TransactionUtil.calculatePoints(transaction.getAmount());
        }
        return new Points(userID, totalPoints);
    }

    @Override
    public Transaction addTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionRepository.save(new TransactionEntity(transactionDTO.getUserId(), transactionDTO.getAmount(), transactionDTO.getDate()));
        if(transactionEntity == null) {
            throw new InvalidTransactionException("Exception occurred while adding transaction. Please try again.");
        }
        return TransactionUtil.convertFromEntity(transactionEntity);
    }

    @Override
    public Points getAllPointsByUserId(long userId) {
        List<TransactionEntity> transactions = transactionRepository.findAllByUserId(userId);
//      TODO: Delete this once UserEntity is set up. Right now this determines if user exists.
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user " + userId + ".");
        }
        int totalPoints = 0;
        for(TransactionEntity transaction : transactions) {
            totalPoints += TransactionUtil.calculatePoints(transaction.getAmount());
        }
        return new Points(userId, totalPoints);
    }

    @Override
    public List<Transaction> getAllByUserId(long userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId).stream()
                .map(TransactionUtil::convertFromEntity)
                .toList();
//      TODO: Delete this once UserEntity is set up. Right now this determines if user exists.
        if(transactionRepository.findAllByUserId(userId).isEmpty()){
            throw new TransactionNotFoundException("No transactions found for user " + userId + ".");
        }
        return transactions;
    }

    @Override
    public Transaction updateTransaction(long transactionId, TransactionDTO transactionDTO) {
        if(!transactionRepository.existsById(transactionId)){
            throw new TransactionNotFoundException("Transaction with id " + transactionId + " does not exist.");
        }
        TransactionEntity transactionEntity = transactionRepository.saveAndFlush(
                new TransactionEntity(transactionId, transactionDTO.getUserId(), transactionDTO.getAmount(), transactionDTO.getDate()));
        return TransactionUtil.convertFromEntity(transactionEntity);
    }

    @Override
    public void deleteTransactionById(long transactionId) {
        if(!transactionRepository.existsById(transactionId)){
            throw new TransactionNotFoundException("Transaction with id " + transactionId + " does not exist.");
        }
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public Transaction updateTransactionByFields(long transactionId, Map<String, Object> fields) {
        Optional<TransactionEntity> transactionEntityOptional = transactionRepository.findById(transactionId);
        if(transactionEntityOptional.isEmpty()){
            throw new TransactionNotFoundException("Transaction with id " + transactionId + " does not exist.");
        }
        TransactionEntity transactionEntity = transactionEntityOptional.get();
        try{
            fields.forEach((k, v) -> {
                switch (k) {
                    case "userId" -> transactionEntity.setUserId((Long) v);
                    case "amount" -> transactionEntity.setAmount((Double) v);
                    case "date" -> transactionEntity.setDate(LocalDate.parse((String) v));
                    default -> {
                        throw new InvalidRequestException("Invalid field: " + k);
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Invalid field.");
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid date format. Use yyyy-MM-dd.");
        } catch (ClassCastException e) {
            throw new InvalidRequestException("Invalid field type.");
        }

        return TransactionUtil.convertFromEntity(transactionRepository.saveAndFlush(transactionEntity));
    }

//    @Override
//    public Transaction updateTransactionByFields(Transaction transaction) {
//
//        return TransactionUtil.convertFromEntity(transactionRepository.saveAndFlush(
//                new TransactionEntity(transaction.getId(), transaction.getUserId(), transaction.getAmount(), transaction.getDate())
//        ));
//    }


}
