package com.upgrad.corebankingaclservice.controller;

import com.upgrad.corebankingaclservice.model.CoreBankingResponseVO;
import com.upgrad.corebankingaclservice.model.CustomerOnboardRequestVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class CoreBankingACLController {
    @PostMapping("customer/v1/validate")
    public ResponseEntity<CoreBankingResponseVO> validateCustomer(@RequestBody CustomerOnboardRequestVO customerOnboardRequestVO) {
        return ResponseEntity.ok(CoreBankingResponseVO
                .builder()
                .coreBankingCustomerId(UUID.randomUUID().toString())
                .build());
    }
}
