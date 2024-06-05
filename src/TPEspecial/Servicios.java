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

    public Solucion backtracking(int tiempoMaximoNoRefrigerado){
    	HashMap<String, Tarea> tareasSinAsignar = new HashMap<>(tareas);
    	HashMap<Procesador, List<Tarea>> asignacionInicial = new HashMap<>();
    	Solucion mejorSolucion = new Solucion(asignacionInicial, Integer.MAX_VALUE);

    	backtrackingRecursivo(tareasSinAsignar, asignacionInicial, procesadores, mejorSolucion, tiempoMaximoNoRefrigerado);
    	
    	mejorSolucion.showSolucion("Backtracking");
    	return mejorSolucion;
    }
    
    private void backtrackingRecursivo(HashMap<String, Tarea> tareasSinAsignar, HashMap<Procesador, List<Tarea>> asignacionActual, HashMap<String, Procesador> procesadores, Solucion mejorSolucion, int tiempoMaximoNoRefrigerado) {
    	
    }

    private boolean esAsignable(Tarea tarea, Procesador procesador, int tiempoMaximoNoRefrigerado) {
    	// Verificar si el procesador puede ejecutar la tarea sin superar el tiempo máximo no refrigerado
    	if (!procesador.EstaRefrigerado() && (procesador.getTiempoEjecucionAsignado() + tarea.getTiempoEjecucion()) > tiempoMaximoNoRefrigerado) {
    		return false;
    	}

    	// Verificar si el procesador no supera la cantidad máxima de tareas críticas
    	if (tarea.EsCritica() && procesador.getCantidadTareasCriticas() >= procesador.getCantidadMaximaTareasCriticas()) {
    		return false;
    	}

    	return true;
    }
    
    private int calcularTiempoFinal(HashMap<Procesador, List<Tarea>> asignacion) {
    	int tiempoFinalMaximo = 0;
    	for (HashMap.Entry<Procesador, List<Tarea>> entrada : asignacion.entrySet()) {
    		int tiempoFinalProcesador = 0;
    		for (Tarea tarea : entrada.getValue()) {
    			tiempoFinalProcesador += tarea.getTiempoEjecucion();
    		}
    		tiempoFinalMaximo = Math.max(tiempoFinalMaximo, tiempoFinalProcesador);
    	}
    	return tiempoFinalMaximo;
    }

    //Implementar solucion via greedy
    public Solucion greedy(){
        return null;
    }


}