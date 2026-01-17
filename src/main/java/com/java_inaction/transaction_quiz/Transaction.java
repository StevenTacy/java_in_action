package com.java_inaction.transaction_quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private long value;
}
