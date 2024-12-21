package xyz.luckypeak.thread_learning;

public class DaemonLearning {

    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Hello, I am DaemonT");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT daemonT = new DaemonT();
        daemonT.setDaemon(true);
        daemonT.start();

        // Exception in thread "main" java.lang.IllegalThreadStateException
        // 	at java.base/java.lang.Thread.setDaemon(Thread.java:1412)
        // 	at xyz.luckypeak.thread_learning.DaemonLearning.main(DaemonLearning.java:23)
        // daemonT.setDaemon(true);

        Thread.sleep(3000);
    }

}
