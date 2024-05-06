import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * PRÁCTICA 1
 * Crea un programa servidor que pueda atender N clientes, siendo N
 * un parámetro que el usuario introducirá por teclado.
 * Debe enviar a cada cliente un mensaje indicando el número de cliente que es.
 * El cliente mostrará el mensaje recibido por su salida estándar
 */
public class Servidor2 {
    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;
    private int clientesActuales = 0;
    private ServerSocket servidor;
    private int numClientes;

    /**
     * El constructor expone el puerto indicado
     * y almacena el número de clientes a aceptar
     */
    public Servidor2(int numClientes) {
        try {
            servidor = new ServerSocket(NUM_PUERTO);
            this.numClientes = numClientes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra el puerto a la escucha
     * @return true si se cierra correctamemte. False en otro caso
     */
    public boolean cerrar(){
        try {
            servidor.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Acepta a los clientes configurados y se comunica con cada uno de ellos
     */
    private void aceptaClientes() {
        for (int i = 1; i <= numClientes; i++) {
            try(Socket socketCliente = servidor.accept();){
                comunicarConCliente(socketCliente);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza el atributo clientesActuales y lo envía al cliente
     */
    private void comunicarConCliente(Socket cliente) {
        try(PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream());){
            clientesActuales++;
            flujoSalida.println("Cliente número " + clientesActuales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Orquesta el resto de funciones:
     *   - Pide número de clientes
     *   - Acepta conexiones para cada uno de ellos secuencialmente y les envía el dato
     *   - Cierra el servidor
     * @param args
     */
    public static void main(String[] args) {
        int numClientes = pideNumClientes();
        Servidor2 servidor = new Servidor2(numClientes);
        servidor.aceptaClientes();
        boolean estaCerrado = servidor.cerrar();
        System.out.println(estaCerrado ? "Servidor cerrado" : "No se pudo cerrar el servidor");
    }

    /**
     * Pide y devuelve el número de clientes a aceptar por el servidor
     */
    private static int pideNumClientes() {
        Scanner sc = new Scanner(System.in);
        int numCli = 0;
        boolean esEntero;

        System.out.print("Introduzca número de clientes: ");
        do {
            if (sc.hasNextInt()) {
                numCli = sc.nextInt();
                esEntero = true;
            } else {
                System.err.println("\nERROR no ha introducido un valor entero");
                esEntero = false;
                System.out.print("Introduzca número de clientes: ");
            }
        } while (!esEntero);
        return numCli;
    }
}
