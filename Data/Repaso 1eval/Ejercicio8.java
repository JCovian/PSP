/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepasoClase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author crist
 * Ejecuta el script "cartelera.sql"
 * 
 */
public class Ejercicio8 {
    
    public static void main(String[] args){
        ejecutarScriptMySQL();
    }
    
    public static void ejecutarScriptMySQL(){
        File scriptFile= new File("./script/cartelera.sql");
        System.out.println("\n\n Ficheros de consulta: "+ scriptFile.getName());
        System.out.println("Convirtiendo ewl fichero en cadena...");
        
        BufferedReader entrada = null;
        try{
            entrada = new BufferedReader(new FileReader(scriptFile));
        }catch(FileNotFoundException e){
            System.out.println("Error, no se encuentra el fichero: "+e.getMessage());
        }
        
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto = System.getProperty("line.separator");
        try{
            while((linea = entrada.readLine())!=null){
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        }catch(IOException e){
            System.out.println("Error de E/S al operar con el (fichero: "+ e.getMessage());
        }
        String consulta = stringBuilder.toString();
        System.out.println(consulta);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException cnf){
            System.out.println("Error al cargar el driver: "+ cnf.getMessage());
        }
        try{
            Connection conexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/cartelera?allowMultiQueries=true","root","");
            Statement sents = conexion.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito: res =" + res);
            conexion.close();
            sents.close();
        }catch(SQLException ex){
            System.out.println("Error al ejecutar el script: "+ ex.getMessage());
        }
    }
    
}
