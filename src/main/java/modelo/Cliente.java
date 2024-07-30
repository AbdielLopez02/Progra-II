/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Kenneth
 */
public class Cliente extends Persona{
    private String nit;
    
    public Cliente(){}
    
    
    public Cliente(String nit, String nombre, String apellido, String direccion, String telefono, String fechanac) {
        super(nombre, apellido, direccion, telefono, fechanac);
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    @Override
    public void agregar(){ 
        
    System.out.println("Nit: "+ getNit());
    System.out.println("Nombre: "+ getNombre());
    System.out.println("Apellido: "+ getApellido());
    System.out.println("DIrección: "+ getDireccion());
    System.out.println("Teléfono: "+ getTelefono());
    System.out.println("Fecha de nacimiento: "+ getFechanac());
    System.out.println("_______________________________________");
    
    
    }
   
    
    
    
    
}
