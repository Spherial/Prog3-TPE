package TPEspecial;

import TPEspecial.utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servicios {

    //Mergeado

    private HashMap<String,Tarea> tareas;
    private HashMap<String, Procesador> procesadores;

    //Expresar la complejidad temporal del constructor.



    //TODO: Crear clase que permita crear tareas y procesadores a partir de CSV
    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();

        this.tareas = reader.readTasks(pathTareas);
        this.procesadores = reader.readProcessors(pathProcesadores);
    }

     //Complejidad: O(1).

    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    //Complejidad: O(n)

    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas.values()){
            if (tarea.EsCritica() == esCritica){
                resultado.add(tarea);
            }
        }

        return resultado;
    }

    //Complejidad: O(n)

    public List<Tarea> servicio3(int prioridadInferior, int  prioridadSuperior) {





        List<Tarea> resultado = new ArrayList<>();

        // Ordenar las tareas por prioridad
        //List<Tarea> tareasOrdenadas = new ArrayList<>(tareas.values());
        //resultado.sort(Comparator.comparingInt(Tarea::getPrioridad));

        // Seleccionar tareas en el rango de prioridad

        for (Tarea tarea : tareas.values()){
            if ((tarea.getPrioridad() >= prioridadInferior) && (tarea.getPrioridad() <= prioridadSuperior)){
                resultado.add(tarea);
            }
        }

        return resultado;
    }




    //Implementar solucion via backtracking

    public SolucionXBackTracking backtracking(){
        //TODO comenzar back




        return null;
    }

    private SolucionXBackTracking backtracking_recursivo(){
    //TODO



        return null;
    }


    //Implementar solucion via greedy
    public SolucionXBackTracking greedy(){
        return null;
    }


}