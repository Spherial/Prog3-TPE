package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolucionXBackTracking {
    private HashMap<Procesador, ArrayList<Tarea>> asignaciones;
    private List<Procesador> procesadores;
    private int tiempoMax;



    //Recibe la lista de procesadores de forma externa, para poder generar la tabla de asignaciones
    public SolucionXBackTracking(List<Procesador> procesadores, int tiempoMax) {
        this.procesadores = procesadores;
        this.tiempoMax = tiempoMax;
        asignaciones = new HashMap<>();

        //Para cada procesador (key), existe una lista de tareas asignadas (values)

        for(Procesador p : procesadores){
            ArrayList<Tarea> t = new ArrayList<>(); //Crea la lista de tareas asignadas (empieza vacia)
            this.asignaciones.put(p, t);            //Se la asigna a ese procesador
        }
    }


    //Asigna una tarea a un procesador (construye solucion parcial)
    public void asignarTarea(Procesador procesador, Tarea tarea, int x) {
        this.asignaciones.get(procesador).add(tarea);                   //Ahora con la funcion esSolucionValida, no hace
                                                                        //Falta preguntar antes de asignar cada tarea
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



    //Comprueba que todas y cada una de las asignaciones sean validas (No superar tiempo limite y no mas de 2 criticas)
    //Se realiza una unica vez cuando se llama esta funcion (antes se validaba por cada tarea que se quisiera asignar)
    public boolean esSolucionValida(){

        for(Procesador p : this.procesadores){

            ArrayList<Tarea> tareas = this.asignaciones.get(p);
            int cantidadCriticas = 0;
            int acum = 0;

            for(Tarea t : tareas){

                if(t.EsCritica())
                    cantidadCriticas++;

                acum += t.getTiempoEjecucion();

                if(cantidadCriticas >= 2)
                    return false;

                if(!p.estaRefrigerado() && acum >= tiempoMax)
                    return false;

            }

        }

        return true;

    }



}
