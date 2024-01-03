public class Cola {
    private int numero;
    private boolean disponible = false;

    public
    synchronized
    int get(){
        if(disponible){
            disponible = false;
            return numero;
        }
        return -1;
    }

    public
    synchronized
    void put(int valor){
        numero = valor;
        disponible = true;
    }
}
