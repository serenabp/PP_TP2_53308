import modelo.Estudiante;
import modelo.EventoUniversitario;
import actividades.Actividad;
import excepciones.CupoExcedidoException;
import modelo.Inscripcion;
import modelo.Sala;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("\n=== PRUEBAS EJERCICIO 1 ===");

        Estudiante est1 = new Estudiante("45123", "Ana Pérez");
        Estudiante est2 = new Estudiante("52892", "Carlos Gómez");

        //un evento y una actividad con un CUPO MÁXIMO DE 1 SOLO LUGAR (para forzar el error)
        EventoUniversitario evento1 = new EventoUniversitario("E1", "Introducción a Python", 1500.0, false);
        Actividad taller = evento1.crearActividad("Taller", 1, "Introducción a C++", 1, "", false);

        //Estructura try-catch-finally
        try {
            System.out.println("\n[Intento 1]: Inscribiendo al primer alumno...");
            taller.inscribir(est1); // Esto se inscribe con éxito

            System.out.println("\n[Intento 2]: Inscribiendo al segundo alumno...");
            taller.inscribir(est2); // Esto lanza la excepción CupoExcedidoException

        } catch (CupoExcedidoException e) {
            System.out.println("-> EXCEPCIÓN CAPTURADA EXITOSAMENTE: " + e.getMessage());
        } finally {
            System.out.println("\nFinalizó el control de inscripciones.");
        }

        System.out.println("\n[Probando Persistencia]:");
        evento1.persistirEvento();
        EventoUniversitario.recuperarEvento("E1");


        System.out.println("\n=== PRUEBAS EJERCICIO 2 ===");

        EventoUniversitario eventoCurso = new EventoUniversitario("E2", "Capacitaciones para un mejor rendimiento de estudio para finales", 5000.0, false);
        Sala salaCurso = new Sala(4, "Aula 05");
        eventoCurso.asignarSala(salaCurso);

        Estudiante estCertificado = new Estudiante("53887", "Lucía Fernández");

        actividades.Curso curso1 = (actividades.Curso) eventoCurso.crearActividad("Curso", 10, "Programación Avanzada en Java", 20, "", false, 12);
        try {
            curso1.inscribir(estCertificado);

            System.out.println("\n[Generando Certificado]:");
            String certificado = curso1.generarCertificado(estCertificado);
            System.out.println(certificado);
            System.out.println("\n----------------------------");

        } catch (excepciones.CupoExcedidoException e) {
            System.out.println("-> Error de cupo: " + e.getMessage());
        }


        System.out.println("\n=== PRUEBAS EJERCICIO 3 ===");

        //evento con múltiples actividades mixtas
        EventoUniversitario eventoGeneral = new EventoUniversitario("E3", "Evento Tecnológico", 10000.0, false);

        eventoGeneral.crearActividad("Charla", 101, "Inteligencia Artificial", 50, "Dr. Alan Turing", false);
        eventoGeneral.crearActividad("Taller", 102, "Laboratorio de Git", 20, "", true);
        eventoGeneral.crearActividad("Curso", 103, "Arquitectura de Software", 30, "", false, 8);

        //filtrado parametrizado por tipo concreto
        List<actividades.Charla> charlas = eventoGeneral.filtrarActividadesPorTipo(actividades.Charla.class);
        List<actividades.Taller> talleres = eventoGeneral.filtrarActividadesPorTipo(actividades.Taller.class);
        List<actividades.Curso> cursos = eventoGeneral.filtrarActividadesPorTipo(actividades.Curso.class);

        System.out.println("Cantidad de Charlas filtradas: " + charlas.size());
        System.out.println("Cantidad de Talleres filtrados: " + talleres.size());
        System.out.println("Cantidad de Cursos filtrados: " + cursos.size());

        //cálculo de costos de materiales
        double costoTotalTalleres = eventoGeneral.calcularCostoMateriales(talleres);
        double costoTotalCursos = eventoGeneral.calcularCostoMateriales(cursos);

        System.out.println("\nCosto total de materiales (Talleres): $" + costoTotalTalleres);
        System.out.println("Costo total de materiales (Cursos): $" + costoTotalCursos);
        System.out.println("\n----------------------------");


        System.out.println("\n=== PRUEBAS EJERCICIO 4 ===");

        //evento con una actividad y estudiantes inscriptos
        EventoUniversitario eventoConcurrente = new EventoUniversitario("E4", "Introducción a la programación", 8000.0, false);
        Actividad tallerConcurrente = eventoConcurrente.crearActividad("Taller", 201, "Hilos en Java", 10, "", true);

        Estudiante e1 = new Estudiante("77111", "Martina Ruarte");
        Estudiante e2 = new Estudiante("77222", "Lucas Benegas");
        try {

        Inscripcion ins1 = tallerConcurrente.inscribir(e1);
        Inscripcion ins2 = tallerConcurrente.inscribir(e2);

        //Confirmar las inscripciones (esto genera automáticamente los tickets de acceso)
        if (ins1 != null) ins1.confirmarInscripcion();
        if (ins2 != null) ins2.confirmarInscripcion();
        } catch (excepciones.CupoExcedidoException e) {
            System.out.println("-> Error de cupo: " + e.getMessage());
        }

        //Iniciar el hilo secundario encargado de enviar los tickets
        hilos.EnvioTicketsThread hiloEnvio = new hilos.EnvioTicketsThread(eventoConcurrente);
        hiloEnvio.start(); // Comienza la ejecución concurrente

        //El hilo principal continúa ejecutando tareas en paralelo
        System.out.println("\n(El programa principal sigue ejecutándose mientras el hilo secundario envía los tickets...)");
        eventoConcurrente.mostrarDatos();
    }
}