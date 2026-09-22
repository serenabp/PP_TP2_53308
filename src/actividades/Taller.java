package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook){
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        if (this.requiereNotebook) {
            return 5000.0;
        } else {
            return 2000.0;
        }
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certificado emitido por " + ENTIDAD_EMISORA +
                " al estudiante " + estudiante.getNombre() +
                " por su participación en el taller: " + getTitulo();
    }
}