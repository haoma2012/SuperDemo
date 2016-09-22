package com.example.superdemo.LoadImageLib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/29.
 * universal-image-loader
 */
public class UILDemo {

    private void test() {
        /**
         * 1
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }


    public class myThread extends Thread{

        @Override
        public void run() {
            super.run();
        }
    }


    private void ThreadPoolExecutor(){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,200, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(5));
    }


}
