package com.upgrad.customeronboardingservicemaster.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoreBankingResponseVO {
    private String bankingCustomerId;
}
