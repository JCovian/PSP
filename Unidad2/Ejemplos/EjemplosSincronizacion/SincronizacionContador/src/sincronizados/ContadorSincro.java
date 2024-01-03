package sincronizados;

/**
 * En esta versión de Contador, las funciones incrementar y decrementar son synchronized
 * Esto produce dos efectos:
 * 1 - Cuando un hilo ejecuta un método sincronizado de un objeto, todos los demás subprocesos que invocan métodos sincronizados para el mismo objeto
 * se bloquean (suspenden la ejecución) hasta que el primer subproceso termina con el objeto.
 * 2 - El valor a la salida del método estará actualizado en cualquier acceso de otro hilo al mismo objeto
 */
class ContadorSincro {
    private int c = 0;

    public ContadorSincro(int c){
        this.c = c;
    }

    public synchronized void incrementar() {
        c++;
    }

    public synchronized void decrementar() {
        c--;
    }

    public int getValor() {
        return c;
    }

}
