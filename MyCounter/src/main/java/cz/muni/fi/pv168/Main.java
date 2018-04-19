package cz.muni.fi.pv168;

public class Main {

    public static void main(String[] args) {



        MyCounter runnable1 = new MyCounter(0);
        MyCounter runnable2 = new MyCounter(1);
        MyCounter runnable3 = new MyCounter(2);

        Thread t1 = new Thread(runnable1, "1");
        Thread t2 = new Thread(runnable2, "2");
        Thread t3 = new Thread(runnable3, "3");

        t1.start();
        t2.start();
        t3.start();
    }
}
