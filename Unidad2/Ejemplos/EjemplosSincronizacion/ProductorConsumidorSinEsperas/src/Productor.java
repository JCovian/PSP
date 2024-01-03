public class Productor extends Thread{
    private Cola cola;

    public Productor(Cola cola) {
        this.cola = cola;
        setName("Productor");
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.ITERACIONES; i++) {
            cola.put(i);
            System.out.println(i + " " + currentThread().getName() +
                    ", produce: " + i);
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
