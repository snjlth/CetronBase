package net.cetron.base.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTaskTest {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ReportGenerator reportGenerator = new ReportGenerator();
        ReportGenerator reportGenerator1 = new ReportGenerator();
        executor.submit(reportGenerator);
        executor.submit(reportGenerator1);
        executor.shutdown();
    }


    public static class ReportGenerator implements Callable {
        public ReportGenerator(){}
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "-" + "Run");
            return null;
        }
    }
}
