package com.ttm.basic;

import com.ttm.basic.drools.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liguoqing on 2016/6/30.
 */
public class TestDrools01 {

    @Test
    public void test01_01(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        MyDrools drools = new MyDrools("Mr Li",18);
        //Assert.assertTrue(drools.getValid());

        //kieSession.execute(drools);
        //Assert.assertFalse(drools.getValid());
    }

    @Test
    public void test01_02(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        MyDrools drools = new MyDrools("Mr Li",20);
        TwoDrools twoDrools = new TwoDrools();
        twoDrools.setDateApply(new Date());
        System.out.println(twoDrools.getValid());
        kieSession.execute(Arrays.asList(new Object[]{drools,twoDrools}));
        System.out.println(twoDrools.getValid());
    }

    @Test
    public void test01_03(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSession kieSession = kieContainer.newKieSession();
        String[] roomNames = new String[]{"kitchen","bedroom","office","living room"};
        FactHandle fireHandler = null;
        Sprinkler sprinkler = null;
        for(String name:roomNames) {
            Room room = new Room(name);
            kieSession.insert(room);
            if("kitchen".equals(name)) {
                sprinkler = new Sprinkler(room,false);
                kieSession.insert(sprinkler);
                Fire fire = new Fire(room);
                fireHandler = kieSession.insert(fire);
            }
        }
        List list = new ArrayList<>();
        kieSession.setGlobal("globalList",list);
        kieSession.fireAllRules();
        list.forEach(i -> System.out.println("list->"+i));
        kieSession.delete(fireHandler);
        kieSession.fireAllRules();
    }


}
