package com.ima.get_fund_info.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitMQBinding {
    String INPUT_MQ_CHANNEL = "mq-isin";
    String OUTPUT_MQ_CHANNEL = "mq-funds";

    @Output(OUTPUT_MQ_CHANNEL)
    MessageChannel publishFundList();

    @Input(INPUT_MQ_CHANNEL)
    SubscribableChannel getIsinList();
}
