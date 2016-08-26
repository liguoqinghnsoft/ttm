package com.ttm.basic.msg.consumer;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.ttm.basic.utils.TagType;
import com.ttm.basic.utils.TopicType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by liguoqing on 2016/5/19.
 */
@Component
public class BizOrderConsumer extends BaseConsumer{

    @Value("${rocketMq.nameServerAddress}")
    private String nameServerAddress;

    @Override
    @PostConstruct
    public void init() throws MQClientException{
        System.out.println("BizOrderConsumer init()");
        setNameServerAddress(nameServerAddress);
        super.init();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return super.consumeMessage(list, consumeConcurrentlyContext);
    }

    @Override
    public String getTopic() {
        return TopicType.Order.name();
    }

    @Override
    public String getTags() {
        return TagType.Order_Create.tag;
    }
}
