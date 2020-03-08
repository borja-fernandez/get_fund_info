package com.ima.get_fund_info.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FinancialTimesHTML {

    public static Document getNameAndCurrency(String isin) throws Exception{

        try {
            Document doc = Jsoup.connect("https://markets.ft.com/data/funds/tearsheet/summary?s=" + isin).get();
            String titleLabel = doc.select("title").first().text();
            if (titleLabel.split(",")[1].split(":")[1].substring(0, 3).trim().length() > 0) return doc;

            /**
            // Get Name & Currency ISO
            Document nameAndCurrency = FinancialTimesHTML.getNameAndCurrency(isin);
            String titleLabel = nameAndCurrency.select("title").first().text();
            mutualFund.setIsin(titleLabel.split(",")[1].split(":")[0].trim());
            mutualFund.setFundName(titleLabel.split(",")[0].trim());
            mutualFund.setCurrencyISO(titleLabel.split(",")[1].split(":")[1].substring(0, 3));

             */
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return null;

    }
}
