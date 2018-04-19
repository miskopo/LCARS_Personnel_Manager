package cz.muni.fi.pv168;

public class MyCounter implements Runnable {
    private int remainder = 0;
    private static Object lock = new Object();
    private static int c = 0;

    public MyCounter(int remainder) {
        this.remainder = remainder;
    }

    public void run() {
        while (c < 51) {
            synchronized (lock) {
                while (c % 3 != remainder) {
                    try {
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " : " + c);
                if (c == 50) {
                    return;
                }
                c++;
                for (long i = 0; i < 1000000; i++);
                lock.notifyAll();
            }
        }
    }
}