import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * PRÁCTICA 3
 * Crea un programa servidor que pueda atender N clientes, siendo N
 * un parámetro que el usuario introducirá por teclado.
 * Debe enviar a cada cliente un mensaje indicando el número de cliente que es.
 * El cliente mostrará el mensaje recibido por su salida estándar
 */
public class ServidorProf {
    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;
    private final int TAM_PAQUETE = 1024;
    private int clientesActuales = 0;
    private DatagramSocket servidor;
    private final int numClientes;


    /**
     * El constructor expone el puerto indicado mediante un socket udp
     * y almacena el número de clientes a aceptar
     */
    public ServidorProf(int numClientes){
        try {
            servidor = new DatagramSocket(NUM_PUERTO);
            this.numClientes = numClientes;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra el socket udp
     */
    public void cerrar(){
        servidor.close();
    }

    /**
     * Recibe un saludo de cada uno de los clientes configurados (en bucle)
     * Registrando su dirección y puerto
     * Responde a cada uno con su número de cliente
     */
    private void comunicaConClientes() {
        for (int i = 1; i <= numClientes; i++){
            byte[] buffer = new byte[TAM_PAQUETE];
            DatagramPacket recepcion = new DatagramPacket(buffer, buffer.length);
            System.out.println("A la espera de mensajes....");
            SocketAddress direccionCliente = recibirDeCliente(recepcion);
            enviarNumCliente(direccionCliente);
        }
    }

    /**
     * Recibe el InetSocketAddress con la dirección y puerto del socket de destino al que enviar su número de cliente
     * Incrementa el contador de clientes y lo envía el host de destino
     */
    private void enviarNumCliente(SocketAddress hostDestino) {
        clientesActuales++;
        byte[] mensajeEviar = ("Num cliente: " + clientesActuales).getBytes();
        try(DatagramSocket cliente = new DatagramSocket();) {
            DatagramPacket paqueteEnvio = new DatagramPacket(mensajeEviar, mensajeEviar.length, hostDestino);
            cliente.send(paqueteEnvio);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recibe el mensaje de saludo del cliente, lo imprime, y devuelve un SocketAddress con su direccion y puerto
     */
    private SocketAddress recibirDeCliente(DatagramPacket recepcion) {
        try {
            //Recibimos los posibles clientes. BLOQUEANTE!!!
            servidor.receive(recepcion);
            String mensajeRecibido = new String(recepcion.getData()).trim();
            System.out.println("Mensaje recibido: " + mensajeRecibido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return recepcion.getSocketAddress();
    }

    /**
     * Orquesta el resto de funciones:
     *   - Pide número de clientes
     *   - Se comunica con cada uno de ellos secuencialmente
     *   - Cierra el servidor
     */
    public static void main(String[] args) {
        ServidorProf servidor1 = new ServidorProf(pideNumClientes());
        servidor1.comunicaConClientes();
        servidor1.cerrar();
    }

    /**
     * Pide y devuelve el número de clientes a aceptar por el servidor
     */
    private static int pideNumClientes() {
        Scanner sc = new Scanner(System.in);
        boolean esValido = false;
        int numero = 0;
        System.out.print("Introduzca número de clientes: ");
        while (!esValido) {
            if(sc.hasNextInt()) {
                numero =  sc.nextInt();
                esValido = true;
            } else {
                esValido = false;
                System.err.println("\nERROR. El valor introducido no es válido");
                System.out.print("\nIntroduzca número de clientes: ");
            }
        }
        return numero;
    }
}
