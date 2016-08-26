package com.ttm.basic;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mvel2.MVEL;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liguoqing on 2016/6/27.
 */
public class TestMvel {

    @Test
    public void test01(){

        String tpls = "\\\"11\\\":\\\"DEFAULT^11\\\",\\\"22\\\":\\\"DEFAULT^22\\\",\\\"12\\\":\\\"DEFAULT^10\\\",\\\"13\\\":\\\"DEFAULT^13\\\",\\\"50\\\":\\\"DEFAULT^50\\\",\\\"40\\\":\\\"DEFAULT^40\\\",\\\"30\\\":\\\"DEFAULT^30\\\",\\\"20\\\":\\\"DEFAULT^20\\\",\\\"31\\\":\\\"DEFAULT^31\\\",\\\"10\\\":\\\"DEFAULT^10\\\",\\\"21\\\":\\\"DEFAULT^20\\\"";
        Map<String,List<String>> maps = Arrays.asList(StringUtils.split(tpls, "^")).stream().collect(Collectors.groupingBy(k -> StringUtils.substringBefore(k,"^")));
        maps.forEach((k,v) ->{
            System.out.println(k+"="+v);
        });


//        System.out.println(Long.valueOf("24"));
//
//        String expression = "fee > 100";
//        Map map = new HashMap<>();
//        map.put("fee",99);

//        User user = new User();
//        user.setPkId(100l);
//        map.put("fee",user.getPkId());
//        Assert.assertEquals(true, MVEL.eval(expression, map));

    }

    @Test
    public void getFee(){
        int coefficient = 100;
        long fee = 0l;
        String money = "0.537";
        BigDecimal bd = new BigDecimal(money);
        Double d = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*coefficient;
        fee = d.longValue();
        System.out.println(fee);
    }
}
