package mpd.prsp.ut2;
/*
 * Utilizo esta clase como acumulador de contadores de los
 * diferentes hilos
 */
public class CuentaPrimos {
    private long contador;
    public CuentaPrimos() {
        this.contador = 0;
    }

    public void acumulador(long totalPrimos){
        contador += totalPrimos;
    }

    public long numeroPrimos(){
        return contador;
    }
}
