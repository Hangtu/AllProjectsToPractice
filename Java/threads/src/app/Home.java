package app;

class Number {

    int c;

    public synchronized void incremenet() {
        c++;
    }

    public synchronized void getC() {
        System.out.println(this.c);
    }
}

public class Home {
    public static void main(String[] args) throws Exception {

        Number num = new Number();

        Runnable obj1 = () -> {
            for (int i = 0; i < 1000; i++) {
                // System.out.println("1");
                num.incremenet();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {

                }
            }
        };

        Thread th1 = new Thread(obj1);
        th1.start();

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                // System.out.println("2");
                num.incremenet();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {

                }
            }
        });

        th2.start();

        th1.join(); // waits for this thread to die
        th2.join();

        // afters joins
        System.out.println("Last");
        System.out.println(num.c);
    }
}