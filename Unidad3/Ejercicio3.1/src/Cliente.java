import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jose Aquilino Covián Ornia
 * Practica 3.1
 * Crear una aplicación cliente/servidor usando sockets TCP en la que el servidor debe generar un número secreto
 * de forma aleatoria entre el 0 y el 100 (exc). El objetivo del cliente es solicitarle al usuario un número y enviarlo
 * al servidor hasta que adivine el número secreto. Para ello, el servidor para cada número que le envía el cliente le
 * indicará si es menor, mayor o es el número secreto.
 */

public class Cliente {
    private final String HOST_SERVIDOR = "localhost";
    private final int PUERTO_SERVIDOR = 6000;
    private final Socket cliente;

    /**
     * El constructor abre la conexión con el servidor
     */
    public Cliente() {
        try {
            cliente = new Socket(HOST_SERVIDOR,PUERTO_SERVIDOR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra la conexión con el servidor
     */
    private void cerrar() {
        try {
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abre el flujo de entrada y salida de datos con el servidor
     */
    public void comunicaServidor() {
        boolean esAcierto = false;
        //Mantenemos la comunicación con el servidor mientras adivinamos el número
        while (!esAcierto) {
            //Envía el número al servidor
            try {
                DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
                flujoSalida.writeInt(pideNumero());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Recibe la respuesta del servidor
            try {
                DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
                String mensaje = flujoEntrada.readUTF();
                if (mensaje.equals("Has acertado")) {
                    System.out.println(mensaje);
                    esAcierto = true;
                } else {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Pide al usuario un númer de entre 0 y 100 (exc.)
     */
    private int pideNumero() {
        boolean esValido = false;
        Scanner sc = new Scanner(System.in);
        int num = -1;

        System.out.print("Introduzca un número entre 0 y 99: ");
        while(!esValido)  {
            if(sc.hasNextInt()) {
                num = sc.nextInt();
                esValido = true;
            } else {
                System.err.println("ERROR, debe introducir un número entero\n");
                esValido = false;
                System.out.print("Introduzca un número entre 0 y 99: ");
                sc.nextLine();
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.comunicaServidor();
        cliente.cerrar();
    }
}
