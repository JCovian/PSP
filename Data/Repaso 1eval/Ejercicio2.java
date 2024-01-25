/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepasoClase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author crist
 * Mostrar los atributos de la tabla profesor
 */
public class Ejercicio2 {
    public static void main(String[] args){
      try{  
        // Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver cargado con éxito");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/instituto","root","");
        System.out.println("Conexión establecida con la BD instituto");
        
        // Preparamoa la consulta
        Statement sentencia = conexion.createStatement();
        String sql;
        sql="SELECT * FROM profesor;";
        ResultSet resul = sentencia.executeQuery(sql);
        while (resul.next()){
            System.out.println(resul.getString(1)+" "+
                    resul.getString(2)+" "+
                    resul.getString(3)+" ");
        }
        sentencia.close();
        conexion.close();
    }catch(ClassNotFoundException cnf){
        System.out.println("Clase no encontrada");
    }catch (SQLException ex){
        System.out.println("Error en sql "+ex.getMessage());
    }
    }
}
