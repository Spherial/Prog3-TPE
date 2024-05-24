package tpe;

import java.util.Objects;

public class Procesador {

    private final String id;
    private final String codigo;
    private final boolean refrigerado;
    private final int anioFuncionamiento;

    public Procesador(String id, String codigo, boolean refrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public int getAnioFuncionamiento() {
        return anioFuncionamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procesador procesador = (Procesador) o;
        return id == procesador.id &&
                refrigerado == procesador.refrigerado &&
                anioFuncionamiento == procesador.anioFuncionamiento &&
                Objects.equals(codigo, procesador.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, refrigerado, anioFuncionamiento);
    }

}