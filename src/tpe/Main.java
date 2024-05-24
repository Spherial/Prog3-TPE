package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
	
		//Muestra datos de tarea por id
		System.out.println(servicios.servicio1("T1").toString());
		
		//Muestra tareas criticas
		System.out.println("Tareas críticas:");
		for(Tarea tareasCriticas : servicios.servicio2(true)) {
			System.out.println(tareasCriticas.toString());
		}
		
		//Muestra tareas no criticas
		System.out.println("Tareas no críticas:");
		for(Tarea tareasNoCriticas : servicios.servicio2(false)) {
			System.out.println(tareasNoCriticas.toString());
		}
		
		//Muestra tareas por prioridad
		System.out.println("Tareas ordenadas por prioridad:");
		for(Tarea tareasPrioridad : servicios.servicio3(1,100)) {
			System.out.println(tareasPrioridad.toString());
		}
	}
}