package com.upgrad.customeronboardingservicemaster.feign;

import com.upgrad.customeronboardingservicemaster.model.CoreBankingResponseVO;
import com.upgrad.customeronboardingservicemaster.model.CustomerOnboardRequestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-onboarding-client", url = "${ACL-service-url}")
public interface CoreBankingACLClient {
    @PostMapping("customer/v1/validate")
    public CoreBankingResponseVO getAccountStatus(@RequestBody CustomerOnboardRequestVO customerOnboardResponseVO);
}
