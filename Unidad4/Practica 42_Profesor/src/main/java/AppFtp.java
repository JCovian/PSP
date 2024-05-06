import java.io.IOException;

public class AppFtp {
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        ClienteFTP c = new ClienteFTP();
        try{
            c.conectarCliente();
            c.bucleNavegacion();
            c.desconectar();
        } catch (IOException e){
            //Mostramos errores finales en Amarillo. Ejemplo: Se cierra la conexión por inactividad
            System.out.println(ANSI_YELLOW + e.getMessage());
        }

    }
}
