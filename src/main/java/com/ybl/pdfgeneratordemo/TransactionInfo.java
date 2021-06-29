package com.ybl.pdfgeneratordemo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TransactionInfo {
    private String transactionDate;
    private String valueDate;
    private String description;
    private String refNo;
    private Double depositedAmount;
    private Double withdrawnAmount;
    private Double runningBalance;
}
