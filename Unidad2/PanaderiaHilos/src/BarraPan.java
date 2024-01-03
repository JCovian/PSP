/**
 * Modela una barra de pan, con un Tipo y un peso en Kgs
 */
public class BarraPan {
    private static int contadorBarras = 1;
    private int idBarra;



    public BarraPan() {
        this.idBarra = contadorBarras++;
    }

    @Override
    public String toString() {
        return "Barra " + idBarra;
    }
}
