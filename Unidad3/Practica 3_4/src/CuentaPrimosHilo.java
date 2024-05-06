import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Un hilo de esta clase contará el número de primos entre min y max.
 * Imprimirá el resultado en la salida estándar, con su id y el tiempo transcurrido
 */
public class CuentaPrimosHilo extends Thread{

        private final String TOKEN_FIN = "  fin  ";
        private long min; //Desde dónde busca primos
        private long max; //Hasta dónde busca primos
        private int id;

        private final Socket cliente;

        //Flujos de comunicación con el cliente
        private Scanner entrada;
        private PrintWriter salida;


        public CuentaPrimosHilo(int id, Socket cliente) {
            this.id = id;
            this.cliente = cliente;
            try{
                entrada = new Scanner(cliente.getInputStream());
                salida = new PrintWriter(cliente.getOutputStream(), true);
            }catch (IOException ignored){}
        }

        @Override
        public void run() {
            pedirNumeros();
            long startTime = System.currentTimeMillis();
            int numPrimos = UtilPrimos.cuentaPrimos(min,max);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            salida.println("Hilo " + id + " cuenta " + numPrimos + " primos entre " + min + " y " + max
                            + " en " + (elapsedTime/1000.0) + " segundos " + TOKEN_FIN);
            cerrarSocket();
        }

    private void cerrarSocket() {
        try {
            cliente.close();
        } catch (IOException ignored) {}
    }

    /**
     * Pedimos los números hasta que pasen las validaciones del enunciado
     */
    private void pedirNumeros() {
            int limiteInferior, limiteSuperior;

            do{
                salida.println("Introduce el límite inferior: ");
                limiteInferior = entrada.nextInt();
                salida.println("Introduce el límite superior: ");
                limiteSuperior = entrada.nextInt();
            }while((limiteInferior <=1) || !(limiteSuperior>limiteInferior));

            min = limiteInferior;
            max = limiteSuperior;
    }
}
