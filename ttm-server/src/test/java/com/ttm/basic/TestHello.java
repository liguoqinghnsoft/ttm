package com.ttm.basic;


import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by liguoqing on 2016/4/12.
 */
public class TestHello {

    static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(147)|(17[0,8])|(18[^4,\\D]))\\d{8}$";
    static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Test
    public void dealSpace() {
        String email = "4 45222574 @qq.com ";
        email = email.replaceAll("\\s", "");
        System.out.println("email:" + email + "!");
        String blank = " ";
        Assert.assertEquals(true, StringUtils.isEmpty(blank));
    }

    @Test
    public void testTime() {
        Time time = new Time(14, 27, 0);
        System.out.println(time.getTime());
        System.out.println(LocalTime.now().isAfter(time.toLocalTime()));
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
    }

    @Test
    public void testTelephone() {
        System.out.println(Pattern.matches(REGEX_MOBILE, "13592393823"));
    }

    @Test
    public void testEmail() {
        System.out.println(Pattern.matches(REGEX_EMAIL, "liguoqing@jk.cn"));
    }

    @Test
    public void testBigForeach() throws Exception {
        List<String> snList = new ArrayList<>();
        List<Person> personList = new ArrayList<Person>();
        String[] f = new String[]{"peter", "anni", "pig", "nomal", "text", "anni"};
        for (int i = 0; i < f.length; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setFirstName(f[i]);
            personList.add(person);
        }
        //personList.stream().filter(distinctByKey(p -> p.firstName)).forEach(p -> System.out.println(p.getFirstName()));
        Person persond = personList.stream().filter(i -> i.getAge() > 3).findFirst().orElseThrow(() -> new RuntimeException("Error"));
        System.out.println(persond);
        personList = personList.stream().filter(i -> {
            boolean flag = false;
            if (i.getAge() <= 3) {
                flag = true;
            }
            return flag;
        }).collect(Collectors.toList());

        personList.forEach(person -> {
            snList.add(person.getFirstName());
            System.out.println(person);
        });
        snList.forEach(sn -> {
            System.out.println(sn);
        });
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void jdk8() throws Exception {
//        List<Person> personList = new ArrayList<Person>();
//        long filterCount = personList.stream().filter(p -> p.getAge()>=2).count();
//        System.out.println("filterCount->"+filterCount);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        Assert.assertEquals(true, localDate.isLeapYear());
        System.out.println("year->" + localDate.getYear() + ",month->" + localDate.getMonthValue());
        System.out.println("isLeapYear->" + localDate.isLeapYear());
        System.out.println("lengthOfMonth->" + localDate.lengthOfMonth() + ",lengthOfYear->" + localDate.lengthOfYear());

        LocalTime localTime = LocalTime.parse("15:35:24");
        LocalDateTime localDateTime = localDate.atTime(localTime);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        localDateTime = localDateTime.plusDays(10);
        System.out.println("localDateTime plus 10->" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        System.out.println("dayOfWeek->" + localDateTime.getDayOfWeek().getValue()
                + ",dayOfMonth->" + localDateTime.getDayOfMonth()
                + ",dayOfYear->" + localDateTime.getDayOfYear());

        Temporal startTemporal = Instant.now();
        Thread.sleep(2000);
        Temporal endTemporal = Instant.parse(Instant.ofEpochMilli(System.currentTimeMillis()).toString());
        Duration duration = Duration.between(startTemporal, endTemporal);
        System.out.println("days->" + duration.toDays() + ",hours->" + duration.toHours()
                + ",minutes->" + duration.toMinutes() + ",seconds->" + duration.getSeconds());


    }

    @Test
    public void testlML() {

//        Map<Long,Long> ml = new HashMap<>();
//        ml.put(1l,1l);
//        ml.put(2l,2l);
//        Iterator iterator = ml.keySet().iterator();
//        while (iterator.hasNext()){
//            long key = (long)iterator.next();
//            System.out.println(key+"="+ml.get(key));
//        }

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setAge(i % 3);
            person.setFirstName("firstName-" + i);
            person.setLastName("lastName-" + i);
            persons.add(person);
        }
        Map<Integer, List<Person>> mps = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        Iterator iterator = mps.keySet().iterator();
        while (iterator.hasNext()) {
            int key = (int) iterator.next();
            List<Person> ps = mps.get(key);
            System.out.println(key + "=" + ps);
        }
    }

    @Test
    public void testlTL() {
        Long[] source = new Long[]{1l, 2l, 3l, 4l, 10l, 9l, 8l, 7l, 6l, 5l};
        Arrays.asList(source).stream().forEach(i -> System.out.println(i));

//        String[] target = new String[]{"10","12","23","34"};
//        List<String> targets = Arrays.asList(target);
//        List<Long> results = targets.stream().map(i -> Long.valueOf(i)).collect(Collectors.toList());
//        Object[] rest = results.toArray();
//        for(Object rst:rest){
//            System.out.println((Long)rst);
//        }
    }

    private Long idGenerator = Long.valueOf(0L);

    public synchronized String getNextId(String s, String s2, int i) {
        ArrayList Ids = new ArrayList();

        for (int sb = 0; sb < i; ++sb) {
            while (this.idGenerator.longValue() / 10L % 10L != 0L || this.idGenerator.longValue() / 1000L % 10L != 0L) {
                this.idGenerator = Long.valueOf(this.idGenerator.longValue() + 1L);
            }

            Ids.add(this.idGenerator);
            this.idGenerator = Long.valueOf(this.idGenerator.longValue() + 1L);
        }

        StringBuilder var8 = new StringBuilder();
        Iterator out = Ids.iterator();

        while (out.hasNext()) {
            Long id = (Long) out.next();
            var8.append(id).append(',');
        }

        String var9 = var8.substring(0, var8.length() - 1);
        return var9;
    }

    class Person {
        public Person() {
            super();
        }

        private int age;
        private String firstName;
        private String lastName;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

}
