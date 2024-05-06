import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private final String TOKEN_FIN = "  fin  ";
    private final String HOST = "localhost";
    private final int NUM_PUERTO = 6000;
    private Socket cliente;

    public Cliente() {
        try {
            cliente = new Socket(InetAddress.getByName(HOST), NUM_PUERTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void comunicarConServidor() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            String leido;
            Scanner sc = new Scanner(System.in);
            //Vamos leyendo los recibido del cliente línea a línea
            while ((leido = entrada.readLine()) != null) {
                //Escribimos el mensaje por pantalla para que lo vea el usuario
                System.out.println(leido);

                if(!leido.contains(TOKEN_FIN)){
                    //Recojo la respuesta deel usuario introducida por teclado (System.in)
                    String respuesta = sc.nextLine();
                    //Traspasamos la respuesta al servidor
                    salida.println(respuesta);
                }
            }

        } catch (IOException ignored) {
        }

    }

    public void cerrar(){
        try {
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.comunicarConServidor();
        c.cerrar();
    }

}
