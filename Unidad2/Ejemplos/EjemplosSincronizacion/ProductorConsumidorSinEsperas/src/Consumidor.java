public class Consumidor extends Thread{
    private Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
        setName("Consumidor");
    }

    @Override
    public void run() {
        int valor = 0;
        for (int i = 0; i < Main.ITERACIONES; i++) {
            valor = cola.get();
            System.out.println(i + " " + currentThread().getName() +
                    ", consume: " + valor);

            //NO HACEMOS PAUSA.
            //El Consumidor será más rápido que el Productor
        }
    }
}
