package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;

public class SolucionXBackTracking {
    private HashMap<Procesador, ArrayList<Tarea>> asignaciones;


    public SolucionXBackTracking() {
        asignaciones = new HashMap<>();
    }


    //Asigna una tarea a un procesador (construye solucion parcial)
    public void asignarTarea(Procesador procesador, Tarea tarea, int x) {
        // Si el procesador no tiene lista de tareas (es uno nuevo) la creo
        if (!asignaciones.containsKey(procesador)) {
            asignaciones.put(procesador, new ArrayList<>());
        }


        // Si ese procesador ya tenia una lista de tareas asignadas, y es asignable, le agrego la tarea
        if (esAsignable(procesador, tarea, x)) {
            asignaciones.get(procesador).add(tarea);
        }


    }


    //Quita una tarea a un procesador (solucion parcial)
    public void removerTarea(Procesador procesador, Tarea tarea) {
        this.asignaciones.get(procesador).remove(tarea);
    }


    //Calcula el tiempo maximo de ejecucion que hubo en el procesador mas sobrecargado
    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        int maximoTiempo = tiempoTotal;
        for (Procesador key : asignaciones.keySet()) {
            tiempoTotal = 0;
            tiempoTotal += calcularTiempoTareas(key);
            if (tiempoTotal > maximoTiempo) {
                maximoTiempo = tiempoTotal;
            }
        }

        return maximoTiempo;
    }

    //Calcula el tiempo que tardan las tareas de X procesador
    private int calcularTiempoTareas(Procesador procesador) {
        ArrayList<Tarea> tareas = this.asignaciones.get(procesador);
        int tiempo = 0;
        for (Tarea tarea : tareas) {
            tiempo += tarea.getTiempoEjecucion();
        }

        return tiempo;
    }

    //Comprueba si a un procesador se le puede asignar cierta tarea
    public boolean esAsignable(Procesador procesador, Tarea tarea, int x){
        if (procesador.estaRefrigerado() && tarea.getTiempoEjecucion() > x){
            return false;
        }

        if (tarea.EsCritica()  &&  getCantCriticas(procesador) >= 2){
            return false;
        }

        return true;
    }



    //Obtiene la cantidad de tareas criticas de un procesador
    private int getCantCriticas(Procesador procesador){
        int cantidad = 0;
        for (Tarea tarea : this.asignaciones.get(procesador)) {
            if (tarea.EsCritica()){
                cantidad++;
            }
        }

        return cantidad;
    }


}
