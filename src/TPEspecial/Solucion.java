package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solucion {
    private HashMap<String, ArrayList<Tarea>> asignaciones;


    public Solucion(){
        asignaciones = new HashMap<>();
    }




    //Asigna una tarea a un procesador (construye solucion parcial)
    public void asignarTarea(Procesador procesador, Tarea tarea){
        // Si el procesador no tiene lista de tareas (es uno nuevo) la creo
        if (!asignaciones.containsKey(procesador.getCodigo())) {
            asignaciones.put(procesador.getCodigo(), new ArrayList<>());
        }
        // Si ese procesador ya tenia una lista de tareas asignadas, agrego la tarea a su lista
        asignaciones.get(procesador.getCodigo()).add(tarea);
    }


    //Quita una tarea a un procesador (solucion parcial)
    public void removerTarea(Procesador procesador, Tarea tarea){
        this.asignaciones.get(procesador.getCodigo()).remove(tarea);
    }



    //Calcula el tiempo maximo de ejecucion que hubo en el procesador mas sobrecargado
    public int calcularTiempoTotal(){
        int tiempoTotal = 0;
        int maximoTiempo = tiempoTotal;
        for(String key : asignaciones.keySet()){
            tiempoTotal = 0;
            tiempoTotal += calcularTiempoTareas(key);
            if (tiempoTotal > maximoTiempo){
                maximoTiempo = tiempoTotal;
            }
        }

        return maximoTiempo;
    }

    //Calcula el tiempo que tardan las tareas de X procesador
    private int calcularTiempoTareas(String id_procesador){
        ArrayList<Tarea> tareas = this.asignaciones.get(id_procesador);
        int tiempo = 0;
        for (Tarea tarea : tareas) {
            tiempo+= tarea.getTiempoEjecucion();
        }

        return tiempo;
    }




}
