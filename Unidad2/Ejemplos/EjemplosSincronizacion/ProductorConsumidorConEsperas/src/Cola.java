public class Cola {
    private int numero;
    private boolean disponible = false;

    public
    synchronized
    int get(){

        if(disponible == false){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        disponible = false;
        notifyAll();
        notify();
        return numero;
    }

    public
    synchronized
    void put(int valor){
        if(disponible == true){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        numero = valor;
        disponible = true;
        notifyAll();
    }
}
