package com.ttm.basic;

import com.ttm.basic.api.service.UserHttpService;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by liguoqing on 2017/1/26.
 */
public class TestSPI {

    @Test
    public void testHttpService(){
        ServiceLoader<UserHttpService> services = ServiceLoader.load(UserHttpService.class);
        Iterator<UserHttpService> iterator = services.iterator();
        System.out.println("classpath:"+System.getProperty("java.class.path"));
        while(iterator.hasNext()){
            UserHttpService userHttpService = iterator.next();
            userHttpService.hello();
        }
    }

}
