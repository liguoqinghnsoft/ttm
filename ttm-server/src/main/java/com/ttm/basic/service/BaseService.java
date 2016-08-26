package com.ttm.basic.service;


import com.ttm.basic.api.dto.PageModel;

import java.io.Serializable;

/**
 * Created by liguoqing on 2016/4/27.
 */
public interface BaseService<T> {

    public boolean saveOrUpdate(T model);

    public boolean delete(Serializable pkId);

    public PageModel query(int offset, int pageSize, T model);
}
