package TPEspecial;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Merge backtracking

    public static void main(String[] args) {
        Servicios servicios = new Servicios("./src/TPEspecial/datasets/Procesadores.csv", "./src/TPEspecial/datasets/Tareas.csv");

        SolucionXBackTracking sol = servicios.backtracking();

        }
}
