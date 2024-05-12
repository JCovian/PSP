import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final int PUERTO = 6005;
    public static void main(String[] args) {
        try(Socket cliente = new Socket("localhost", PUERTO)) {
            String respuesta = null;
            Scanner stdInput = new Scanner(System.in);
            //Creamod flujos de entrada y salida
            BufferedReader entradaSocket = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(cliente.getOutputStream(), true);
            do{
                //Muestra pon consola mensaje solictando número
                System.out.print(entradaSocket.readLine());
                int num = stdInput.nextInt();
                //Envía número para comprobar si hemos acertado
                salidaCliente.println(num);
                respuesta = entradaSocket.readLine();
                //Muestra por consola la respuesta
                System.out.println(respuesta);
            }while(!respuesta.contains("Has acertado"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
