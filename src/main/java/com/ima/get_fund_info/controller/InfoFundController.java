package com.ima.get_fund_info.controller;

import com.ima.get_fund_info.GetFundInfoApplication;
import com.ima.get_fund_info.client.FinancialTimeProxy;
import com.ima.get_fund_info.client.FinancialTimesHTML;
import com.ima.get_fund_info.client.QueFondosHTML;
import com.ima.get_fund_info.dictionary.QueFondosDictionary;
import com.ima.get_fund_info.model.MutualFund;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class InfoFundController {

    private static final Logger logger = LogManager.getLogger(InfoFundController.class);

    @Autowired
    private FinancialTimeProxy financialTimeProxy;

    @GetMapping(path = "financialtimes/isin/{isin}")
    public String getName(@PathVariable("isin") String isin){
        logger.info("Financial Times process is running. ISIN:" + isin);

        return financialTimeProxy.getHTML(isin);

    }

    @GetMapping(path = "quefondos/isin/{isin}")
    public MutualFund getNameJSOUP(@PathVariable("isin") String isin){

        logger.info("Que Fondos process is running. ISIN:" + isin);

         try {

             return QueFondosHTML.getFund(isin);

         } catch (Exception e){
             logger.fatal("Fatal error in Que Fondos process");
             e.printStackTrace();
        }

         return null;
     }
}
