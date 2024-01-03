public class Mensajes extends Thread {
    private String mensaje;
    private int repeticiones;
    private Object bloqueo;
    private int orden;

    public Mensajes(String mensaje, int repeticiones, Object bloqueo, int orden) {
        this.mensaje = mensaje;
        this.repeticiones = repeticiones;
        this.bloqueo = bloqueo;
        this.orden = orden;
    }

    @Override
    public void run() {
        synchronized (bloqueo) {

            for (int i = 1; i <= repeticiones; i++) {
                //Espera a que sea el turno del hilo
                //while(orden != 1) {
                //    bloqueo.wait();
                //}
                //Imprime el mensaje y notifica al siguiente hilo
                System.out.print(mensaje + " ");
                //orden = (orden % 3) +1;
                //System.out.print(orden + " ");
                bloqueo.notifyAll();
                try {
                    bloqueo.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            bloqueo.notifyAll();
        }
    }
}
