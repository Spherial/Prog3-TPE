package TPEspecial;

import TPEspecial.utils.CSVReader;

import java.util.*;

public class Servicios {

    //Mergeado

    private HashMap<String,Tarea> tareas;
    private HashMap<String, Procesador> procesadores;

    //BACKTRACKING
    private SolucionXBackTracking mejorSolucion;
    private int tiempoEjecucionMaximo;
    private int cantidadCasos;

    //Expresar la complejidad temporal del constructor.



    //TODO: Crear clase que permita crear tareas y procesadores a partir de CSV
    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();

        this.tareas = reader.readTasks(pathTareas);
        this.procesadores = reader.readProcessors(pathProcesadores);
        this.tiempoEjecucionMaximo = 100;
        this.mejorSolucion = new SolucionXBackTracking(this.obtenerProcesadores(), this.tiempoEjecucionMaximo);
        this.cantidadCasos = 0;
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



        for (Tarea tarea : tareas.values()){
            if ((tarea.getPrioridad() >= prioridadInferior) && (tarea.getPrioridad() <= prioridadSuperior)){
                resultado.add(tarea);
            }
        }

        return resultado;
    }






    //--------------------------------------------------PARTE 2-----------------------------------------------------

    public int getCantidadCasos(){
        return this.cantidadCasos;
    }

    public void contarCaso(){
        cantidadCasos++;
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

    public void setTiempoEjecucionMaximo(int nuevoTiempo){
        this.tiempoEjecucionMaximo = nuevoTiempo;
    }

    public SolucionXBackTracking backtracking(){
        contarCaso();
        List<Procesador> procesadores = obtenerProcesadores();
        List<Tarea> tareasParaAsignar = obtenerTareas();
        SolucionXBackTracking solucionParcial = new SolucionXBackTracking(procesadores, this.tiempoEjecucionMaximo);



        this.backtracking_recursivo(solucionParcial, 0,tareasParaAsignar);
        if (this.mejorSolucion.vacio()){
            System.out.println("No se encontro solucion");


            return null;
        }




        this.mejorSolucion.imprimirSolucion();
        System.out.println("Cantidad de casos: " + this.getCantidadCasos());
        return this.mejorSolucion;
    }

    //NOTA: no devolver nada porque la funcion pisa al atributo mejorSolucion cada vez que encuentra una mejor
    private void backtracking_recursivo(SolucionXBackTracking solucionParcial, int posicion, List<Tarea> tareasParaAsignar){
        //Posicion nos sirve para contar cuantas tareas fueron asignadas, en cada llamada se aumenta en 1
        //y si tiene el mismo valor que la cantidad total de tareas, es porque se intento asignar todas


        contarCaso();
        if (posicion == tareasParaAsignar.size()){
            if (solucionParcial.esSolucionValida()){
                if(solucionParcial.calcularTiempoTotal() <= this.mejorSolucion.calcularTiempoTotal()){
                    mejorSolucion = solucionParcial.getCopia();
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