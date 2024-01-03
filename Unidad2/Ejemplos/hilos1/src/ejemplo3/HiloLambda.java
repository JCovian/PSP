package ejemplo3;

/**
 * También podemos crear hilos pasando directamente el código Runnable en forma de lambda
 */
public class HiloLambda {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("En el hilo... " + i);
            }
        }, "NombreOpcional" ).start();
    }
}
