package com.ttm.basic.dal.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by liguoqing on 2016/4/27.
 */
public interface BaseMapper<T> {

    public void insert(T model);

    public void update(T model);

    public void delete(Serializable pkId);

    public List<T> select(Map<String, Object> params);

    public long count(Map<String, Object> params);

}
