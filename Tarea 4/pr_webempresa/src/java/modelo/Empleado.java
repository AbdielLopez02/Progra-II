/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author Kenneth
 */
public class Empleado extends Persona {
    
    private String codigo;
    private int id, id_puesto;
    conexion cn;
    
    public Empleado() {}

    public Empleado(String codigo,int id_puesto ,int id,  String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.codigo = codigo;
        this.id_puesto = id_puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
    
    
    
    
    @Override
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
String query = "SELECT e.id_empleado AS id, e.codigo, e.nombres, e.apellidos, e.direccion, e.telefono, e.fecha_nacimiento, p.puesto, p.id_puesto FROM empleados AS e INNER JOIN puestos AS p ON e.id_puesto = p.id_puesto;";

        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        String encabezado[] = {"id", "codigo", "nombres", "apellidos", "direccion", "telefono", "nacimiento", "puesto", "id_puesto"};
        tabla.setColumnIdentifiers(encabezado);

        String datos[] = new String[9];
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("codigo");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            datos[7] = consulta.getString("puesto");
            datos[8] = consulta.getString("id_puesto");
           

            tabla.addRow(datos);
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en leer: " + ex.getMessage());
    }
    return tabla;
}
    
    
    
    
    
    @Override
    public int crear(){
        
        int retorno =0;
         try{
              PreparedStatement parametro;
              cn = new conexion(); 
              String query = "INSERT INTO empleados(`codigo`,`nombres`,`apellidos`,`direccion`,`telefono`,`fecha_nacimiento`,`id_puesto`)VALUES(?,?,?,?,?,?,?);";
              cn.abrir_conexion();
              parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
              parametro.setString(1, getCodigo());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_Nacimiento());
              parametro.setInt(7, getId_puesto());
              retorno = parametro.executeUpdate();
              cn.cerrar_conexion();
              
          }catch(SQLException ex){
              
              
              System.out.println(ex.getMessage());
          }
        return retorno;
    }
    
    
    
    
    @Override
    public int actualizar() {
        
    int retorno = 0;

    try {
        PreparedStatement parametro;
        cn = new conexion();
        
        String query = "update empleados set codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ?  where id_empleado= ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_Nacimiento());
        parametro.setInt(7, getId_puesto());  
        parametro.setInt(8, getId()); 
        retorno = parametro.executeUpdate();

        

              
              cn.cerrar_conexion();
              
          }catch(SQLException ex){
              
              
              System.out.println(ex.getMessage());
          }
        return retorno;
}

    
    @Override
      public int eliminar(){
     int retorno =0;

      
      try{
              PreparedStatement parametro;
              cn = new conexion();
              String query = "DELETE FROM empleados WHERE id_empleado = ?;";
              cn.abrir_conexion();

              parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
              parametro.setInt(1, getId());
              int ejecutar = parametro.executeUpdate();
              System.out.println("Eliminacion exitosa" + Integer.toString(ejecutar));

              
             cn.cerrar_conexion();
              
          }catch(SQLException ex){
              
              retorno = 0;
              System.out.println("Error en crear: " + ex.getMessage());
          }
        return retorno;
      
      
      
      }
    
    
    
    
}
