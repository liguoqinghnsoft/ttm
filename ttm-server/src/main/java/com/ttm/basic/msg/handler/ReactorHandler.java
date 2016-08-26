package com.ttm.basic.msg.handler;

import com.ttm.basic.api.dto.ReactorDTO;
import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

/**
 * Created by liguoqing on 2016/4/18.
 */
@Consumer
public class ReactorHandler {

    @Selector(value = "TEST_REACTOR",reactor = "@rootReactor")
    public  void handlerDTO(Event<ReactorDTO> event){
        ReactorDTO reactorDTO = event.getData();
        System.out.println("handler-->>>>"+reactorDTO);
    }


}
