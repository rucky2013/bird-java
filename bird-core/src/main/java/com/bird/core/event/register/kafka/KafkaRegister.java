package com.bird.core.event.register.kafka;

import com.bird.core.event.arg.IEventArg;
import com.bird.core.event.register.IEventRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka事件注册器，向kafka队列中push消息
 */
@Component
public class KafkaRegister implements IEventRegister {

    @Autowired(required = false)
    private KafkaTemplate<String,IEventArg> kafkaTemplate;

    /**
     * 事件注册
     *
     * @param eventArg 事件参数
     */
    @Override
    public void regist(IEventArg eventArg) {
        kafkaTemplate.send(getTopic(eventArg),eventArg);
    }

    /**
     * 获取kafka的topic
     *
     *
     * @param eventArg
     * @return topic
     */
    private String getTopic(IEventArg eventArg){
        return eventArg.getClass().getName();
    }
}
