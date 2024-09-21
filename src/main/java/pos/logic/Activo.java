package pos.logic;

import java.util.Objects;

public class Activo {
    private String nombre;
    private String codigo;
    private Categoria categoria;
    private Integer anioFabricacion;
    private Double valor;
    public Activo(String nombre, String codigo, Categoria categoria, Integer anioFabricacion, Double valor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.anioFabricacion = anioFabricacion;
        this.valor = valor;
    }
    public Activo() {
        this("","",null,0,0.0);
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Integer getAnioFabricacion() {
        return anioFabricacion;
    }
    public void setAnioFabricacion(Integer anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activo activo = (Activo) o;
        return Objects.equals(codigo, activo.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


}
