package com.upgrad.PaymentService.controller;

import com.upgrad.PaymentService.dto.TransactionDTO;
import com.upgrad.PaymentService.entity.TransactionDetailsEntity;
import com.upgrad.PaymentService.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"*/*"})
    public ResponseEntity<Integer> createPayment(@RequestBody TransactionDTO transactionDTO) {
        TransactionDetailsEntity newTransactionDetailsEntity = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);
        TransactionDetailsEntity savedTransactionDetailsEntity = paymentService.saveTransactionDetails(newTransactionDetailsEntity);
        TransactionDTO savedtransactionDTO = modelMapper.map(savedTransactionDetailsEntity, TransactionDTO.class);

        /* Retrieving the transactionId received after updating the payment details */
        Integer transactionId = savedtransactionDTO.getTransactionId();

        return new ResponseEntity(transactionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable("transactionId") int transactionId) {
        TransactionDetailsEntity transactionDetailsEntity = paymentService.findByTransactionId(transactionId);
        TransactionDTO transactionDTO = modelMapper.map(transactionDetailsEntity, TransactionDTO.class);

        return new ResponseEntity(transactionDTO, HttpStatus.OK);
    }
}
