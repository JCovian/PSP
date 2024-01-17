package mpd.prsp.ut2;

/**
 * Un hilo de esta clase contará el número de primos entre min y max.
 * Imprimirá el resultado en la salida estándar, con su id y el tiempo transcurrido
 */
public class CuentaPrimosHilo extends Thread{
    private int id;
    private int inicio;
    private int fin;
    private CuentaPrimos contador;

    public CuentaPrimosHilo(int id, int inicio, int fin, CuentaPrimos contador) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.contador = contador;
    }

    @Override
    public void run() {
        //Se toma la hora del sistema para saber cuando comienza el hilo
        long tiempoInicioHilo = System.currentTimeMillis();
        //Calcula cuantos primos hay en el rango que le corresponda al hilo
        long cantidadPrimos = UtilPrimos.cuentaPrimos(inicio, fin);
        //Se vuelve a tomar la hora del sistema para saber cuando finaliza el hilo
        long tiempoFinHilo = System.currentTimeMillis();
        long tiempoCalculado = tiempoFinHilo - tiempoInicioHilo;
        System.out.println("Hilo " + id + " cuenta " + cantidadPrimos + " primos entre "
            + inicio + " y " + fin + " en " + (tiempoCalculado / 1000.0)
            + " segundos");
        //Almacena en el contador la cantidad de primos que encontró en su rango
        contador.acumulador(cantidadPrimos);
    }
}
