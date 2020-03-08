package com.ima.get_fund_info.controller;

import com.ima.get_fund_info.client.FinancialTimeProxy;
import com.ima.get_fund_info.client.FinancialTimesHTML;
import com.ima.get_fund_info.client.QueFondosHTML;
import com.ima.get_fund_info.dictionary.QueFondosDictionary;
import com.ima.get_fund_info.model.MutualFund;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "InfoFund/v1/")
public class InfoFundControler {

    @Autowired
    private FinancialTimeProxy financialTimeProxy;

    @GetMapping(path = "name/{isin}")
    public String getName(@PathVariable("isin") String isin){

        return financialTimeProxy.getHTML(isin);

    }

    @GetMapping(path = "jsoup/name/{isin}")
    public MutualFund getNameJSOUP(@PathVariable("isin") String isin){

         try {

             return QueFondosHTML.getFund(isin);

         } catch (Exception e){
            e.printStackTrace();
        }

         return null;
     }
}
