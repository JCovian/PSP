/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasexamen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jose
 * Crea un fichero con el contenido de una cadena que lo escribe caracter a caracter
 * Luego lo lee y lo muestra por pantalla
 */
public class FicheroCaracterACaracter {
    
    public static void main(String[] args) {
        String cadena = "Hola amigos";
        String ruta = "./Files/";
        String nomFichero = "ejemplo1.txt";
        String texto ="";
        
        //Creamos el fichero
        File fichero = new File (ruta, nomFichero);
        try {
            //String existe = fichero.exists() ? "Creando Fichero" : "El fichero ya existe";
            //System.out.println(existe);
            //Abrimos el fichero para lectura
             FileWriter fw = new FileWriter(fichero,true);
            //Recorremos la cadena caracter a caracter
            for (int i = 0; i < cadena.length(); i++){
                fw.write(cadena.charAt(i));
            }
            cadena = "\n";
            fw.write(cadena.charAt(0));
            fw.close();
           
        } catch (IOException ex) {
            System.err.println("ERROR de lecto / escritura");
            ex.getMessage();
        }
        
        try {
            //Abro el fichero para lectura
            FileReader fr = new FileReader (fichero);
            try {
                //Leemos el fichero
                int c = fr.read(); //Almacena el valor numérico del caracter
                while (c != -1) { //-1 es el valor que nos devuelve al llegar al final del fichero
                    texto = texto + (char)c;
                    c = fr.read();
                }
                fr.close();
            } catch (IOException ex) {
                System.err.println("ERROR de lectura");
                ex.getMessage();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR fichero no encontrado");
        }
        System.out.println(texto);
    }
}
