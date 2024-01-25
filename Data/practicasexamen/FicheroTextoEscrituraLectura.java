/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasexamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Jose
 * Pide por teclado un nombre y edad hasta que el nombre esté vacío
 * Lee el contendio del fichero
 */
public class FicheroTextoEscrituraLectura {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = "./Files";
        String fichero = "ejemplo2.txt";
        String nombre;
        String linea;
        int edad = 0;
        boolean esEntero;
        int pos, cont = 0;
        
        //Creamos el fichero
        File file = new File (ruta,fichero);
        System.out.println(file.exists() ? "El fichero ya existe" : "Creando fichero");
        
        try {
            //Creamos el flujo de escritura
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            System.out.println("Introduzca el nombre (INTRO para salir)");
            //Pedimos los datos por teclado
            nombre = sc.nextLine();
            while (nombre.length() != 0) { //Pide datos hasta encontrar nombre vacio
                esEntero = false;
                while (!esEntero) { //Compruebo que se mete un numero entero para la edad
                    System.out.println("Introduzca edad:");
                    if (sc.hasNextInt()) {
                        edad = sc.nextInt();
                        esEntero = true;
                    } else {
                        System.err.println("ERROR no ha introducido una edad válida");
                    }
                    sc.nextLine();
                }
                //sc.nextLine();
                
                //Escribimos los datos en el fichero
                bw.write("Nombre: " + nombre);
                bw.newLine();
                bw.write("Edad: " + edad);
                bw.newLine();
                System.out.println("Introduzca un nombre (INTRO para salir");
                nombre = sc.nextLine();
            }
            bw.close();
            sc.close();   
        } catch (IOException ex) {
            System.err.println("ERROR de escritura");
            ex.getMessage();
        }
        
        try {
             //Creamos el flujo de lectura   
            BufferedReader br = new BufferedReader (new FileReader(file));
            System.out.println("Contenido del fichero:");
            try {
                linea = br.readLine();
                while (linea != null) { //Lee en bucle hasta que llega al final del fichero
                    System.out.println(linea);
                    //Contamos palabras que tiene el fichero
                    cont ++;
                    pos = linea.indexOf(" ");
                    while (pos != -1) {
                        cont ++;
                        pos = linea.indexOf(pos + 1);
                        
                    }
                    linea = br.readLine();
                }
                System.out.println("El fichero tiene " + cont + " palabras");
                br.close();
            } catch (IOException ex) {
                System.err.println("ERROR de lectura");
                ex.getMessage();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR fichero no encontrado");
            ex.getMessage();
        }
        File directorio = new File(ruta);
        System.out.println("\nContenido del directorio:");
        String[] contenidoDirectorio = directorio.list();
        for (String dir : contenidoDirectorio) {
            System.out.println(dir);
        }
    }
}
