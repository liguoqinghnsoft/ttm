package com.ttm.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * Created by liguoqing on 2016/4/26.
 */
@Configuration
@EnableReactor
public class ReactorConfig {

    @Bean(name="rootReactor")
    public Reactor rootReactor(Environment env){
        Reactor reactor = Reactors.reactor().env(env).get(); //.dispatcher(env.THREAD_POOL).get();
        return reactor;
    }

}
