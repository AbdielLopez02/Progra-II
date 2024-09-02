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
public class Empleado extends Persona {
    private String codigo;
    private double sueldo;

    private int id;
    private int id_puesto;
    conexion cn;

    public Empleado() {}

    public Empleado(int id, String codigo, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, int id_puesto, double sueldo) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.codigo = codigo;
        this.id_puesto = id_puesto;
        this.sueldo = sueldo;
    }

    // Getters y Setters para idPuesto
    public int getIdPuesto() {
        return id_puesto;
    }

    public void setIdPuesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   @Override
   
public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new conexion();
        cn.abrir_conexion();
String query = "SELECT e.id_empleado AS id, e.codigo, e.nombres, e.apellidos, e.direccion, e.telefono, e.fecha_nacimiento, p.puesto, p.id_puesto,e.sueldo FROM empleados AS e INNER JOIN puestos AS p ON e.id_puesto = p.id_puesto;";

        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);

        String encabezado[] = {"id", "codigo", "nombres", "apellidos", "direccion", "telefono", "nacimiento", "puesto", "id_puesto", "sueldo"};
        tabla.setColumnIdentifiers(encabezado);

        String datos[] = new String[10];
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
            datos[9] = consulta.getString("sueldo");

            tabla.addRow(datos);
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en leer: " + ex.getMessage());
    }
    return tabla;
}

    @Override
public void crear() {
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        String query = "INSERT INTO `empleados`(`codigo`,`nombres`,`apellidos`,`direccion`,`telefono`,`fecha_nacimiento`, `id_puesto`, `sueldo`)" +
                       " VALUES(?,?,?,?,?,?,?,?);";
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_Nacimiento());
        parametro.setInt(7, getIdPuesto()); 
        parametro.setDouble(8, getSueldo()); 

        int ejecutar = parametro.executeUpdate();
        System.out.println("Ingreso exitoso: " + Integer.toString(ejecutar));

        cn.cerrar_conexion();

    } catch (SQLException ex) {
        System.out.println("Error en crear: " + ex.getMessage());
    }
}
      
    
    @Override
public void actualizar() {
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        String query = "UPDATE empleados SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ?, sueldo = ? WHERE id_empleado = ?;";
        parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_Nacimiento());
        parametro.setInt(7, getIdPuesto());  
        parametro.setDouble(8, getSueldo()); 
        parametro.setInt(9, getId()); 

        int ejecutar = parametro.executeUpdate();
        System.out.println("Actualización exitosa: " + Integer.toString(ejecutar));

        cn.cerrar_conexion();

    } catch (SQLException ex) {
        System.out.println("Error en actualizar: " + ex.getMessage());
    }
}

    
    @Override
      public void eliminar(){
      
      try{
              PreparedStatement parametro;
              cn = new conexion();
              cn.abrir_conexion();
              String query = "DELETE FROM empleados WHERE id_empleado = ?;";
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

