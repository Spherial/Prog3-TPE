package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;

public class SolucionGreedy {

	private HashMap<Procesador, ArrayList<Tarea>> asignaciones;
	private int maxTiempoNoRefrigerado;

	public SolucionGreedy() {
		this.asignaciones = new HashMap<>();
	}
	
	public SolucionGreedy(HashMap<Procesador, ArrayList<Tarea>> asignaciones, int maxTiempoNoRefrigerado) {
		this.asignaciones = asignaciones;
		this.maxTiempoNoRefrigerado = maxTiempoNoRefrigerado;
	}
	
	//Comprueba si a un procesador se le puede asignar cierta tarea
    public boolean esAsignable(Procesador procesador, ArrayList<Tarea> tareas, Tarea tarea, int x){
    	if (tareas == null) {
    		// Valida si procesador refrigerado no supere tiempos de ejecucion
        	if (procesador.estaRefrigerado() && tarea.getTiempoEjecucion() > x){
                return false;
            }
    	}
    	else {
	    	// Valida si procesador refrigerado no supere tiempos de ejecucion
	    	if (procesador.estaRefrigerado() && (tarea.getTiempoEjecucion() + getTiempoTotalTareas(tareas)) > x){
	            return false;
	        }
	    	
	    	// Valida si tarea es cr�tica para no superar m�ximo 2
	        if (tarea.esCritica()  &&  getCantCriticas(tareas) >= 2){
	            return false;
	        }
    	}
        return true;
    }

    //Obtiene la cantidad de tareas criticas de un procesador
    private int getCantCriticas(ArrayList<Tarea> tareas){
        int cantidad = 0;
        for (Tarea tarea : tareas) {
            if (tarea.esCritica()){
                cantidad++;
            }
        }

        return cantidad;
    } 
    
    //Obtiene el tiempo total de una lista de tareas
    private int getTiempoTotalTareas(ArrayList<Tarea> tareas){
        int tiempo = 0;
        if (tareas != null) {
	        for (Tarea tarea : tareas) {
	            tiempo += tarea.getTiempoEjecucion();
	        }
        }
        return tiempo;
    } 
	
    //Calcula el tiempo maximo de ejecucion que hubo en el procesador mas sobrecargado
    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        int maximoTiempo = tiempoTotal;
        for (HashMap.Entry<Procesador, ArrayList<Tarea>> asignacion : asignaciones.entrySet()) {
            tiempoTotal = 0;
            tiempoTotal += getTiempoTotalTareas(asignacion.getValue());
            if (tiempoTotal > maximoTiempo) {
                maximoTiempo = tiempoTotal;
            }
        }

        return maximoTiempo;
    }
    
    public void showSolucion() {
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("Greedy:");
    	System.out.println("Solucion obtenida:");
    	System.out.println("-------------------------------------------------------------------------------------");
    	for (HashMap.Entry<Procesador, ArrayList<Tarea>> a : asignaciones.entrySet()) {
    		// Muestra Procesador
    		System.out.println("-" + a.getKey().toString());
    		for(Tarea t : a.getValue()) {
				// Muestra Tarea
    			System.out.println("---" + t.toString());
			}
			System.out.println("-------------------------------------------------------------------------------------");
		}
    	// Calcular tiempo total de ejecuci�n
    	System.out.println("Solucion obtenida: " + this.calcularTiempoTotal());
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("M�trica para analizar el costo de la soluci�n:");
		System.out.println("-Maximo tiempo permitido para no refrigerados: " + this.maxTiempoNoRefrigerado);
    	
		// Calcular n�mero de procesadores utilizados
    	int numProcesadoresUtilizados = 0;
    	for (HashMap.Entry<Procesador, ArrayList<Tarea>> a : asignaciones.entrySet()) {
    		if (!a.getValue().isEmpty()) {
    			numProcesadoresUtilizados++;
    		}
    	}
    	System.out.println("-N�mero de procesadores utilizados: " + numProcesadoresUtilizados);

    	// Calcular n�mero de tareas cr�ticas en un procesador
    	for (HashMap.Entry<Procesador, ArrayList<Tarea>> a : asignaciones.entrySet()) {
    		System.out.println("-N�mero de tareas cr�ticas por procesador:");
    		System.out.println("---Procesador: "+ a.getKey().getId() + ". Tareas Cr�ticas: " + getCantCriticas(a.getValue()));
    	}
    }
}