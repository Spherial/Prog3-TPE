package tpe_parte1;

import tpe_parte1.utils.CSVReader;

import java.util.HashMap;
import java.util.List;

public class Servicios {

    private HashMap<String,Tarea> tareas;
    private HashMap<String, Procesador> procesadores;

    //Expresar la complejidad temporal del constructor.



    //TODO: Crear clase que permita crear tareas y procesadores a partir de CSV
    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();

        this.tareas = reader.readTasks(pathTareas);
        this.procesadores = reader.readProcessors(pathProcesadores);
    }

     //Expresar la complejidad temporal del servicio 1.

    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    //Expresar la complejidad temporal del servicio 2.

    public List<Tarea> servicio2(boolean esCritica) {
        return null;
    }

    //Expresar la complejidad temporal del servicio 3.

    public List<Tarea> servicio3(int prioridadInferior, int  prioridadSuperior) {
        return null;
    }
}