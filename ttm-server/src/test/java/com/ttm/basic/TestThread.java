package com.ttm.basic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liguoqing on 2017/5/24.
 */
public class TestThread {

    @Test
    public void testExecuteService() throws Exception {
        List<Future<String>> futureList = Lists.newArrayList();
        ExecutorService service = Executors.newCachedThreadPool();
        futureList.add(service.submit(new _Runnable()));
        for (Future<String> future : futureList) {
            //if (future.isDone()) {
            System.out.println(future.get());
            // }
        }
        service.shutdown();
    }

    @Test
    public void testCount() throws Exception {
        List<Future<Integer>> futureList = Lists.newArrayList();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 1; i < 50; i++) {
            futureList.add(service.submit(new _Runnables(i)));
        }
        int total = 0;
        for (Future<Integer> future : futureList) {
            total += future.get();
        }
        service.shutdown();
        System.out.println("total=" + total);
    }

    class _Runnable implements Callable<String> {
        @Override
        public String call() {
            return "callable";
        }
    }

    class _Runnables implements Callable<Integer> {
        int seed = 0;

        public _Runnables() {
        }

        public _Runnables(int i) {
            this.seed = i;
        }

        @Override
        public Integer call() {
            if (seed % 5 == 0) {
                return seed;
            }
            return seed + (seed - 1);
        }
    }

}
