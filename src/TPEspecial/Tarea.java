package TPEspecial;

public class Tarea {

    private String id;
    private String nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int prioridad;


    public Tarea(String id, String nombre, int tiempoEjecucion, boolean esCritica, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public boolean esCritica() {
        return esCritica;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", esCritica=" + esCritica +
                ", prioridad=" + prioridad +
                '}';
    }
}
