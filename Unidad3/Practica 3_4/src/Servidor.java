import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private final int NUM_PUERTO = 6000;
    private int id = 0;


    private ServerSocket servidor;

    public Servidor(){
        try {
            servidor = new ServerSocket(NUM_PUERTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void atenderClientes(){

        while(true){
            try {
                System.out.println("Esperando cliente.....");
                Socket cliente = servidor.accept();
                id++;
                CuentaPrimosHilo hilo = new CuentaPrimosHilo(id,cliente);
                hilo.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cerrarServidor(){

        try {
            servidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.atenderClientes();
        s.cerrarServidor();
    }
}
