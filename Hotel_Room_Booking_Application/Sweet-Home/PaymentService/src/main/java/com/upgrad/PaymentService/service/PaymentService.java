package com.upgrad.PaymentService.service;

import com.upgrad.PaymentService.entity.TransactionDetailsEntity;

public interface PaymentService {
    public TransactionDetailsEntity saveTransactionDetails(TransactionDetailsEntity transactionDetailsEntity);

    public TransactionDetailsEntity findByTransactionId(int transactionId);
}
