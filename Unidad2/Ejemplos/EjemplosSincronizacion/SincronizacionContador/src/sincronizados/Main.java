package sincronizados;

class Main {

    /**
     * En este paquete, los hilos utilizan la versión de Contador sincronizada
     * Esto garantiza que las operaciones incrementar y decrementar se realicen de forma atómica
     * devolviendo el resultado esperado
     */
    public static void main(String[] args) {
        ContadorSincro cont = new ContadorSincro(100);
        HiloA a = new HiloA("HiloA", cont);
        HiloB b = new HiloB("HiloB",cont);
        a.start();
        b.start();
    }
}
