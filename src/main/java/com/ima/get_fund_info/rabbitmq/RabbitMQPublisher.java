package com.ima.get_fund_info.rabbitmq;

import com.ima.get_fund_info.model.MutualFund;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

@EnableBinding(RabbitMQBinding.class)
public class RabbitMQPublisher {

    private static final Logger logger = LogManager.getLogger(RabbitMQPublisher.class);

    @Autowired
    private RabbitMQBinding mqConfig;

    public void publishFundList(MutualFund mutualFund) throws IllegalStateException {
        logger.info("Publishing fund: " + mutualFund.getFundName());
        boolean successfully = mqConfig.publishFundList().send(MessageBuilder.withPayload(mutualFund).build());
        if (successfully) {
            logger.info("Fund List published successfully");
        } else {
            logger.error("Error publishing message.");
            throw new IllegalStateException("Notification Published Exception. " + mutualFund);
        }
    }
}