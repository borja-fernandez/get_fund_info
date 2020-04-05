package com.ima.get_fund_info.rabbitmq;

import com.ima.get_fund_info.client.QueFondosHTML;
import com.ima.get_fund_info.model.MutualFund;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.ArrayList;
import java.util.List;

@EnableBinding(RabbitMQBinding.class)
public class RabbitMQListener {

    private static final Logger logger = LogManager.getLogger(RabbitMQListener.class);

    @Autowired
    private RabbitMQPublisher publisher;

    @StreamListener(target = RabbitMQBinding.INPUT_MQ_CHANNEL)
    public void getDiaryNetValue(List<String> isinList) {

        logger.info("Diary Net Value Listener is executing...");

        List<MutualFund> mutualFundList = new ArrayList<MutualFund>();

        for(String isin: isinList) {
            try{
                publisher.publishFundList(QueFondosHTML.getFund(isin));
            } catch (Exception e) {
                logger.error("Error while QueFondos.com is taking information. ISIN code:" + isin);
            }
        }

//        publisher.publishFundList(mutualFundList);
    }



}
