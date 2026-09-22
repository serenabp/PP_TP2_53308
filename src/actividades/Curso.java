package actividades;

import certificacion.Certificable;
import modelo.Estudiante;
import java.io.Serializable;

public class Curso extends Actividad implements Certificable, Serializable {
    private static final long serialVersionUID = 1L;
    private int duracionSemanas;

    public Curso(int id, String titulo, int cupoMaximo, int duracionSemanas) {
        super(id, titulo, cupoMaximo);
        this.duracionSemanas = duracionSemanas;
    }

    public int getDuracionSemanas() {
        return duracionSemanas;
    }

    public void setDuracionSemanas(int duracionSemanas) {
        this.duracionSemanas = duracionSemanas;
    }

    @Override
    public double calcularCostoMateriales() {
        return 3000.0; // Costo fijo de materiales para los cursos
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    // Metodo obligatorio heredado de la interfaz Certificable
    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certificado emitido por " + ENTIDAD_EMISORA +
                " al estudiante " + estudiante.getNombre() +
                " (Legajo: " + estudiante.getLegajo() +
                ") por haber completado el curso: " + getTitulo();
    }
}