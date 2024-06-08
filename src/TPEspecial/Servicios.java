package TPEspecial;

import TPEspecial.utils.CSVReader;

import java.util.*;

public class Servicios {

    //Mergeado

    private HashMap<String,Tarea> tareas;
    private HashMap<String, Procesador> procesadores;

    //BACKTRACKING
    private SolucionXBackTracking mejorSolucion;

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



    //Obtener lista con todos los procesadores (Para poder ser usados por la Solucion)
    private List<Procesador> obtenerProcesadores(){
        List<Procesador> procesadores = new ArrayList<>();
        for(Procesador procesador : this.procesadores.values()){
            procesadores.add(procesador);
        }
        return procesadores;
    }


    //Obtener lista con todas las tareas (lista de tareas asignables)
    private List<Tarea> obtenerTareas(){
        List<Tarea> tareas = new ArrayList<>();
        for(Tarea tarea : this.tareas.values()){
            tareas.add(tarea);
        }
        return tareas;
    }



    public SolucionXBackTracking backtracking(){
        List<Procesador> procesadores = obtenerProcesadores();
        List<Tarea> tareasParaAsignar = obtenerTareas();
        SolucionXBackTracking solucionParcial = new SolucionXBackTracking(procesadores, 30); //valor de prueba



        this.backtracking_recursivo(solucionParcial, 0,tareasParaAsignar);
        if (this.mejorSolucion.vacio()){
            System.out.println("No se encontro solucion");


            return null;
        }





        return this.mejorSolucion;
    }

    //NOTA: no devolver nada porque la funcion pisa al atributo mejorSolucion cada vez que encuentra una mejor
    private void backtracking_recursivo(SolucionXBackTracking solucionParcial, int posicion, List<Tarea> tareasParaAsignar){
        //Posicion nos sirve para contar cuantas tareas fueron asignadas, en cada llamada se aumenta en 1
        //y si tiene el mismo valor que la cantidad total de tareas, es porque se intento asignar todas


        if (posicion+1 == this.tareas.size()){
            if (solucionParcial.esSolucionValida()){
                if(solucionParcial.calcularTiempoTotal() <= this.mejorSolucion.calcularTiempoTotal()){
                    mejorSolucion = solucionParcial;
                }
            }
            return;
        }


        //Intenta asignar la siguiente tarea
        Tarea tarea = tareasParaAsignar.get(posicion);

        for(Procesador procesador : this.procesadores.values()){
            solucionParcial.asignarTarea(procesador,tarea);
            //Pasa a la siguiente tarea
            this.backtracking_recursivo(solucionParcial, posicion+1, tareasParaAsignar);
            solucionParcial.removerTarea(procesador, tarea);
        }




    }


    //Implementar solucion via greedy
    public SolucionXBackTracking greedy(){
        return null;
    }


}