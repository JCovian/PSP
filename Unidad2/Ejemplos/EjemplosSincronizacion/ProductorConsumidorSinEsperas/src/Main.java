public class Main {
    public static final int ITERACIONES = 5;

    public static void main(String[] args) throws InterruptedException {
        Cola cola = new Cola();
        Productor p = new Productor(cola);
        Consumidor c = new Consumidor(cola);
        p.start();
        c.start();
    }

}
