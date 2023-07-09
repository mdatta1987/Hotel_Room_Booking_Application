package com.upgrad.customeronboardingservicemaster.repository;

import com.upgrad.customeronboardingservicemaster.entity.Customer;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardRequestVO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOnboardingRepository extends MongoRepository<Customer, String> {
}
