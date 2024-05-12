import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo extends Thread {
    private Socket cliente;
    private int numSecreto;
    private int intentos;

    public Hilo(Socket socketCliente, int numeroSecreto) {
        cliente = socketCliente;
        numSecreto = numeroSecreto;
        intentos = 0;
    }

    @Override
    public void run() {
        int numeroIntroducido = 0;
        //BufferedReader entradaCliente = null;
        try {
            //Creamos flujos de entrada y salida
            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(cliente.getOutputStream(), true);
            do {
                salidaCliente.println("Introduce un número: ");
                numeroIntroducido = Integer.parseInt(entradaCliente.readLine());
                intentos++;
                if (numeroIntroducido > numSecreto) {
                    salidaCliente.println("El número secreto es menor");
                } else if (numeroIntroducido < numSecreto) {
                    salidaCliente.println("Tu número secreto es mayor");
                } else {
                    salidaCliente.println("Has acertado tras " + intentos + " intentos.");
                }
            } while (numeroIntroducido != numSecreto);
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
