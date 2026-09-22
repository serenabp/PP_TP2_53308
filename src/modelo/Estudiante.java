package modelo;
import java.io.Serializable;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
     private String legajo;
     private String nombre;

     public Estudiante(String legajo, String nombre) {
         this.legajo = legajo;
         this.nombre = nombre;
     }
     //getters
    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }
    //setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public void mostrarDatos() {
        System.out.println("Nombre del estudiante: " + getNombre() + "Legajo: " + getLegajo());
    }
}
