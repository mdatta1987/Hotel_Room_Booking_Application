package com.upgrad.corebankingaclservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOnboardRequestVO {
    private String customerId;
    private String coreBankingCustomerId;
    private String customerMobileNumber;
    private Date customerDateOfBirth;
}
