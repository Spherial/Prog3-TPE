package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;

public class Solucion {
	private HashMap<String, ArrayList<Tarea>> asignaciones;


    public Solucion() {
        asignaciones = new HashMap<>();
    }

    //Asigna una tarea a un procesador (construye solucion parcial)
    public void asignarTarea(Procesador procesador, Tarea tarea, int x) {
        // Si el procesador no tiene lista de tareas (es uno nuevo) la creo
        if (!asignaciones.containsKey(procesador.getCodigo())) {
            asignaciones.put(procesador.getCodigo(), new ArrayList<>());
        }

        // Si ese procesador ya tenia una lista de tareas asignadas, y es asignable, le agrego la tarea
        if (esAsignable(procesador, tarea, x)) {
            asignaciones.get(procesador.getCodigo()).add(tarea);
        }
    }

    //Quita una tarea a un procesador (solucion parcial)
    public void removerTarea(Procesador procesador, Tarea tarea) {
        this.asignaciones.get(procesador.getCodigo()).remove(tarea);
    }

    //Calcula el tiempo maximo de ejecucion que hubo en el procesador mas sobrecargado
    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        int maximoTiempo = tiempoTotal;
        for (String key : asignaciones.keySet()) {
            tiempoTotal = 0;
            tiempoTotal += calcularTiempoTareas(key);
            if (tiempoTotal > maximoTiempo) {
                maximoTiempo = tiempoTotal;
            }
        }

        return maximoTiempo;
    }

    //Calcula el tiempo que tardan las tareas de X procesador
    private int calcularTiempoTareas(String id_procesador) {
        ArrayList<Tarea> tareas = this.asignaciones.get(id_procesador);
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

        if (tarea.EsCritica()  &&  getCantCriticas(procesador.getCodigo()) >= 2){
            return false;
        }

        return true;
    }


    //Obtiene la cantidad de tareas criticas de un procesador
    private int getCantCriticas(String id){
        int cantidad = 0;
        for (Tarea tarea : this.asignaciones.get(id)) {
            if (tarea.EsCritica()){
                cantidad++;
            }
        }

        return cantidad;
    }
	
	public void showSolucion(String algoritmo) {
		System.out.println(algoritmo + ":");
		System.out.println("Solucion obtenida:");
		for (HashMap.Entry<String, ArrayList<Tarea>> a : asignaciones.entrySet()) {
			System.out.println("-" + a.getKey().toString());
			for(Tarea t : a.getValue()) {
				System.out.println("--" + t.toString());
			}
		}
		System.out.println("Solucion obtenida: " + this.calcularTiempoTotal());
		System.out.println("Métrica para analizar el costo de la solución");
	}
	
}