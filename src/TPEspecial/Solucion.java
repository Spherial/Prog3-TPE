package TPEspecial;

import java.util.HashMap;
import java.util.List;

public class Solucion {

	private HashMap<Procesador, List<Tarea>> asignacion;
	private int tiempoFinal;

	public Solucion(HashMap<Procesador, List<Tarea>> asignacion, int tiempoFinal) {
		this.asignacion = asignacion;
		this.tiempoFinal = tiempoFinal;
	}

	public HashMap<Procesador, List<Tarea>> getAsignacion() {
		return asignacion;
	}

	public int getTiempoFinal() {
		return tiempoFinal;
	}

	public void setTiempoFinal(int tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}

	@Override
	public String toString() {
		return "Solucion{" +
			"asignacion=" + asignacion +
			", tiempoFinal=" + tiempoFinal +
			'}';
	}
	
	public void showSolucion(String algoritmo) {
		System.out.println(algoritmo + ":");
		System.out.println("Solucion obtenida:");
		for (HashMap.Entry<Procesador, List<Tarea>> a : asignacion.entrySet()) {
			System.out.println("-" + a.getKey().toString());
			for(Tarea t : a.getValue()) {
				System.out.println("--" + t.toString());
			}
		}
		System.out.println("Solucion obtenida: " + this.getTiempoFinal());
		System.out.println("Métrica para analizar el costo de la solución");
	}
	
}