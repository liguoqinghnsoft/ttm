package com.ttm.basic;

import com.ttm.basic.dal.model.User;
import com.ttm.basic.exception.GlobalException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by liguoqing on 2016/5/30.
 */
public class TestOptional {

    @Test
    public void testOptional(){
//        Optional<String> optional = Optional.of("123");
//        System.out.println(optional.get());
//        optional = Optional.of(null);
//        System.out.println(optional.get());

//        int i = -00000001;
//        i = i >> 2;
//        System.out.println(i);

//        Optional<User> user =  Optional.ofNullable(new User());
//        System.out.println(user.isPresent());

        List<User> users = new ArrayList<>();
        users.stream().filter(i -> i.getPkId() >0 ).findAny().orElseThrow(() -> new GlobalException("Exception..."));


    }

    @Test
    public void testLambda(){
        List<String> list = Arrays.asList("123","abc","cdx","232");
        Collections.sort(list,(a,b)->{return b.compareTo(a);});
        list.forEach(a->{System.out.println(a);});

//        IPerson<String,Integer> ip = Integer::valueOf;
//        int result = ip.convert("123X");
//        System.out.println(result);


    }

    @Test
    public void testFork(){
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        //System.out.println(forkJoinPool.invoke(new TestFork()));
        int start = 1,end = 100;
        try {
            System.out.println(""+start+"->"+end+" sum=" + forkJoinPool.submit(new TestFork(start, end)).get());
            System.out.println("stealCount="+forkJoinPool.getStealCount()+",activeThreadCount="+forkJoinPool.getActiveThreadCount()+",parallelism="+forkJoinPool.getParallelism());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class TestFork extends RecursiveTask<Integer>{
        private static final long serialVersionUID = -1580525412387470003L;

        private int start;
        private int end;
        public TestFork(){}
        public TestFork(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if(end - start <= 2){
                for(int i=start;i<=end;i++){
                    sum += i;
                }
            }else{
                int middle = (end + start) / 2;
                TestFork rTestFork = new TestFork(middle+1,end); //6,10 ,4,5
                TestFork lTestFork = new TestFork(start,middle);//
                System.out.println("middle="+middle+",start="+start+",end="+end);
                rTestFork.fork();
                lTestFork.fork();
                int rS = rTestFork.join();
                int lS = lTestFork.join();
                sum = rS + lS;
            }
            return sum;
        }
    }

    private Long idGen = new Long(5000L);

    @Test
    public void testIDGen(){
        ArrayList Ids = new ArrayList();
        for(int i=0;i<5000;i++){
            while(this.idGen.longValue() ==0L || this.idGen.longValue() /10L%10L !=0L || this.idGen.longValue() /100L%10L !=0L){
                this.idGen = Long.valueOf(this.idGen.longValue() + 1L);
            }
            Ids.add(this.idGen);
            this.idGen = Long.valueOf(this.idGen.longValue() + 1L);
        }
        Ids.forEach(v->System.out.println(v));
    }

}
