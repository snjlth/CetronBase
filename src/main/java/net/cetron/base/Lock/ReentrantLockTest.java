package net.cetron.base.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    //https://www.cnblogs.com/xiaoxi/p/9140541.html
    int num;
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    public static void main(String[] args) {


    }

    private void add(){
        ReentrantReadWriteLock.WriteLock lock = reentrantReadWriteLock.writeLock();
        num++;
        lock.unlock();
    }
}
