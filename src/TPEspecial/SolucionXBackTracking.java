package TPEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolucionXBackTracking {
    private HashMap<Procesador, ArrayList<Tarea>> asignaciones;
    private List<Procesador> procesadores;
    private int tiempoMax;



    //Recibe la lista de procesadores de forma externa, para poder generar la tabla de asignaciones
    public SolucionXBackTracking(List<Procesador> procesadores, int tiempoMax) {
        this.procesadores = procesadores;
        this.tiempoMax = tiempoMax;
        asignaciones = new HashMap<>();


        //Para cada procesador (key), existe una lista de tareas asignadas (values)

        for(Procesador p : procesadores){
            ArrayList<Tarea> t = new ArrayList<>(); //Crea la lista de tareas asignadas (empieza vacia)
            this.asignaciones.put(p, t);            //Se la asigna a ese procesador
        }
    }


    //Asigna una tarea a un procesador (construye solucion parcial)
    public void asignarTarea(Procesador procesador, Tarea tarea) {
        this.asignaciones.get(procesador).add(tarea);                   //Ahora con la funcion esSolucionValida, no hace
                                                                        //Falta preguntar antes de asignar cada tarea
    }


    //Quita una tarea a un procesador (solucion parcial)
    public void removerTarea(Procesador procesador, Tarea tarea) {
        this.asignaciones.get(procesador).remove(tarea);
    }




    //Calcula el tiempo maximo de ejecucion que hubo en el procesador mas sobrecargado
    public int calcularTiempoTotal(){

        int tiempoTotal = 0;
        for(Procesador p : this.procesadores) {

            int tiempoAcumulado = 0;
            List<Tarea> tareas = this.asignaciones.get(p);
            for (Tarea t : tareas)
                tiempoAcumulado += t.getTiempoEjecucion();


            if (tiempoAcumulado > tiempoTotal)
                tiempoTotal = tiempoAcumulado;

        }

        if (tiempoTotal == 0){

            return Integer.MAX_VALUE;
        }

        return tiempoTotal;
    }





    //Obtiene la cantidad de tareas criticas de un procesador
    private int getCantCriticas(Procesador procesador){
        int cantidad = 0;
        for (Tarea tarea : this.asignaciones.get(procesador)) {
            if (tarea.esCritica()){
                cantidad++;
            }
        }

        return cantidad;
    }



    //Comprueba que todas y cada una de las asignaciones sean validas (No superar tiempo limite y no mas de 2 criticas)
    //Se realiza una unica vez cuando se llama esta funcion (antes se validaba por cada tarea que se quisiera asignar)
    public boolean esSolucionValida(){

        for(Procesador p : this.procesadores){

            ArrayList<Tarea> tareas = this.asignaciones.get(p);
            int cantidadCriticas = 0;
            int acum = 0;

            for(Tarea t : tareas){

                if(t.esCritica())
                    cantidadCriticas++;

                acum += t.getTiempoEjecucion();

                if(cantidadCriticas >= 2)
                    return false;

                if(!p.estaRefrigerado() && acum >= tiempoMax)
                    return false;

            }

        }

        return true;

    }

    public boolean vacio(){
        for(Procesador p : this.procesadores){
            //Para cada procesador, pregunta si su lista de tareas esta vacia
            if (!asignaciones.get(p).isEmpty()){
                return false;
            }
        }
        return true;
    }

    public void imprimirSolucion(){
        System.out.println("Backtracking");
        System.out.println("Solucion obtenida");
        for(Procesador p : this.procesadores){
            System.out.println("--------------------------------------------");

            if (p.estaRefrigerado())
                System.out.println("Procesador Refrigerado: " + p.getId());
            else
                System.out.println("Procesador: " + p.getId());

            System.out.println("{");
            for(Tarea t : this.asignaciones.get(p)){
                if (t.esCritica())
                    System.out.println("Tarea Critica: " + t.getId());
                else
                    System.out.println("Tarea: " + t.getId());
            }
            System.out.println("}");
        }

        System.out.println("Tiempo de ejecuccion maximo obtenido: "+ calcularTiempoTotal());
        System.out.println("Maximo tiempo permitido para no refrigerados: " + this.tiempoMax);

    }


    public SolucionXBackTracking getCopia() {

        SolucionXBackTracking copia = new SolucionXBackTracking(procesadores, tiempoMax);

        HashMap<Procesador, ArrayList<Tarea>> copiaTareas = new HashMap<>();


        for(Procesador p : procesadores){
            ArrayList<Tarea> tareas = new ArrayList<>(this.asignaciones.get(p));
            copiaTareas.put(p, tareas);
        }


        copia.setAsignaciones(copiaTareas);

        return copia;
    }


    //SOLO deberia ser llamado por la funcion de copia
    private void setAsignaciones(HashMap<Procesador, ArrayList<Tarea>> asignaciones) {
        this.asignaciones = asignaciones;
    }


}
