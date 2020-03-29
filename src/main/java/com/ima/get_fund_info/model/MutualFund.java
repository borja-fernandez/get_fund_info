package com.ima.get_fund_info.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MutualFund {

    private String isin;
    private String fundName;
    private String fundHouse;
    private String fundCategory;
    private String FundSubCategory;
    private String currencyISO;
    private double expensesRatio;
    private double netValue;
    private Date valueDate;
    private double dailyChange;

}
