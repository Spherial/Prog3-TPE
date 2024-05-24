package tpe;

import java.util.Objects;

public class Tarea {

    private final String id;
    private final String nombre;
    private final int tiempoEjecucion;
    private final boolean critica;
    private final int prioridad;

    public Tarea(String id, String nombre, int tiempoEjecucion, boolean critica, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public boolean isCritica() {
        return critica;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return id == tarea.id &&
                tiempoEjecucion == tarea.tiempoEjecucion &&
                critica == tarea.critica &&
                prioridad == tarea.prioridad &&
                Objects.equals(nombre, tarea.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tiempoEjecucion, critica, prioridad);
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", critica=" + critica +
                ", prioridad=" + prioridad +
                '}';
    }
}