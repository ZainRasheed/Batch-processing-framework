package com.example.BatchProcessingInHouseFramework.util;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {

    public static List<Object> getTransactions(int page) {
        int initValue = page*100;
        List<Object> transactions = new ArrayList<>();
        if (page == 6){
            return transactions;
        }
        for (int i = initValue; i<initValue+100; i++) {
            transactions.add(String.valueOf(i));
        }
        return transactions;
    }
}
