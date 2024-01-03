package interferencias;

import interferencias.Contador;

class HiloB extends Thread{
    private Contador contador;

    /**
     * Recibe una referencia a un objeto Contador
     */
    public HiloB(String nombre, Contador c){
        setName(nombre);
        contador = c;
    }

    /**
     * Debería decrementar el contador en 300
     */
    @Override
    public void run(){
        for (int i = 0; i < 300; i++) {
            contador.decrementar();
            try {
                //Dormimos el hilo para que otro hilo pueda ejecutar sus operaciones -> se intercalen
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}
