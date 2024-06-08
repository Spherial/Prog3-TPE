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
            if (tarea.esCritica() == esCritica){
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
    	return null;
    }
    

    //Implementar solucion via greedy
    public Solucion greedy(int tiempoMaximoNoRefrigerado) {
    	// Inicializar las asignaciones para cada procesador
    	HashMap<Procesador, ArrayList<Tarea>> asignaciones = new HashMap<>();
    	
    	// Carga procesadores a solución
    	for (Procesador p : procesadores.values()) {
    		asignaciones.put(p, new ArrayList<Tarea>());
    	}
    	
    	for (Tarea tarea : tareas.values()) {
    		Procesador mejorProcesador = null;
    		int mejorTiempo = Integer.MAX_VALUE;

    		for (HashMap.Entry<Procesador, ArrayList<Tarea>> asignacion : asignaciones.entrySet()) {
    			// Obtener Proceador para trabajar
    			Procesador procesador = asignacion.getKey();
    			
    			if (!new SolucionGreedy().esAsignable(procesador, asignacion.getValue(), tarea, tiempoMaximoNoRefrigerado)) {
    				continue;
    			}

    			int tiempoTotalProcesador = calcularTiempoTareas(asignacion.getValue()) + tarea.getTiempoEjecucion();

    			if (mejorProcesador == null) {
    				mejorProcesador = procesador;
    				mejorTiempo = tiempoTotalProcesador;
    			}
    			else if (tiempoTotalProcesador <= mejorTiempo) {
    				if (procesador.estaRefrigerado()) {
    					mejorProcesador = procesador;
	    				mejorTiempo = tiempoTotalProcesador;
    				}
    				else if (!mejorProcesador.estaRefrigerado()) {
    					mejorProcesador = procesador;
	    				mejorTiempo = tiempoTotalProcesador;
    				}
    			}
    		}

    		if (mejorProcesador != null) {
    			asignaciones.get(mejorProcesador).add(tarea);
    		}
    	}

    	return new SolucionGreedy(asignaciones);
    }

    //Calcula el tiempo que tardan las tareas de X procesador
    private int calcularTiempoTareas(ArrayList<Tarea> tareas) {
        int tiempo = 0;
        if (tareas != null) {
	        for (Tarea tarea : tareas) {
	            tiempo += tarea.getTiempoEjecucion();
	        }
    	}
        return tiempo;
    }

}