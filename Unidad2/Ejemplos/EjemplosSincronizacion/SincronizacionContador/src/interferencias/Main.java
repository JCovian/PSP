package interferencias;

class Main {
    /**
     * Teóricamente hiloA e hiloB deberían anularse (uno suma 300, y el otro resta 300)
     * y contador quedarse con el valor 100 original al final
     * Pero no suele ser así. El resultado es impredecible y depende del orden de cada operación individual
     * Distintas ejecuciones darán distintos resultados
     */
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA a = new HiloA("HiloA", cont);
        HiloB b = new HiloB("HiloB",cont);
        a.start();
        b.start();
    }

    /*
    Un ejemplo secuencia de interferencia:
        Hilo A: Recupera el valor c.
        Hilo B: Recupera el valor c.
        Hilo A: Incrementa el valor; El resultado es 101
        Hilo B: Incrementa el valor; El resultado es 99
        Hilo A: Almacena 101 en c
        Hilo B: Almacena 99 en c;
        c se queda con el valor 99. El resultado del HiloA se ha perdido.
     */
}
