package selfstudy;

/**
 * Created by Alexander on 2016/6/17.
 */
public class Study08_Thread {
    public static void main(String[] args) {
        new RunnableThread();
        new NewThread();
    }
}

class RunnableThread implements Runnable {
    Thread thread;

    public RunnableThread() {
        thread = new Thread(this, "demo thread RunnableThread");
        System.out.println("Child thread " + thread);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Child thread RunnableThread run.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println("Child thread RunnableThread resume.");
    }
}

class NewThread extends Thread {
    public NewThread() {
        super("demo thread NewThread");
        System.out.println("Child thread " + this);
        start();
    }

    @Override
    public void run() {
        System.out.println("Child thread newThread run.");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        System.out.println("Child thread newThread resume.");
    }
}