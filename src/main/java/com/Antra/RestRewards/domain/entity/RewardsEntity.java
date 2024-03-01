package com.Antra.RestRewards.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

//TODO: Seperate rewards from transactions table to make calculation quicker on getting rewards
//TODO: This is because the reward will be added once but get many times, thus 1 calculation instead of many
public class RewardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserEntity userEntity;


    private TransactionEntity transactionEntity;

    private Integer rewardsAmount;

}