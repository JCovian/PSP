/**
 * Modela una panaderia con varias barras de pan
 * Para darle más emoción, lo hacemos con un array, sin controlar que sobrepasemos su tamaño o accedamos a un índice negativo
 * La lógica de las funciones añadir y vender es correcta. Hay que sincronizarlas
 */
//TODO
// Se requieren retoques y controles para que se respeten las condiciones del ejercicio en un sistema concurrente
public class Panaderia {
    private final int CAPACIDAD_MOSTRADOR = 5;
    private BarraPan[] mostrador = new BarraPan[CAPACIDAD_MOSTRADOR];
    private int barrasActuales = 0;

    /**
     * Añade una barra de pan al final del mostrador
     */
    public synchronized void añadirBarraMostrador(BarraPan barra) {
        while (barrasActuales == CAPACIDAD_MOSTRADOR) {
            try {
                wait();
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        mostrador[barrasActuales] = barra;
        barrasActuales++;
        notify();
        System.out.println("Añadida barra al mostrador: " + barra);
    }

    /**
     * Vende la última barra añadida y actualiza el contador
     */
    public synchronized void venderBarraPan() {
        while (barrasActuales == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        barrasActuales--;
        BarraPan vendida = mostrador[barrasActuales];
        notify();
        System.out.println("Vendida barra: " + vendida);
    }

}
