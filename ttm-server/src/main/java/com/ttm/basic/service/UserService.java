package com.ttm.basic.service;

import com.ttm.basic.dal.model.User;

/**
 * Created by liguoqing on 2016/4/27.
 */
public interface UserService extends BaseService<User>{

    public User userLogin(String userName, String userPassword);


}
