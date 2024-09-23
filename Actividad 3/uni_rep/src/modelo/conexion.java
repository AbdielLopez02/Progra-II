/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kenneth
 */
public class conexion {
    /** base de datps, nombre de la base de datos, puerto de  base de datos
     *  url conexion, usuario, contraseña, driver conector (jbc)
     */
    
    
    private final String puerto = "3306";
    private final String db = "universidad";
    private final String urlConexion = String.format("jdbc:mysql://127.0.0.1:%s/%s?serverTimezone=UTC",puerto,db);
    private final String usuario = "root2";
    private final String contra = "1234b";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    public Connection conexionDB;
    
    
    public void abrir_conexion(){
        
        try{
            Class.forName(jdbc);
            conexionDB = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("conexion exitosa");

        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("algo salio mal:(" + ex.getMessage());
        }
        
    }
    
     public void cerrar_conexion(){
        
        try{
            conexionDB.close();
            
        }catch(SQLException ex){
            System.out.println("algo salio mal:(" + ex.getMessage());
        }
        
    }



}
