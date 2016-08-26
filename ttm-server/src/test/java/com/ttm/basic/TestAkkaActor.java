package com.ttm.basic;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liguoqing on 2016/8/10.
 */
public class TestAkkaActor {

    private static ActorSystem actorSystem;
    private static ActorRef actorRef;

    @Before
    public void before(){
        actorSystem = ActorSystem.create("akka-actor-test");
        actorRef = actorSystem.actorOf(Props.create(AkkaHello.class));
    }

    @After
    public void after(){
        actorSystem.shutdown();
    }

    @Test
    public void testActor(){
        actorRef.tell("Akka", ActorRef.noSender());
    }

    private static class AkkaHello extends UntypedActor{

        @Override
        public void onReceive(Object o) throws Throwable {
                 if(o instanceof String){
                     System.out.println("receive->"+o);
                 }
        }
    }

}
