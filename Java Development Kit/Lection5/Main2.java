public class Main2 {
    static Integer object = Integer.valueOf(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            object = object + 1;
            System.out.println((Thread.currentThread().getName()));
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(Thread.currentThread().getName());
        object = object + 1;
        System.out.println(object.intValue());
    }
}
