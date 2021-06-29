package com.ybl.pdfgeneratordemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerInfo {
    private String customerName; //${customerInfo.customerName}
    private String emailId;
    private String mobileNumber;
    private CustomerAddress customerAddress;
}
