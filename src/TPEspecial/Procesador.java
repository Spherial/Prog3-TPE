package TPEspecial;

public class Procesador {
    private String id;
    private String codigo;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;



    public Procesador(String id, String codigo, boolean estaRefrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean estaRefrigerado() {
        return estaRefrigerado;
    }

    public int getAnioFuncionamiento() {
        return anioFuncionamiento;
    }


    @Override
    public String toString() {
        return "Procesador{" +
                "id='" + id + '\'' +
                ", codigo='" + codigo + '\'' +
                ", estaRefrigerado=" + estaRefrigerado +
                ", anioFuncionamiento=" + anioFuncionamiento +
                '}';
    }
}
