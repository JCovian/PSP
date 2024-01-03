import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class E03_FuncionesProcess {
    public static void main(String[] args) {
        String nombreProceso = "notepad";
        Runtime rt = Runtime.getRuntime();
        /*
        try {
            Process miProceso = rt.exec(nombreProceso); //Crear proceso
            System.out.println("El PID del proceso es: " + miProceso.pid());
            System.out.println("Información del proceso: " + miProceso.info());
            int valorSalida = miProceso.waitFor();
            //boolean valorSalida = miProceso.waitFor(5000, TimeUnit.MILLISECONDS);
            int valorSalida2 = miProceso.exitValue();
            //System.out.println("Valor de salida: " + valorSalida);
            System.out.println("Valor de salida: " + valorSalida2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        */
        try {
            Process miProceso = rt.exec(nombreProceso); //Crear proceso
            System.out.println("El PID del proceso es: " + miProceso.pid());
            System.out.println("Información del proceso: " + miProceso.info());
            miProceso.destroy();
            int valorSalida2 = miProceso.exitValue();
            System.out.println("Valor de salida: " + valorSalida2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
