public enum Saludo {
    HOLA, QUE_TAL, BUENAS, QUE_HAY, HEY_SOCKET;

    /**
     * Devuelve un saludo aleatorio en String
     */
    public static String getAleatorio(){
        Saludo[] saludos = values();
        int posAleatoria = (int)(Math.random() * saludos.length);
        return saludos[posAleatoria].toString();
    }
}
