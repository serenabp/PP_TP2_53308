package actividades;

public class Charla extends Actividad implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String disertante;

    public Charla(int id, String titulo, int cupoMaximo, String disertante){
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }
    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }
    public double calcularCostoMateriales(){
        return 0.0; // gratuito
    }
    public String getTipo() {
        return "Charla";
    }
}
