public class Main {
    public static void main(String[] args) throws InterruptedException {
        Panaderia panaderia = new Panaderia();
        Panadero panadero = new Panadero(panaderia);
        Vendedor vendedor = new Vendedor(panaderia);

        panadero.start();
        vendedor.start();
    }
}
