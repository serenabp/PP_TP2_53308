package actividades;

import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    protected  int id;
    protected String titulo;
    protected int cupoMaximo;
    public static final int cupoMINIMO = 2;

    // Colección para consultar las inscripciones de una actividad
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>(); //iniciamos la lista vacia
    }

    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    //metodo para inscribir estudiante

    public Inscripcion inscribir(Estudiante estudiante) throws excepciones.CupoExcedidoException {
        if (inscripciones.size() >= cupoMaximo) {
            throw new excepciones.CupoExcedidoException("No hay cupo disponible para la actividad: " + getTitulo());
        }

        Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
        inscripciones.add(nuevaInscripcion);
        System.out.println("Estudiante " + estudiante.getNombre() + " inscripto con éxito en la actividad: " + getTitulo());
        return nuevaInscripcion;
    }

    // Mostrar inscripciones
    public void mostrarinscripciones() {
        System.out.println("\nActividad: " + getTitulo() + ", ID: " + getId() + ", Cupo Máximo: " + getCupoMaximo() + "\n");
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
        } else {
            for (Inscripcion inscripcion : inscripciones) {
                inscripcion.mostrarInscripcion();
            }
        }
    }
    //metodos abstractos de las clases hijas
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    //metodo para mostrar identificacion de actividad
    public final void mostrarIdentificacion() {
        System.out.println("Tipo: " + getTipo() + "Titulo: " + getTitulo() + "ID: " + getId());
    }
}
