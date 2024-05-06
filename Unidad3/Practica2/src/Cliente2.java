import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente2 {
    private final String HOST_SERVIDOR = "localhost";
    private final int NUM_PUERTO_SERVIDOR = 6000;
    private Socket cliente;
    /**
     * El constructor abre la conexión con el servidor
     */
    public Cliente2() {
        try {
            cliente = new Socket(HOST_SERVIDOR,NUM_PUERTO_SERVIDOR);
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }
    }

    /**
     * Cierra la conexión con el servidor
     */
    public void cerrar(){
        try {
            cliente.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Abre el flujo de entrada
     * Recibe e imprime el número recibido del servidor
     */
    public void recibirNumero(){
        try(BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));) {
            String mensaje = null;
            while ((mensaje = flujoEntrada.readLine()) != null){
                System.out.println(mensaje);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }



    /**
     * Crea UN cliente, recibe los datos del servidor, y lo cierra
     * Probad a ejecutarlo tantas veces como clientes hayáis indicado al servidor.
     * Y alguna más para ver qué pasa.
     */
    public static void main(String[] args) {
        Cliente2 cliente = new Cliente2();
        cliente.recibirNumero();
        cliente.cerrar();
    }
}
