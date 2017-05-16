package com.ttm.basic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by liguoqing on 2017/3/16.
 */
public class TestList {

    @Test
    public void test01() {
        List<Hsptl1> hsptl1 = Lists.newArrayList();
        for (long i = 1001; i < 1010; i++) {
            Hsptl1 hsptl = new Hsptl1();
            hsptl.setId(i);
            hsptl.setName(i + "-Name");
            hsptl.setSpecialRank((int) i % 10);
            hsptl.setCommonRank((int) i / 10);
            hsptl1.add(hsptl);
        }

        Map map = Maps.newHashMap();
        List<Hsptl2> hsptl2 = Lists.newArrayList();
        for (long i = 1008; i < 1010; i++) {
            Hsptl2 hsptl = new Hsptl2();
            hsptl.setHsptlId(i);
            hsptl.setHsptlName(i + "-Name");
            hsptl2.add(hsptl);
            map.put(i, hsptl);
        }

        hsptl1 = hsptl1.stream().filter(hsptl -> !map.containsKey(hsptl.getId())).collect(Collectors.toList());
        System.out.println(hsptl1);
        List<Hsptl1> result = Lists.newArrayList(hsptl1.get(0));
        int idx = 1;
        boolean endFlag = false;
        for (int i = 1; i < hsptl1.size(); ) {
            for (int j = 0; !endFlag && j < hsptl2.size(); ) {
                idx++;
                if (idx % 2 == 0 && i <= hsptl1.size() - 1) {
                    result.add(hsptl1.get(i++));
                } else {
                    result.add(new ModelConvert().convert(hsptl2.get(j++)));
                }
                if (j > hsptl2.size() - 1) endFlag = true;
            }
            if (endFlag && i <= hsptl1.size() - 1) {
                result.add(hsptl1.get(i++));
            }
        }
        System.out.println(result);
        int pageNo = 4,pageSize = 4;
        System.out.println(result.stream().skip(Long.valueOf((pageNo-1)*pageSize)).limit(Long.valueOf(pageSize)).collect(Collectors.toList()));
    }

    class Hsptl1 {
        private Long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        private String name;

        public int getSpecialRank() {
            return specialRank;
        }

        public void setSpecialRank(int specialRank) {
            this.specialRank = specialRank;
        }

        public int getCommonRank() {
            return commonRank;
        }

        public void setCommonRank(int commonRank) {
            this.commonRank = commonRank;
        }

        private int specialRank = 0;
        private int commonRank = 0;

        @Override
        public String toString() {
            return "Hsptl1{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", specialRank=" + specialRank +
                    ", commonRank=" + commonRank +
                    '}';
        }
    }

    class Hsptl2 {
        private Long hsptlId;
        private String hsptlName;

        public Long getHsptlId() {
            return hsptlId;
        }

        public void setHsptlId(Long hsptlId) {
            this.hsptlId = hsptlId;
        }

        public String getHsptlName() {
            return hsptlName;
        }

        public void setHsptlName(String hsptlName) {
            this.hsptlName = hsptlName;
        }

        @Override
        public String toString() {
            return "Hsptl2{" +
                    "hsptlId=" + hsptlId +
                    ", hsptlName='" + hsptlName + '\'' +
                    '}';
        }
    }

    class ModelConvert {

        Hsptl1 convert(Hsptl2 hsptl2) {
            Hsptl1 hsptl1 = new Hsptl1();
            hsptl1.setId(hsptl2.getHsptlId());
            hsptl1.setName(hsptl2.getHsptlName());
            hsptl1.setCommonRank(hsptl2.getHsptlId().intValue());
            hsptl1.setSpecialRank(hsptl2.getHsptlId().intValue());
            return hsptl1;
        }

    }
}
