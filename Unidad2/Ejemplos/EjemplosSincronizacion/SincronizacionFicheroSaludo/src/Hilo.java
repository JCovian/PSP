public class Hilo extends Thread{
    private Fichero fichero;

    private final boolean SALUDAR;//Indica si el hilo inserta saludos o despedidas

    public Hilo(String nombreHilo, Fichero fichero, boolean SALUDAR) {
        this.fichero = fichero;
        this.SALUDAR = SALUDAR;
        setName(nombreHilo);
    }

    @Override
    public void run() {
        if (SALUDAR) fichero.insertarTexto("Hola\n");
        else fichero.insertarTexto("Adiós\n");
    }
}
