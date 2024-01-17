package mpd.prsp.ut2;

/**
 * Un hilo de esta clase contará el número de primos entre min y max.
 * Imprimirá el resultado en la salida estándar, con su id y el tiempo transcurrido
 */
public class CuentaPrimosHilo extends Thread{
        private int id;  // Id numérico para el hilo
        private long min; //Desde dónde busca primos
        private long max; //Hasta dónde busca primos

        //Cada hilo necesita una referencia a ese contador compartido,
        private ContadorCompartido contador;

        public CuentaPrimosHilo(int id, long min, long max, ContadorCompartido contador) {
            this.id = id;
            this.min = min;
            this.max = max;
            this.contador = contador;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            int numPrimos = UtilPrimos.cuentaPrimos(min,max, contador);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Hilo " + id + " cuenta " +
                    numPrimos + " primos entre " + min + " y " + max + " en " + (elapsedTime/1000.0) + " segundos.");
        }
}
