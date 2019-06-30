package net.cetron.base.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class InnerLock implements Runnable {

    public volatile int num = 0;
    //https://www.cnblogs.com/zhengbin/p/5654805.html#_label1
    //https://blog.csdn.net/u012723673/article/details/80682208
    public /*volatile*/ boolean stop = false;

    public void stopMe(){
        stop = true;
    }

    public void run() {
        int i = 0;
        while (!stop){
            i++;
        }
        System.out.println("Stop:"+i);
    }

    public void print(){
        System.out.println(Thread.currentThread().getName()+":"+num);
    }

    public void tryStop(){
        if (stop == !stop){
            System.out.println("out!!!");
            System.exit(0);
        }
    }

    public void swapValue(){
        stop = !stop;
    }


    //lock method
    public synchronized boolean getStatus(){
        return stop;
    }

    //lock class
    public synchronized static boolean isStop(){
        return false;
    }

    public static void main(String[] args) {
//        try {
//            final InnerLock innerLock = new InnerLock();
//            new Thread(innerLock).run();
//            Thread.sleep(2000);
//            innerLock.stopMe();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        final InnerLock innerLock = new InnerLock();
        Thread mainThread = new Thread(){
          public void run(){
              System.out.println("Main Thread start!");
              while (true){
                  innerLock.tryStop();
              }
          }
        };
        mainThread.start();

        Thread swapThread = new Thread(){
            public void run(){
                System.out.println("swap value start!");
                while (true)
                innerLock.swapValue();
            }
        };
        swapThread.start();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    static class BlockQueue implements Runnable {

        private List<String> queueList = new ArrayList<String>();
        public synchronized String pop() throws InterruptedException {
            while (queueList.size() == 0){
                this.wait();
            }

            if(queueList.size() > 0){
                return  queueList.remove(0);
            }
            else{
                return null;
            }
        }

        public synchronized void put(String value){
            queueList.add(value);
            this.notify();
        }

        public static void main(String[] args) {

            BlockQueue blockQueue = new BlockQueue();
            for (int i = 0; i < 10; i++) {
                try {
                    blockQueue.pop();
                    blockQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            try {
                pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
