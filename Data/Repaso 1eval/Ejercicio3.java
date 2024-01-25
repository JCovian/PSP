/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepasoClase;

/**
 *
 * @author crist
 * 
 * Insertar un registro en la tabla alumno con los siguientes datos: 
 * '-7','Luis','Diaz Pérez','2001-08-11','B'
 */

import java.sql.*;
public class Ejercicio3 {
   
    public static void main(String[] args){
       try{ 
        //Cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver cargado con éxito");
        //Establecemos la conexión
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/instituto","root","");
        System.out.println("Conexión establecida con instituto");
        
        //Preparamos la consulta
        Statement sentencia = conexion.createStatement();
        String sql;
        sql="INSERT INTO alumno "
             +"VALUES ('37','Luis','Diaz Pérez', '2001-08-11','B');";  
        System.out.println(sql);
        
        int num_registros = sentencia.executeUpdate(sql);
        System.out.println("Se han modificado "+num_registros);
        
        sentencia.close();
        conexion.close();
    }catch(ClassNotFoundException cnf){
        System.out.println("Clase no encontrada ");
    }catch(SQLException ex){
        System.out.println("Error en el sql: "+ex.getMessage());
    }
    }
}
