package com.ima.get_fund_info.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "get-fund-info", url = "https://markets.ft.com")
public interface FinancialTimeProxy {

    @GetMapping("/data/funds/tearsheet/historical?s={isin}")
    public String getHTML(@PathVariable("isin") String isin);
}
