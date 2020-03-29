package com.ima.get_fund_info.client;

import com.ima.get_fund_info.dictionary.QueFondosDictionary;
import com.ima.get_fund_info.model.MutualFund;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class QueFondosHTML {

    public static MutualFund getFund(String isin) throws Exception{

        MutualFund mutualFund = new MutualFund();

        try {
            Document doc = Jsoup.connect("https://www.quefondos.com/es/fondos/ficha/index.html?isin=" + isin).get();
            doc.select("p").forEach( element -> updateMutualFund(mutualFund, element));
            mutualFund.setFundName(doc.select("h2").last().text());

        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return mutualFund;
    }

    private static void updateMutualFund(MutualFund mutualFund, Element element){
        //System.out.println(element.text());
//        element.getAllElements().forEach(e -> System.out.println(e.text()));
        try {

            String key = element.text().split(":")[0];
            System.out.println(key);

            if (key.equalsIgnoreCase(QueFondosDictionary.FUND_HOUSE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.FUND_CATEGORY) ||
                    key.equalsIgnoreCase(QueFondosDictionary.CURRENCY) ||
                    key.equalsIgnoreCase(QueFondosDictionary.CONSTANT_EXPENSE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.STORE_EXPENSE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.NET_VALUE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.NET_DATE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.DIFFERENCE) ||
                    key.equalsIgnoreCase(QueFondosDictionary.ISIN)
            ) {

                String value = element.text().split(":")[1].trim();

                if (key.equalsIgnoreCase(QueFondosDictionary.FUND_HOUSE)) mutualFund.setFundHouse(element.getAllElements().get(2).text());
                if (key.equalsIgnoreCase(QueFondosDictionary.FUND_CATEGORY)) mutualFund.setFundSubCategory(element.getAllElements().get(2).text());
                if (key.equalsIgnoreCase(QueFondosDictionary.FUND_CATEGORY)) mutualFund.setFundCategory(element.getAllElements().last().text());
                if (key.equalsIgnoreCase(QueFondosDictionary.ISIN)) mutualFund.setIsin(value);
                if (key.equalsIgnoreCase(QueFondosDictionary.CURRENCY)) mutualFund.setCurrencyISO(value);
                if (key.equalsIgnoreCase(QueFondosDictionary.CONSTANT_EXPENSE) || key.equalsIgnoreCase(QueFondosDictionary.STORE_EXPENSE)) {
                    System.out.println("ENTRA PARA COGER EL FIJO O EL DEPÓSICO");
                    value = value.substring(0, value.length() - 1);
                    Locale spanish = new Locale("es", "ES");
                    NumberFormat nf = NumberFormat.getInstance(spanish);
                    mutualFund.setExpensesRatio(mutualFund.getExpensesRatio() + nf.parse(value).doubleValue());
                }
                if (key.equalsIgnoreCase(QueFondosDictionary.NET_VALUE)) {
                    System.out.println("ENTRA PARA COGER EL VALOR LIQUIDATIVO");
                    value = value.split(" ")[0];
                    Locale spanish = new Locale("es", "ES");
                    NumberFormat nf = NumberFormat.getInstance(spanish);
                    mutualFund.setNetValue(nf.parse(value).doubleValue());
                }
                if (key.equalsIgnoreCase(QueFondosDictionary.NET_DATE)) {
                    System.out.println("ENTRA PARA COGER LA FECHA");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    mutualFund.setValueDate(formatter.parse(value));
                }
                if (key.equalsIgnoreCase(QueFondosDictionary.DIFFERENCE)) {
                    System.out.println("ENTRA PARA COGER LA DIFERENCIA");
                    value = value.substring(0, value.length() - 1);
                    Locale spanish = new Locale("es", "ES");
                    NumberFormat nf = NumberFormat.getInstance(spanish);
                    mutualFund.setDailyChange(nf.parse(value).doubleValue());
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
