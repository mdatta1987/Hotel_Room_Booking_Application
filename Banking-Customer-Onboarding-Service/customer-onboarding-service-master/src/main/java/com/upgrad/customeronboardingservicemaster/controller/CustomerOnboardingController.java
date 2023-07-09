package com.upgrad.customeronboardingservicemaster.controller;

import com.upgrad.customeronboardingservicemaster.model.CoreBankingResponseVO;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardRequestVO;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardResponseVO;
import com.upgrad.customeronboardingservicemaster.service.CustomerOnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerOnboardingController {
    CustomerOnboardingService service;

    public CustomerOnboardingController(CustomerOnboardingService service) {
        this.service = service;
    }

    @PostMapping("/customer/v1/onboard")
    public ResponseEntity<CustomerOnboardResponseVO> onboardCustomer(@RequestBody CustomerOnboardRequestVO customer) {
        return ResponseEntity.ok(service.saveCustomer(customer));
    }

    @PostMapping("/customer/v1/validate")
    public ResponseEntity<CustomerOnboardResponseVO> validateCoreCustomer(@RequestBody CustomerOnboardRequestVO customer) {
        return ResponseEntity.ok(service.validateCustomer(customer));
    }

    @GetMapping("/v1/customer/{customerId}")
    public ResponseEntity<CustomerOnboardResponseVO> getCoreCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(service.getCustomer(customerId));
    }
}
