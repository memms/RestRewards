package com.Antra.RestRewards.repository;

import com.Antra.RestRewards.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByUserIdAndDateAfterOrderByDateDesc(long uid, LocalDate date);

    List<TransactionEntity> findAllByUserId(long uid);

}
