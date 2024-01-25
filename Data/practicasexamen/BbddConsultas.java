/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasexamen;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Jose
 * Programa que añade un alumno a la BBDD y la muestra
 * 
 */
public class BbddConsultas {
    //private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private final static String BBDD = "jdbc:mysql://192.168.1.209:3306/ejemplo";
    //private final static String USER = "jose";
    //private final static String PASS = "CeRo124";
    private final static String DRIVER = "org.mariadb.jdbc.Driver";
    private final static String BBDD = "jdbc:mariadb://192.168.1.150:3307/ejemplo";
    private final static String USER = "jose";
    private final static String PASS = "JaCo1928.P";
    
    public static void main(String[] args) {
        try {
            //Cargamos driver de la base de datos
            Class.forName(DRIVER);
            System.out.println("Driver cargado");
            //Establecemos conexión con la BBDD
            Connection conexion = DriverManager.getConnection(BBDD, USER, PASS);
            System.out.println("Conexión realizada con éxito");
            //Creamos la sentencia Statment para pode ejecutar las Querys
            Statement sentencia = conexion.createStatement();
            
            //Añadimos un registro a la tabla ALUMNOS
            String sql = "INSERT INTO ALUMNOS VALUES ('45123321A','Covian Onrnia Jose','Llaviada 10','Oviedo','610666521')";
            int filas = sentencia.executeUpdate(sql);
            System.out.println("Se han visto afectadas " + filas + " fila(s)");

            // Consultarmos la base de datos
            sql = "SELECT * FROM ALUMNOS";
            ResultSet resul = sentencia.executeQuery(sql); 
            while (resul.next()) {
                System.out.printf("%S, %S, %S, %S, %S \n",
                        resul.getString(1),
                        resul.getString(2),
                        resul.getString(3),
                        resul.getString(4),
                        resul.getString(5));
            }
            
            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR al cargar el DRIVER");
            ex.getMessage();
        } catch (SQLException ex) {
            Logger.getLogger(BbddConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
