package hilos;
import modelo.EventoUniversitario;
import actividades.Actividad;
import modelo.Inscripcion;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n>>> Arrancando el envío de tickets para el evento: " + evento.getTitulo() + " <<<\n");

        for (Actividad act : evento.getActividades()) {
            for (Inscripcion insc : act.getInscripciones()) {
                if (insc.estaConfirmada() && insc.getTicket() != null) {
                    try {
                        // Simulamos un tiempo de procesamiento/envío en red
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido: " + e.getMessage());
                    }
                    // Enviamos el ticket de forma concurrente
                    insc.getTicket().enviarTicket();
                }
            }
        }

        System.out.println("\n>>> Todos los tickets han sido enviados con éxito <<<\n");
    }
}
