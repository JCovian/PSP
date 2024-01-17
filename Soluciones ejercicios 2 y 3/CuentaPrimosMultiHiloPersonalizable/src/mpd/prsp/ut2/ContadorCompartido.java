package mpd.prsp.ut2;

public class ContadorCompartido {
    //Contador para cualquier cosa. En  este ejemplo, para contar primos
    private int contador = 0;

    /**
     * Contabiliza el valor indicado en el contador
     * Sincronizado para que no haya "condiciones de carrera". Se ejecutará de forma atómica
     */
    //Descomentar el modificador synchronized para comprobar el fallo en el resultado
    //synchronized
    public void contabilizar(int cuantos) {
        contador += cuantos;
    }

    public int getContador() {
        return contador;
    }
}
