public class UtilPrimos {

    private int cuenta;

    /**
     * Devuelve el número de primos entre min y max
     */
    public static int cuentaPrimos(long min, long max) {
        int count = 0;
        for (long i = min; i <= max; i++) {
            if (esPrimo(i)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Comprueba si un número dado es primo
     * Asumimos que el número es mayor que 1
     */
    public static boolean esPrimo(long x) {
        if(x <= 1) throw new IllegalArgumentException("Número 1 no admitido");

        int hasta = (int)Math.sqrt(x); //Matemáticamente, se sabe que el mayor divisor posible será la raíz cuadrada sin decimales

        for (int i = 2; i <= hasta; i++){
            if ( x % i == 0 )
                return false;
        }

        return true;
    }
}
