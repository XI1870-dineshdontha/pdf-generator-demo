package com.ybl.pdfgeneratordemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAddress {
    private String addressLine1;
    private String addressLine2;
    private String area;
    private String district;
    private String state;
    private String pinCode;
}
