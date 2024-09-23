package modelo;

import javax.swing.table.DefaultTableModel;

abstract class Persona {
    private String nit;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String fecha_nacimiento;

    // Constructor vacío
    public Persona() {
    }

    // Constructor con todos los atributos
    public Persona(String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        this.nit = nit;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Getters y setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_Nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_Nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Métodos CRUD vacíos
    protected void crear() {}
    protected DefaultTableModel leer() { return null; }
    protected void actualizar() {}
    protected void eliminar() {}
}
