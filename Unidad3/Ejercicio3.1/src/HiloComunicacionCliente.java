import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloComunicacionCliente extends Thread{
    private Socket cliente;
    private int numSecreto;

    public HiloComunicacionCliente(Socket socketCliente, int numero) {
        cliente = socketCliente;
        numSecreto = numero;
    }

    @Override
    public void run() {
        //Para testear la aplicación
        //System.out.println("Num secreto: " + numSecreto);
        boolean esAcierto = false;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        //Recibe números y muestra resultado hasta que acierte el número
        try {
            while (!esAcierto) {
                flujoEntrada = new DataInputStream(cliente.getInputStream());
                flujoSalida = new DataOutputStream(cliente.getOutputStream());
                int num = flujoEntrada.readInt();
                System.out.println("Número recibido: " + num);
                if (num == numSecreto) {
                    flujoSalida.writeUTF("Has acertado");
                    esAcierto = true;
                } else flujoSalida.writeUTF((num < numSecreto ? "Tu número es menor" : "Tu número es mayor"));
            }
            //flujoEntrada.close();
            //flujoSalida.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
