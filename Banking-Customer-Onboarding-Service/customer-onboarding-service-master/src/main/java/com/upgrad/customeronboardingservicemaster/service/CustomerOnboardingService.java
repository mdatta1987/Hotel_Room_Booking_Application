package com.upgrad.customeronboardingservicemaster.service;

import com.upgrad.customeronboardingservicemaster.feign.CoreBankingACLClient;
import com.upgrad.customeronboardingservicemaster.model.CoreBankingResponseVO;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardRequestVO;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardResponseVO;
import com.upgrad.customeronboardingservicemaster.repository.CustomerOnboardingRepository;
import com.upgrad.customeronboardingservicemaster.entity.Customer;
import org.springframework.stereotype.Service;
import java.util.UUID;
import static com.upgrad.customeronboardingservicemaster.constants.CustomerOnboardingConstants.ONBOARDING_STATUS;

@Service
public class CustomerOnboardingService {
    private CustomerOnboardingRepository repository;
    private CoreBankingACLClient coreClient;

    public CustomerOnboardingService(CustomerOnboardingRepository repository, CoreBankingACLClient coreClient) {
        this.repository = repository;
        this.coreClient = coreClient;
    }
    public CustomerOnboardResponseVO saveCustomer(CustomerOnboardRequestVO customerVO) {
        String customerId = UUID.randomUUID().toString();

        Customer customer = repository.save(Customer
                            .builder()
                            .customerId(customerVO.getCustomerId())
                            .bankingCustomerId(customerVO.getBankingCustomerId())
                            .customerDateOfBirth(customerVO.getCustomerDateOfBirth())
                            .customerMobileNumber(customerVO.getCustomerMobileNumber())
                            .build());

        return CustomerOnboardResponseVO
                .builder()
                .customerId(customerId)
                .onBoardingStatus(ONBOARDING_STATUS)
                .build();
    }
    public CustomerOnboardResponseVO validateCustomer (CustomerOnboardRequestVO customerVO) {
        CoreBankingResponseVO responseVO = coreClient.getAccountStatus(customerVO);

        return CustomerOnboardResponseVO
            .builder()
            .bankingCustomerId(responseVO.getBankingCustomerId())
            .build();
    }
    public CustomerOnboardResponseVO getCustomer(String customerId) {
        Customer customer = repository.findById(customerId).map(cust -> {
            return Customer
                    .builder()
                    .customerId(cust.getCustomerId())
                    .bankingCustomerId(cust.getBankingCustomerId())
                    .customerDateOfBirth(cust.getCustomerDateOfBirth())
                    .customerMobileNumber(cust.getCustomerMobileNumber())
                    .build();
        })
             .orElse(null);

        return CustomerOnboardResponseVO
                .builder()
                .customerId(customer.getCustomerId())
                .bankingCustomerId(customer.getBankingCustomerId())
                .customerDateOfBirth(customer.getCustomerDateOfBirth())
                .customerMobileNumber(customer.getCustomerMobileNumber())
                .build();
    }
}
