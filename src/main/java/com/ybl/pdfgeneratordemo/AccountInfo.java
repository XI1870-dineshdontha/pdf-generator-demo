package com.ybl.pdfgeneratordemo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AccountInfo {
    private String accountNumber;
    private String accountType;
    private String currency;
    private Double balance;
    private String branchCode;
    private String branchAddress;
    private Integer productCode;
    private String IFSCCode;
    private String MICRCode;
    private String salutation;
    private String primaryHolder;
    private String jointHolder1;
    private String jointHolder2;
    private String accountStatus;
    private Double overdraftLimit;
    private String accountOpeningDate;
    private CustomerInfo customerInfo;
    private NomineeInfo nomineeInfo;
    private List<TransactionInfo> transactionInfoList;
}
