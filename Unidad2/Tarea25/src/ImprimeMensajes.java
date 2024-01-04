public class ImprimeMensajes {
    private int contador = 1;

    public synchronized void mostrar(String mensaje, int orden) {
        // Lo utilizo para saber cuando llegué al último hilo
        if (contador > 3) { // Si el contador pasa de 3 le vuelve a tocar al hilo 1
            contador = 1;
        }
        // Espera a que le llegue el turno al hilo, mientras no coincida con contador queda en espera
        while (contador != orden) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Imprime el mensaje
        System.out.print(mensaje + " ");
        contador++; // Actualiza contador para que coincida con el siguiente orden de hilo
        // Notificamos al resto de hilos para darle paso al siguiente
        notifyAll();
    }
}
