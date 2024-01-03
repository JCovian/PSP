package ejemplo1;

/**
 * Ejemplo de implementación de hilos mediante la extensión de la clase Thread
 */
public class HiloEjemplo1 extends Thread{
    private int c;//Contador para cada hilo

    //Constructor
    public HiloEjemplo1(String nombre){
        //Asignamos un nombre interno al hilo. Valdría también con setName()
        super(nombre);
        System.out.println("Creando hilo " + getName());
    }

    @Override
    public void run() {
        c = 0;
        while(c <= 5){
            System.out.println(getName() + "; C = " + c);
            c++;
        }
    }

}
