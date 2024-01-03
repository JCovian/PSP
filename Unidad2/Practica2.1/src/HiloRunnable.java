public class HiloRunnable implements Runnable{
    private int limite;

    public HiloRunnable(int limite) {
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int i = 1; i <= limite; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
