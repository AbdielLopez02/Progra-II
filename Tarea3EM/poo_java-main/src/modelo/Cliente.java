/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paiz2
 */
public class Cliente extends Persona{
    private String nit;
     private int id;
    conexion cn;

    public Cliente() {}

    public Cliente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.nit = nit;
    }
    

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
     public DefaultTableModel leer(){
       DefaultTableModel tabla = new DefaultTableModel();
       try{
           cn = new conexion();
           cn.abrir_conexion();
           String query = "select * from clientes;";
           ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
           
           String encabezado [] = {"id","nit","nombres","apellidos","direccion","telefono","nacimiento"};
           tabla.setColumnIdentifiers(encabezado);
           
           
           String datos [] = new String [7];
           while(consulta.next()){
               datos [0] = consulta.getString("id_cliente");
               datos [1] = consulta.getString("nit");
               datos [2] = consulta.getString("nombres");
               datos [3] = consulta.getString("apellidos");
               datos [4] = consulta.getString("direccion");
               datos [5] = consulta.getString("telefono");
               datos [6] = consulta.getString("fecha_nacimiento");
               
               tabla.addRow(datos);
               
           }
           cn.cerrar_conexion();
       }catch(SQLException ex){
           
       }
          return tabla;
      
}
    @Override
      public void crear(){
          try{
              PreparedStatement parametro;
              cn = new conexion();
              cn.abrir_conexion();
              String query = "INSERT INTO `clientes`(`nit`,`nombres`,`apellidos`,`direccion`,`telefono`,`fecha_nacimiento`)" +  "VALUES(?,?,?,?,?,?);";
              parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
              parametro.setString(1, getNit());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_Nacimiento());
              int ejecutar = parametro.executeUpdate();
              System.out.println("Ingreso exitosos" + Integer.toString(ejecutar));

              
              cn.cerrar_conexion();
              
          }catch(SQLException ex){
              System.out.println("Error en crear: " + ex.getMessage());
          }
          
          
          
      }
      
    
    @Override
      public void actualizar(){
          
              try{
              PreparedStatement parametro;
              cn = new conexion();
              cn.abrir_conexion();
              String query = "UPDATE clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id_cliente = ?;";
              parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
              parametro.setString(1, getNit());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_Nacimiento());
              parametro.setInt(7, getId());
              int ejecutar = parametro.executeUpdate();
              System.out.println("Actualizacion exitosa" + Integer.toString(ejecutar));

              
              cn.cerrar_conexion();
              
          }catch(SQLException ex){
              System.out.println("Error en actualizar: " + ex.getMessage());
          }
          
          
      }
     
    
    @Override
      public void eliminar(){
      
      try{
              PreparedStatement parametro;
              cn = new conexion();
              cn.abrir_conexion();
              String query = "DELETE FROM clientes WHERE id_cliente = ?;";
              parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
              parametro.setInt(1, getId());
              int ejecutar = parametro.executeUpdate();
              System.out.println("Eliminacion exitosa" + Integer.toString(ejecutar));

              
              cn.cerrar_conexion();
              
          }catch(SQLException ex){
              System.out.println("Error en eliminar: " + ex.getMessage());
          }
      
      
      
      }
      
      
      
      
}

