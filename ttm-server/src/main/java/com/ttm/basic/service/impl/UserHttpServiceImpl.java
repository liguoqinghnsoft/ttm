package com.ttm.basic.service.impl;

import com.ttm.basic.api.service.UserHttpService;
import org.springframework.stereotype.Service;

/**
 * Created by liguoqing on 2016/6/7.
 */
@Service("userHttpService")
public class UserHttpServiceImpl implements UserHttpService {

    @Override
    public void hello() {
        System.out.println("UserHttpServiceImpl->Hello");
    }
}
