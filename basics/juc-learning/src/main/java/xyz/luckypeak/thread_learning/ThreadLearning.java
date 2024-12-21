package xyz.luckypeak.thread_learning;

public class ThreadLearning {

    public static void main(String[] args) throws InterruptedException {
        // 新建线程1
        Thread t1 = new Thread();
        t1.start();

        // 新建线程2
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Hello, I am t2");
            }
        };
        t2.start();

        // 新建线程3
        Thread t3 = new Thread(new CreateThread3());
        t3.start();

        // 新建线程4
        Thread t4 = new Thread(() -> {
            System.out.println("Hello, I am t4");
        });
        t4.start();

        Thread.sleep(1000);
    }

    public static class CreateThread3 implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello, I am t3");
        }
    }

}
