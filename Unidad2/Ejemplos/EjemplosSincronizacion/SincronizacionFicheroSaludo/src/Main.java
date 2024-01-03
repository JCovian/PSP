public class Main {
    public static void main(String[] args) {
        Fichero fichero = new Fichero("prueba.txt");

        for (int i = 0; i < 50; i++) {
            new Hilo("Saludo" + i, fichero, true).start();
        }

        for (int i = 0; i < 50; i++) {
            new Hilo("Despedida" + i, fichero, false).start();
        }
    }
}
