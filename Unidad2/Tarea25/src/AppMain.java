/*
 * Programa usando hilos:
 * - Hilo 1 muestra 50 veces la palabra PIN
 * - Hilo 2 muestra 50 veces la palabra PAN
 * - Hilo 3 muestra 50 veces la palabra PUN
 * Usando notificaciones conseguir mostrar por pantalla:
 * PIN PAN PUN PIN PAN PUN PIN PAN PUN...
 */

public class AppMain {
    public static void main(String[] args) {
        // Crea una instancia de un objeto compartido entre los hilos y así los puedo bloquear
        ImprimeMensajes imprimeMensajes = new ImprimeMensajes();

        //Crea las instancias de las clases que extienden Thread (los hilos)
        Mensajes hilo1 = new Mensajes(imprimeMensajes,"PIN",50,1);
        Mensajes hilo2 = new Mensajes(imprimeMensajes,"PAN",50,2);
        Mensajes hilo3 = new Mensajes(imprimeMensajes,"PUN",50,3);

        //Inicia los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
