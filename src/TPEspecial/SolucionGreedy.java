package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;

public class SolucionGreedy implements Solucion{

	private HashMap<Procesador, ArrayList<Tarea>> asignaciones;

	public SolucionGreedy() {
		this.asignaciones = new HashMap<>();
	}
	
	public SolucionGreedy(HashMap<Procesador, ArrayList<Tarea>> asignaciones) {
		this.asignaciones = asignaciones;
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
	    	
	    	// Valida si tarea es crítica para no superar máximo 2
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
    
    //Obtiene la cantidad de tareas criticas de un procesador
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
    @Override
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
    
    @Override
	public void showSolucion() {
		System.out.println("Greedy:");
		System.out.println("Solucion obtenida:");
		for (HashMap.Entry<Procesador, ArrayList<Tarea>> a : asignaciones.entrySet()) {
			System.out.println("-" + a.getKey().toString());
			for(Tarea t : a.getValue()) {
				System.out.println("--" + t.toString());
			}
		}
		System.out.println("Solucion obtenida: " + this.calcularTiempoTotal());
		System.out.println("Métrica para analizar el costo de la solución");
	}
	
}