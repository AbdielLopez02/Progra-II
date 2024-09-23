package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Docente extends Persona {
    private String codigo_docente;
    private double salario;
    private String fechaIngresoLaborar;
    private String fechaIngresoRegistro;
    private int id;
    
    private conexion cn;
    
    public Docente() {}

   
    public Docente(int id, String codigo_docente, double salario, String fechaIngresoLaborar, String fechaIngresoRegistro, 
                   String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento); 
        this.id = id;
        this.codigo_docente = codigo_docente;
        this.salario = salario;
        this.fechaIngresoLaborar = fechaIngresoLaborar; 
        this.fechaIngresoRegistro = fechaIngresoRegistro; 
    }

    // Getters y Setters
    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }
    
    public String getFechaIngresoLaborar() {
        return fechaIngresoLaborar;
    }

    public void setFechaIngresoLaborar(String fechaIngresoLaborar) {
        this.fechaIngresoLaborar = fechaIngresoLaborar;
    }
    
    public String getFechaIngresoRegistro() {
        return fechaIngresoRegistro;
    }

    public void setFechaIngresoRegistro(String fechaIngresoRegistro) {
        this.fechaIngresoRegistro = fechaIngresoRegistro;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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
        
        String query = "select * from docente;";
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
        
        String encabezado[] = {"id", "nit", "nombres", "apellidos", "direccion", "telefono", "fecha_nacimiento", "codigo_docente", "salario", "fecha_ingreso_laborar", "fecha_ingreso_registro"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String[11];
        
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("nit");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            datos[7] = consulta.getString("codigo_docente");
            datos[8] = consulta.getString("salario");
            datos[9] = consulta.getString("fecha_ingreso_laborar");
            datos[10] = consulta.getString("fecha_ingreso_registro");
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
        
        // Consulta SQL ajustada para insertar en la tabla docente con todos los campos necesarios
        String query = "INSERT INTO docente(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro) VALUES(?,?,?,?,?,?,?,?,?,?);";
        
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getNit());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_Nacimiento());
        parametro.setString(7, getCodigo_docente());
        parametro.setDouble(8, getSalario());
        parametro.setString(9, getFechaIngresoLaborar());
        parametro.setString(10, getFechaIngresoRegistro());

        int ejecutar = parametro.executeUpdate();
        System.out.println("Ingreso exitoso: " + ejecutar);

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
        
        String query = "UPDATE docente SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, codigo_docente = ?, salario = ?, fecha_ingreso_laborar = ?, fecha_ingreso_registro = ? WHERE id = ?;";
        parametro = cn.conexionDB.prepareStatement(query);
        
        parametro.setString(1, getNit());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_Nacimiento());
        parametro.setString(7, getCodigo_docente());
        parametro.setDouble(8, getSalario());
        parametro.setString(9, getFechaIngresoLaborar());
        parametro.setString(10, getFechaIngresoRegistro());
        parametro.setInt(11, getId());

        int ejecutar = parametro.executeUpdate();
        System.out.println("Actualización exitosa: " + ejecutar);

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en actualizar: " + ex.getMessage());
    }
}
@Override
public void eliminar() {
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        
        String query = "DELETE FROM docente WHERE id = ?;";
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, getId());
        
        int ejecutar = parametro.executeUpdate();
        System.out.println("Eliminación exitosa: " + ejecutar);

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    }
}

      
}