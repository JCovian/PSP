import java.io.IOException;

public class E01_CrearProceso1 {
    public static void main(String[] args) {
        String nombreProceso = "notepad.exe";
        ProcessBuilder pb = new ProcessBuilder(nombreProceso);
        try {
            pb.start(); //Crea proceso
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}