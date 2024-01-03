import java.util.concurrent.Semaphore;

/**
 * Modela una panaderia con varias barras de pan
 * Para darle más emoción, lo hacemos con un array, sin controlar que sobrepasemos su tamaño o accedamos a un índice negativo
 * La lógica de las funciones añadir y vender es correcta. Hay que sincronizarlas
 */
public class Panaderia {
    private final int CAPACIDAD_MOSTRADOR = 5;
    private BarraPan[] mostrador = new BarraPan[CAPACIDAD_MOSTRADOR];
    private Semaphore semaforoProductor = new Semaphore(CAPACIDAD_MOSTRADOR);
    private Semaphore semaforoConsumidor = new Semaphore(0);
    private int barrasActuales = 0;

    /**
     * Añade una barra de pan al final del mostrador
     */
    public void añadirBarraMostrador(BarraPan barra) {
        try {
            semaforoProductor.acquire();
        } catch (InterruptedException ignored) { }
        mostrador[barrasActuales] = barra;
        barrasActuales++;
        System.out.println("Añadida barra al mostrador: " + barra);
        if (barrasActuales == 5) {
            semaforoConsumidor.release(5);
        }
    }

    /**
     * Vende la última barra añadida y actualiza el contador
     */
    public void venderBarraPan() {
        try {
            semaforoConsumidor.acquire();
        } catch (InterruptedException ignored) { }
        barrasActuales--;
        BarraPan vendida = mostrador[barrasActuales];
        System.out.println("Vendida barra: " + vendida);
        if (barrasActuales == 0) {
            semaforoProductor.release(5);
        }
    }
}
