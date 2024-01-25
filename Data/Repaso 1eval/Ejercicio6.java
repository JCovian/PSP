/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package RepasoClase;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author crist
 * 
 * Introducir un código de alumno por teclado para borrar su registro
 */
public class Ejercicio6 {
    public static void main(String[] args){
        try{
            Scanner teclado = new Scanner(System.in);
            String codigo;
            System.out.println("Introduce un código de alumno a eliminar: ");
            codigo=teclado.nextLine();
            
            //cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");
            
            //Establecemos la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/instituto","root","");
            System.out.println("Conexión establecida con instituto");
            
            //Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql;
            sql="DELETE FROM alumno WHERE cod_alumno='"+codigo+"';";
            System.out.println(sql);
            
            int num_registros = sentencia.executeUpdate(sql);
            System.out.println("Se han eliminado "+num_registros);
            
            sentencia.close();
            conexion.close();
            
        }catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }catch (SQLException ex){
            System.out.println("Error en el sql: "+ ex.getMessage());
        }
    }
    
}
