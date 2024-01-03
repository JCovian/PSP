import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jose Aquilino Covián Ornia
 * Lee por su entrada estándard caracter a caracter hasta encontrar un caracter
 * de terminación de lectura (el caracter *). Entonces muestra por pantalla todo
 * lo leido hasta ahora.
 */
public class HijoCaracteres {
    public static void main(String[] args) throws IOException {
        InputStreamReader lectura = new InputStreamReader(System.in);

        int is = lectura.read(); // Almacena el entero que devuelve el InputStreamReader
        char caracter = (char)is; //Convierte el entero al caracter correspondiente
        String totalLeido = ""; //String con todos los caracteres leidos

        // Lee los caracteres que llegan por la entrada estandar hasta que encuentra el *
        // Se saca por salida estándar los caracteres hasta el * o muestra error si no encuentra ningún *
        while ((caracter !=  '*') || (is == -1)) {
            totalLeido += caracter;
            is = lectura.read();
            if (is == -1) {
                System.err.println("No ha introducido el caracter de fin de cadena *");
                System.exit(-1);
            } else caracter = (char)is;
        }
        System.out.println(totalLeido);
    }
}
