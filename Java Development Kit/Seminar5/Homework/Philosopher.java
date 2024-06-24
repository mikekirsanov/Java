public class Philosopher implements Runnable {
    private Object leftFork;
    private Object rightFork;
    private final int id;
    private int eatingCount = 0;

    public Philosopher(Object leftFork, Object rightFork, int id) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (eatingCount < 3) {
                think();
                synchronized (leftFork) {
                    doAction("берет левую вилку.");
                    synchronized (rightFork) {
                        doAction("берет правую вилку.");
                        eat();
                        doAction("кладет правую вилку.");
                    }
                    doAction("кладет левую вилку.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void think() throws InterruptedException {
        doAction("думает.");
        Thread.sleep(((int) (Math.random() * 100)));
    }

    private void eat() throws InterruptedException {
        eatingCount++;
        doAction("ест " + eatingCount + " раз.");
        Thread.sleep(((int) (Math.random() * 100)));
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                "Философ " + id + " " + action);
    }
}
