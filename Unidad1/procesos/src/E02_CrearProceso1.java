import java.io.IOException;

public class E02_CrearProceso1 {
    public static void main(String[] args) {
        String nombreProceso = "notepad.exe";
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(nombreProceso); //Crea proceso
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}