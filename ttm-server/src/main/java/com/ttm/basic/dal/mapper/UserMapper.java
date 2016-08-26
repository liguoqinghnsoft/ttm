package com.ttm.basic.dal.mapper;

import com.ttm.basic.dal.model.User;

/**
 * Created by liguoqing on 2016/4/27.
 */
public interface UserMapper extends BaseMapper<User>{

        public User login(User user);

}
