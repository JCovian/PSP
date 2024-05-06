import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProfeCliente {
    private static int PUERTO = 6000;
    public static void main(String[] args) {
        try (Socket cliente = new Socket("localhost",PUERTO)) {
            String respuesta = null;
            Scanner stdInput = new Scanner(System.in);
            Scanner entradaSocket = new Scanner(cliente.getInputStream());
            PrintWriter salidaCliente = new PrintWriter(cliente.getOutputStream(), true);
            do{
                salidaCliente.println("Introduce un número: ");
                int num = stdInput.nextInt();
                salidaCliente.println(num);
                respuesta = entradaSocket.nextLine();
                System.out.println(respuesta);
            }while(!respuesta.equals("Has acertado"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
