package sincronizados;

class HiloB extends Thread{
    private ContadorSincro contadorSincro;

    /**
     * Recibe una referencia a un objeto Contador
     */
    public HiloB(String nombre, ContadorSincro c){
        setName(nombre);
        contadorSincro = c;
    }

    /**
     * Debería decrementar el contador en 300
     */
    @Override
    public void run(){
        for (int i = 0; i < 300; i++) {
            contadorSincro.decrementar();
            try {
                //Dormimos el hilo para que otro hilo pueda ejecutar sus operaciones -> se intercalen
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getName() + " contador vale " + contadorSincro.getValor());
    }
}
