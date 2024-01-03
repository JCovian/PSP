public class Vendedor extends Thread{
    private final int ITERACIONES = 50;
    private Panaderia panaderia;

    public Vendedor(Panaderia panaderia) {
        super("Vendedor");
        this.panaderia = panaderia;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERACIONES; i++) {
            panaderia.venderBarraPan();
        }
    }
}
