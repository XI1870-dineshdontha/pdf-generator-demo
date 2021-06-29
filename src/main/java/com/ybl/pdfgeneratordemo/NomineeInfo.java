package com.ybl.pdfgeneratordemo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NomineeInfo {
    private String nomineeName;
    private Integer nomineeAge;
    private String relationShip;
}
