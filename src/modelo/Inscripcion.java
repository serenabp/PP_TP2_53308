package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(Estudiante estudiante){
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
        this.estudiante = estudiante;
        this.ticket = null;
    }
    //getters
    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Estudiante getEstudiante() {

        return estudiante;
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }

    //Setters

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void confirmarInscripcion() {
        this.estado = "Confirmada";

        // Se emite el ticket únicamente cuando se confirma
        this.ticket = new TicketDeAcceso("Ticket-" + System.currentTimeMillis());
    }

    public boolean estaConfirmada() {
        return "Confirmada".equalsIgnoreCase(this.estado);
    }

    public void mostrarInscripcion() {
        System.out.println("Inscripción [Fecha: " + getFecha() + ", Estado: " + getEstado() + ", Estudiante: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")]");
    }

    // Clase anidada
    public class TicketDeAcceso implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id;
        private LocalDate fechaEmision;

        public TicketDeAcceso(String id) {
            this.id = id;
            this.fechaEmision = LocalDate.now();
        }

        public void enviarTicket() {
            System.out.println("-> [" + Thread.currentThread().getName() +
                    "] Enviando Ticket ID: " + id +
                    " al estudiante: " + estudiante.getNombre());
        }

        public String getId() {
            return id;
        }

    }
}

