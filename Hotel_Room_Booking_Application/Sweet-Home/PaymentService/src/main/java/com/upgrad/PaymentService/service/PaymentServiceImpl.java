package com.upgrad.PaymentService.service;

import com.upgrad.PaymentService.dao.PaymentDao;
import com.upgrad.PaymentService.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    public PaymentDao paymentDao;

    @Override
    public TransactionDetailsEntity saveTransactionDetails(TransactionDetailsEntity transactionDetailsEntity) {
        return paymentDao.save(transactionDetailsEntity);
    }

    @Override
    public TransactionDetailsEntity findByTransactionId(int transactionId) {
        Optional<TransactionDetailsEntity> allTransactionDetailsEntity = paymentDao.findById(transactionId);
        TransactionDetailsEntity transactionDetailsEntity = allTransactionDetailsEntity.get();

        return transactionDetailsEntity;
    }
}
