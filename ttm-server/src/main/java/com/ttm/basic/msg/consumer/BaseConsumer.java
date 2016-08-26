package com.ttm.basic.msg.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by liguoqing on 2016/5/19.
 */
public abstract class BaseConsumer implements MessageListenerConcurrently {

    private DefaultMQPushConsumer pushConsumer;

    private String nameServerAddress;

    public void init() throws MQClientException {
        System.out.println("BaseConsumer init()");
//        pushConsumer.setNamesrvAddr(nameServerAddress);
//        pushConsumer.subscribe(getTopic(), getTags());
//        pushConsumer.setConsumerGroup("");
//        pushConsumer.registerMessageListener(this);
//        pushConsumer.start();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return null;
    }

    public abstract String getTopic();

    public abstract String getTags();

    public void setNameServerAddress(String nameServerAddress) {
        this.nameServerAddress = nameServerAddress;
    }
}
