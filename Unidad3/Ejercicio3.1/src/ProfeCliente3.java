import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProfeCliente3 {
    private static int PUERTO = 6000;
    public static void main(String[] args) {
        try (Socket cliente = new Socket("localhost",PUERTO)) {
            String respuesta = null;
            Scanner stdInput = new Scanner(System.in);
            BufferedReader entradaSocket = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(cliente.getOutputStream(), true);
            do{
                System.out.println(entradaSocket.readLine());
                int num = stdInput.nextInt();
                salidaCliente.println(num);
                respuesta = entradaSocket.readLine();
                System.out.println(respuesta);
            }while(!respuesta.equals("Has acertado!"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
