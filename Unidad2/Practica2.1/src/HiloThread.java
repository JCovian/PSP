public class HiloThread extends Thread{
    private int limite;

    public HiloThread(int limite, String nombreHilo) {
        super(nombreHilo);
        this.limite = limite;
    }

    @Override
    public void run(){
        for (int i = 1; i <= limite; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

}
