package tpe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import tpe.utils.CSVReader;

public class Servicios {

	private HashMap<String, Tarea> tareas;
	private HashMap<String,Procesador> procesadores;
	
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		
		this.tareas = reader.readTasks(pathTareas);
		this.procesadores = reader.readProcessors(pathProcesadores);
	}
	
	// Obtener tarea por ID
	// Complejidad O(1)
	public Tarea servicio1(String ID) {
		
		return tareas.get(ID);
	}
   
	// Listar tareas críticas o no críticas
    // Complejidad O(n)
	public List<Tarea> servicio2(boolean esCritica) {
		
		List<Tarea> tareasFiltradas = new ArrayList<>();

	    for (Tarea tarea : tareas.values()) {
	        if (tarea.isCritica() == esCritica) {
	            tareasFiltradas.add(tarea);
	        }
	    }

	    return tareasFiltradas;
	}

    // Listar tareas en un rango de prioridad
	//
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		
		// Ordenar las tareas por prioridad
	    List<Tarea> tareasOrdenadas = new ArrayList<>(tareas.values());
	    tareasOrdenadas.sort(Comparator.comparingInt(Tarea::getPrioridad));

	    // Seleccionar tareas en el rango de prioridad
	    List<Tarea> tareasEnRango = new ArrayList<>();
	    for (Tarea tarea : tareasOrdenadas) {
	        int prioridadTarea = tarea.getPrioridad();
	        if (prioridadTarea >= prioridadInferior && prioridadTarea <= prioridadSuperior) {
	            tareasEnRango.add(tarea);
	        }
	    }

	    return tareasEnRango;
	}
    
}