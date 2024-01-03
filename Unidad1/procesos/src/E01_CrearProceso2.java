import java.io.IOException;

public class E01_CrearProceso2 {
    public static void main(String[] args) {
        String nombreProceso = "C:\\Users\\Jose\\AppData\\Roaming\\Telegram Desktop\\Telegram.exe";
        ProcessBuilder pb = new ProcessBuilder();
        try {
            pb.command(nombreProceso); //Establecer proceso
            pb.start(); //Crea proceso
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}