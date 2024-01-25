/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepasoClase;
import java.sql.*;
/**
 *
 * @author crist
 * Muestra por pantalla los atributos de la tabla alumno
 */
public class Ejercicio1 {
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
        sql="SELECT * FROM alumno;";
        ResultSet resul = sentencia.executeQuery(sql);
        while (resul.next()){
            System.out.println(resul.getString(1)+" "+
                    resul.getString(2)+" "+
                    resul.getString(3)+" "+
                    resul.getDate(4)+" "+
                    resul.getString(5));
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
