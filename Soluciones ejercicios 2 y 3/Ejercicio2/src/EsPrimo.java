import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase lee por su entrada estándar un número con nextInt() de la clase Scanner
 * Si el valor introducido no es un número, muestra un mensaje de error
 * Si es un número,
 *   - responde con un 1 por su salida estándar si es primo
 *   - responde con un 0 por su salida estándar si no lo es
 *
 *   Se asume que el número leído (si es un número) es positivo. No hace falta comprobarlo
 *
 */
public class EsPrimo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            int numero = sc.nextInt();
            System.out.println(esPrimo(numero));
        }catch(InputMismatchException e){
            System.err.println("El valor recibido no es un número");
            System.exit(-1);
        }
    }

    /**
     * Calcula si el número recibido es primo o no
     * @param numero el número a chequear si es primo
     * @return 1 si es primo, 0 si no lo es
     */
    private static int esPrimo(int numero) {
        for (int divisor = numero -1 ; divisor > 1; divisor--) {
            if(numero % divisor == 0){
                return 0;
            }
        }
        //Llegados a este punto, el número es primo
        return 1;
    }
}
