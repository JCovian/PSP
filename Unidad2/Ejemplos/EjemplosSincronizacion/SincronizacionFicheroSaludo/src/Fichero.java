import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fichero {
    private File fichero;
    //Para este ejemplo es importante que el FileWriter tenga ámbito de clase, un atributo
    private FileWriter flujo;

    public Fichero(String nombreFichero) {
        this.fichero = new File(nombreFichero);
        //Nos aseguramos de empezar a escribir en un nuevo fichero
        fichero.delete();
    }

    /**
     * En cada llamada:
     * 1 - Abre el flujo para añadir contenido
     * 2 - Escribe en el flujo
     * 3 - Cierra el flujo
     *
     * PRUEBA: Comentad el modificador synchronized
     */
    public
    synchronized
    void insertarTexto(String texto){
        try{
            flujo = new FileWriter(fichero, true);
            flujo.write(texto);
            flujo.close();
        } catch (IOException e) {
            System.err.println("Excepción desde hilo " + Thread.currentThread().getName());
            throw new RuntimeException(e);
        }
    }
}
