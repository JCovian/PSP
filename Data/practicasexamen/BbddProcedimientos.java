/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicasexamen;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class BbddProcedimientos {
    private final static String DRIVER = "org.mariadb.jdbc.Driver";
    private final static String URL = "jdbc:mariadb://192.168.1.150:3307/ejemplo";
    private final static String USER = "jose";
    private final static String PASS = "JaCo1928.P";
    
    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver cargado");
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion establecida");
            
            int dep = 10;
            String sql = "{? = call nombre_dep (?)}";
            CallableStatement llamada = conexion.prepareCall(sql);
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.setInt(2, dep);
            llamada.execute();
            System.out.println("Nombre del departamento: " + llamada.getString(1));
            
            llamada.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR al cargar el driver");
            ex.getMessage();
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos");
            ex.getMessage();
        }
    }
}
