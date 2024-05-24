package tpe_parte1;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Servicios servicios = new Servicios("./src/tpe_parte1/datasets/Procesadores.csv", "./src/tpe_parte1/datasets/Tareas.csv");
        System.out.println(servicios.servicio1("T4"));


        List<Tarea> criticas = servicios.servicio2(true);
        System.out.println("Tareas criticas:");
        System.out.println(criticas);

        List<Tarea> noCriticas = servicios.servicio2(false);
        System.out.println("Tareas no Criticas:");
        System.out.println(noCriticas);

        System.out.println("Filtradas por prioridad");
        List<Tarea> filtradasXPrioridad = servicios.servicio3(70,100);
        System.out.println(filtradasXPrioridad);

        }
}
