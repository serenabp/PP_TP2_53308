package modelo;
import java.io.Serializable;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;

    //contructor para iniciar la sala
    public Sala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    //Getters
    public int getId()  {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarDatos() {
        System.out.println("\nSala asignada: " + getNombre() + " - ID: " + getId());
    }
}
