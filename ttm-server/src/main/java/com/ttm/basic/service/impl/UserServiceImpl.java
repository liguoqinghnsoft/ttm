package com.ttm.basic.service.impl;

import com.ttm.basic.api.dto.PageModel;
import com.ttm.basic.dal.mapper.UserMapper;
import com.ttm.basic.dal.model.User;
import com.ttm.basic.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liguoqing on 2016/4/27.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public boolean saveOrUpdate(User model) {
        model.setUpdateTime(new Date());
        boolean flag = model.getPkId() > 0 ? true:false;
        if (null != model.getPkId() && model.getPkId() > 0) {
            userMapper.update(model);
        } else {
            model.setCreateTime(new Date());
            userMapper.insert(model);
        }
        throw new RuntimeException("自定义异常");
        //return true;
    }

    @Override
    @Transactional
    public boolean delete(Serializable pkId) {
        userMapper.delete(pkId);
        return true;
    }

    @Override
    public PageModel query(int offset,int pageSize,User user) {
        Map<String, Object> params = new HashMap<>();
        objectReflectMap(params,user.getClass());
        params.put("offset",(offset - 1) * pageSize);
        params.put("pageSize",pageSize);
        int totalRecords = (int)userMapper.count(params);
        List<User> lists = userMapper.select(params);
        PageModel pageModel = new PageModel(offset,pageSize,totalRecords);
        pageModel.setData(lists);
        return pageModel;
    }

    @Override
    public User userLogin(String userName, String userPassword) {
        User user = new User();
        user.setUserName(userName);
        user.setIdentityCardNo(userPassword);
        return userMapper.login(user);
    }

}
