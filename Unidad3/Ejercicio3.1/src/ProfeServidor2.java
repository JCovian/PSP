import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ProfeServidor2 {
    private static final int PUERTO = 6000;
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO);){
            int numInventado = inventarNumero();
            int numIntroducido = 0;
            System.out.println("A la espera de un jugador...");
            try(Socket cliente = servidor.accept();) {
                //Creamos los flujos de entrada y salida
                Scanner entradaCliente = new Scanner(cliente.getInputStream());
                PrintWriter salidaCliente = new PrintWriter(cliente.getOutputStream(), true);
                do{
                    salidaCliente.println("Introduce un número: ");
                    numIntroducido = entradaCliente.nextInt();
                    if(numInventado > numIntroducido){
                        salidaCliente.println("Tu número es menor");
                    }else if (numInventado < numIntroducido){
                        salidaCliente.println("Tu número es mayor");
                    } else {
                        salidaCliente.println("Has acertado!");
                    }
                } while(numInventado != numIntroducido);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int inventarNumero() {
        return (int) (Math.random() * 100);
    }
}
