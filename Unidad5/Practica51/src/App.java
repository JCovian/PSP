import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private String ficheroOriginal = null;
    private String ficheroCifrado = null;
    private String ficheroDescifrado = null;
    private final String extensionCifrado = ".cif";
    private final String extensionDescifrado = ".claro";
    private String algoritmo = null;
    // Clave de cifrado simetrico
    private SecretKey clave;

    /**
     * Genera la clave de cifrado, pide el fichero a encriptar y limpia ficheros encriptado desencriptado
     */
    public App() {
        generarClave();
        pedirFichero();
        limpiarFicheros();
    }

    public static void main(String[] args) {
        //Genera la clave
        App cif = new App();
        //cif.pedirFichero();
        System.out.println("Nombre del fichero: " + cif.ficheroOriginal);
        System.out.println("Cifrando fichero...");
        cif.encriptarFichero();
        System.out.println("Descifrando fichero...");
        cif.desencriptarFichero();
    }

    /**
     *
     *  Valida que el nombre del fichero sea correcto
     * @return boolean
     */
    private static boolean validarNombreFichero(String nombreFichero) {
        // Declara el patrón de validación, no distingue mayúsculas de minúsculas
        Pattern pat = Pattern.compile("\\w+\\.[A-Za-z]{3}");
        // Crea matcher a partir de la expresión regular
        Matcher mat = pat.matcher(nombreFichero);
        if(!mat.matches()) {
            System.err.println("El formato de fichero es incorrecto");
            return false;
        }
        return true;
    }

    /**
     * Pide el nombre del fichero y comprueba si existe
     * @return String con el nombre del fichero
     */
    private String pedirFichero() {
        Scanner sc = new Scanner(System.in);
        boolean existe = false;
        boolean esValido = false;
        File fichero = null;
        String nomFichero = null;

        while (!existe){
            System.out.println("Introduzca el fichero a cifrar: ");
            ficheroOriginal = sc.nextLine();
            esValido = validarNombreFichero(ficheroOriginal);
            if(esValido) {
                fichero = new File(ficheroOriginal);
                if(!fichero.exists()) {
                    System.err.println("El fichero no existe.");
                    existe = false;
                } else {
                    existe = true;
                }
            }
        }
        sc.close();
        return nomFichero;
    }

    /**
     * Elimina los ficheros de cifrado y descifrado
     */
    private void limpiarFicheros() {
        String[] quitaExtension = ficheroOriginal.split("\\.");
        ficheroCifrado = quitaExtension[0] + extensionCifrado;
        //System.out.println(ficheroCifrado);
        File cifrado = new File(ficheroCifrado);
        if(cifrado.exists()) cifrado.delete();
        ficheroDescifrado = quitaExtension[0] + extensionDescifrado;
        //System.out.println(ficheroDescifrado);
        File descifrado = new File(ficheroDescifrado);
        if(descifrado.exists()) descifrado.delete();
    }

    /**
     * Genera una clave simétrica
     *
     * @return la {@link SecretKey SecretKey}  generada
     * @see <a href="https://es.wikipedia.org/wiki/Data_Encryption_Standard">DES</a>
     */
    private void generarClave() {
        try {
            algoritmo = pedirAlgoritmo();
            //Indicamos al motor qué algoritmo usaremos.
            KeyGenerator keyGen = KeyGenerator.getInstance(algoritmo);
            //int keysize = (algoritmo.equals("DES") ? 56 : 128);
            int keysize = switch (algoritmo) {
                case "DES" -> 56;
                case "AES" -> 128;
                default -> 0;
            };
            keyGen.init(keysize);
            clave = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void encriptarFichero() {
        //Las clases que cifran y descifran en Java trabajan siempre con objetos byte[]
        //Podemos convertirlos a String nosotros mismos para ver los resultados

        //Buffer de 1k = 1000 bytes. Uno para el contenido plano y otro para el cifrado
        byte[] bufferPlano = new byte[1000];
        byte[] bufferCifrado;
        int bytesLeidos;


        //Inicializamos los dos flujos: lectura del fichero original y escritura del fichero cifrado
        try (FileInputStream lecturaFicheroPlano = new FileInputStream(ficheroOriginal);
             FileOutputStream escrituraFicheroCifrado = new FileOutputStream(ficheroCifrado);
        ) {
            //Declaramos el cifrador, indicando el algoritmo a usar. Al no indicar proveedor se usará el preferido (SUN)
            Cipher cifrador = Cipher.getInstance(algoritmo);
            //Iniciamos el cifrador con la clave en modo encriptar
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            System.out.println("Clave generada: " + clave.toString());

            //Vamos leyendo bytes de 1k en 1k
            while ((bytesLeidos = lecturaFicheroPlano.read(bufferPlano)) != -1) {
                //Ciframos los bytes leídos. IMPORTANTE indicar los bytesLeidos para no escribir caracteres nulos
                bufferCifrado = cifrador.update(bufferPlano, 0, bytesLeidos);
                //Los escribimos en el fichero cifrado
                escrituraFicheroCifrado.write(bufferCifrado);
            }
            //Con cifrados por bloques, debemos llamar al final a doFinal para resetear el Cifrador a su estado original,
            //procesar cualquier dato restante, y liberar recursos
            bufferCifrado = cifrador.doFinal();
            escrituraFicheroCifrado.write(bufferCifrado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void desencriptarFichero() {
        int bytesLeidos;
        //Buffer para almacenar el contenido descifrado y el cifrado original
        byte[] bufferPlano;
        byte[] bufferCifrado = new byte[1000];

        //Declaramos los streams de lectura del fichero cifrado y escritura del descifrado
        try (FileInputStream entradaCifrado = new FileInputStream(ficheroCifrado);
             FileOutputStream salidaPlano = new FileOutputStream(ficheroDescifrado)) {
            //Declaramos e iniciamos el cifrador en modo descifrar
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, clave);
            System.out.println("Clave generada: " + clave.toString());

            //Vamos leyendo bytes de 1k en 1k
            while ((bytesLeidos = entradaCifrado.read(bufferCifrado)) != -1) {
                //DesCiframos los bytes leídos.
                //IMPORTANTE: Se debe indicar la cantidad de bytesLeidos, ya que la última porción puede no rellenar el buffer completo
                bufferPlano = cifrador.update(bufferCifrado, 0, bytesLeidos);
                //Los escribimos en el fichero descifrado
                salidaPlano.write(bufferPlano);
            }

            //Terminamos cualquier posible operación de descifrado pendiente y reseteamos el cipher
            bufferPlano = cifrador.doFinal();
            salidaPlano.write(bufferPlano);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pide por teclado el tipo de algoritmo para cifrar
     * @return String con el algoritmo de cifrado
     */
    private static String pedirAlgoritmo(){
        Scanner sc = new Scanner(System.in);
        boolean esCorrecto = false;
        String cifrado = null;
        while(!esCorrecto){
            System.out.println("¿Cifrado AES o DES? ");
            cifrado = sc.nextLine();
            // Declara el patrón de validación
            Pattern pat = Pattern.compile("DES|AES");
            // Crea matcher a partir de la expresión regular
            Matcher mat = pat.matcher(cifrado);
            if (!mat.matches()){
                System.err.println("Cifrado desconocido");
                esCorrecto = false;
            } else {
                esCorrecto = true;
            }
        }
        return cifrado;
    }
}