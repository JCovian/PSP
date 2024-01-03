import java.io.IOException;

public class E03_ObtenerInfoProceso {
    public static void main(String[] args) {
        String nombreProceso = "notepad";
        Runtime rt = Runtime.getRuntime();
        try {
            Process miProceso = rt.exec(nombreProceso); //Crear proceso
            System.out.println("El PID del proceso es: " + miProceso.pid());
            System.out.println("Información del proceso: " + miProceso.info());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
