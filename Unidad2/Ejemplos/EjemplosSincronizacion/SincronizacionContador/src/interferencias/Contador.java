package interferencias;

/**
 * Contador sin sincronización
 *
 * Si se hace referencia a un objeto Contador desde varios hilos
 * la interferencia entre ellos podría ocasionar comportamientos inesperados.
 *
 * La interferencia se da cuando dos operaciones en diferentes hilos actúan sobre los mismos datos, se intercalan.
 * Es decir, que las dos operaciones constan de múltiples pasos y las secuencias de pasos se superponen.
 */
class Contador {
    private int c = 0;

    public Contador(int c){
        this.c = c;
    }

    /**
     * c++ y c-- se descomponen en la JVM en 3 pasos:
     * 1 - recuperar el valor actual de c
     * 2 - incrementar/decrementar en 1 el valor de c
     * 3 - guardar el nuevo valor de c
     */
    public void incrementar() {
        c++;
    }

    public void decrementar() {
        c--;
    }

    public int getValor() {
        return c;
    }

}
