package modelo;

import actividades.Actividad;
import actividades.Charla;
import actividades.Curso;
import actividades.Taller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class EventoUniversitario implements Serializable {
    private static final long serialVersionUID = 1L;


    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;

    private Sala sala;
    private List<Actividad> actividades;

    private static int cantidadEventos = 0;


    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();

        cantidadEventos++; //Cada vez que se crea un nuevo evento, sumamos 1 al contador global
    }

    //Cosntructor de copia
    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id + "_copia";
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);

        cantidadEventos++;
    }

        //getters y setters
        public String getId () {
            return id;
        }

        public String getTitulo () {
            return titulo;
        }
        public void setTitulo (String titulo){
            this.titulo = titulo;
        }

        public double getCostoBase () {
            return costoBase;
        }
        public void setCostoBase ( double costoBase){
            this.costoBase = costoBase;
        }

        public boolean isGratuito () {
            return gratuito;
        }
        public void setGratuito ( boolean gratuito){
            this.gratuito = gratuito;
        }

        public Sala getSala () {
            return sala;
        }
        public void setSala (Sala sala){
            this.sala = sala;
        }

        public List<Actividad> getActividades () {
            return actividades;
        }

        //Asignar sala
        public void asignarSala (Sala sala){
            this.sala = sala;
        }

        //Crear actividad

        //Versión para Charlas y Talleres (6 argumentos)
        public Actividad crearActividad(String tipo, int id, String titulo, int cupo, String disertante, boolean requiereNotebook) {
            return crearActividad(tipo, id, titulo, cupo, disertante, requiereNotebook, 0);
        }
        //Versión completa que incluye la duración en semanas para los Cursos (7 argumentos)
        public Actividad crearActividad(String tipo, int id, String titulo, int cupo, String disertante, boolean requiereNotebook, int duracionSemanas) {
            Actividad nueva = null;
            if (tipo.equalsIgnoreCase("charla")) {
                nueva = new Charla(id, titulo, cupo, disertante);
            } else if (tipo.equalsIgnoreCase("taller")) {
                nueva = new Taller(id, titulo, cupo, requiereNotebook);
            } else if (tipo.equalsIgnoreCase("curso")) {
                nueva = new Curso(id, titulo, cupo, duracionSemanas);
            }
            if (nueva != null) {
                this.actividades.add(nueva);
            }
            return nueva;
        }

        //Calcualar costo del evento
        public double calcularCostoEstimado () {
            if (this.gratuito) {
                return 0.0;
            }
            else {
                double sumaMatreiales = 0.0;
                for (Actividad act : actividades) {
                    sumaMatreiales += act.calcularCostoMateriales();
                }
                return (this.costoBase + sumaMatreiales) * 1.21;
            }
        }

        //Consultar el contador global de eventos
        public static int getCantidadEventos () {
            return cantidadEventos;
        }

        //mostrar la informacion en pantalla
        public void mostrarDatos () {
            System.out.println("ID del Evento: " + this.id);
            System.out.println("Título: " + this.titulo);
            System.out.println("Costo Base: $" + this.costoBase);
            System.out.println("¿Es gratuito?: " + this.gratuito);
            System.out.println("Costo total con IVA y Materiales: $" + calcularCostoEstimado());

            if (sala != null) {
                sala.mostrarDatos();
            }
            else {
                System.out.println("Sala: Sin sala asignada");
            }

            System.out.println("\n-- Actividades del Evento --");
            if (actividades.isEmpty()) {
                System.out.println("No hay actividades registradas.");
            }
            else {
                for (Actividad act : actividades) {
                    System.out.println("\nTipo de actividad: " +  act.getTipo());
                    act.mostrarinscripciones();
                }
            }
            System.out.println("---------------------------");
        }

    //Metodo para guardar el evento en un archivo
    public boolean persistirEvento() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.id + ".dat"))) {
            oos.writeObject(this);
            System.out.println("-> Éxito: Evento " + this.id + " persistido correctamente.");
            return true;
        } catch (IOException e) {
            System.out.println("-> Error de E/S al persistir el evento: " + e.getMessage());
            return false;
        }
    }

    //Metodo estático para leer el evento desde el archivo
    public static EventoUniversitario recuperarEvento(String id) {
        EventoUniversitario evento = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(id + ".dat"))) {
            evento = (EventoUniversitario) ois.readObject();
            System.out.println("-> Éxito: Evento recuperado correctamente desde el archivo.");
            System.out.println("\n---------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("-> Error: No se encontró el archivo de persistencia.");
            System.out.println("\n---------------------------");
        } catch (IOException e) {
            System.out.println("-> Error de E/S al leer el archivo: " + e.getMessage());
            System.out.println("\n---------------------------");
        } catch (ClassNotFoundException e) {
            System.out.println("-> Error: La clase no pudo ser encontrada.");
            System.out.println("\n---------------------------");
        }
        return evento;
    }

        //Filtrar actividades por tipo utilizando métodos parametrizados acotados
        public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
            List<T> filtradas = new ArrayList<>();
            for (Actividad act : actividades) {
                if (tipo.isInstance(act)) {
                    filtradas.add(tipo.cast(act));
                }
            }
            return filtradas;
        }

        //Calcular costo de materiales usando wildcards
        public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {
            double total = 0.0;
            for (Actividad act : listaActividades) {
                total += act.calcularCostoMateriales();
            }
            return total;
        }
    }


