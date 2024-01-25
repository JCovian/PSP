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
public class BbddProcedimientos2 {
    private final static String DRIVER = "org.mariadb.jdbc.Driver";
    private final static String URL = "jdbc:mariadb://192.168.1.150:3307/ejemplo";
    private final static String USER = "jose";
    private final static String PASS = "JaCo1928.P";
    
    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            
            String sql = "{call subida_sal (?,?)}";
            CallableStatement llamada = conexion.prepareCall(sql);
            llamada.setInt(1, 10);
            llamada.setFloat(2, 1f);
            
            llamada.executeUpdate();
            System.out.println("Subida realizada");
            
            llamada.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR no se encuentra el Driver");
        } catch (SQLException ex) {
            System.err.println("ERROR al acceder a la base de datos");
        }
    }
    
}
