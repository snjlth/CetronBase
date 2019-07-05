package net.cetron.base.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    //https://www.cnblogs.com/xiaoxi/p/9140541.html
    int num;
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    ReentrantLock reentrantLock = new ReentrantLock(false);

    public static void main(String[] args) {


    }

    private void add(){
        ReentrantReadWriteLock.WriteLock lock = reentrantReadWriteLock.writeLock();
        num++;
        lock.unlock();
    }
}
