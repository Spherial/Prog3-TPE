package TPEspecial;

import java.util.ArrayList;

public interface Solucion {

    boolean esAsignable(Procesador procesador, ArrayList<Tarea> tareas, Tarea tarea, int x);
    int calcularTiempoTotal();
	void showSolucion();
	
}