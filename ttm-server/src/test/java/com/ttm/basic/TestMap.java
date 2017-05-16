package com.ttm.basic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguoqing on 2017/3/16.
 */
public class TestMap {

    @Test
    public void test01() {
        Map map = new HashMap<>();
        map.put("01", "v01");
        map.put("02", "v02");

        map.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
    }

}
