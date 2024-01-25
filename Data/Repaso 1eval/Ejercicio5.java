/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepasoClase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author crist
 * Modificar los datos de un registro por el código. Se deben introducir el código y los demás datos por teclado
 */
public class Ejercicio5 {
        public static void main(String[] args){
       try{ 
            Scanner teclado = new Scanner(System.in);
            //Introducimos los datos por teclado
            String codigo, nombre, apellidos, fec_nac, grupo;
            System.out.println("Introduzca el código del alumno:");
            codigo = teclado.nextLine();
            System.out.println("Introduzca el nombre del alumno:");
            nombre = teclado.nextLine();
            System.out.println("Introduzca los apellidos del alumno:");
            apellidos = teclado.nextLine();
            System.out.println("Introduzca la fecha de nacimiento:");
            fec_nac = teclado.nextLine();
            System.out.println("Introduce el grupo del alumno");
            grupo = teclado.nextLine();

           
           
            //Cargamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado con éxito");
            //Establecemos la conexión
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/instituto","root","");
            System.out.println("Conexión establecida con instituto");

              //Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql;
            sql=
                    "UPDATE alumno SET "
                    +"nombre_alumno='"+nombre+"',apellidos_alumno='"+apellidos+
                     "',fecha_nacimiento='"+fec_nac+"',grupo='"+grupo+"'WHERE cod_alumno='"+codigo+"';";
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
