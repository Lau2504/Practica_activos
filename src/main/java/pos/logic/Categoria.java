package pos.logic;

import java.util.Objects;

public class Categoria {
    private String codigo;
    private String nombre;
    private Integer vida;
    public Categoria() {this("","",0);}
    public Categoria(String codigo, String nombre, Integer vida) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.vida=vida;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public Integer getVida() {
        return vida;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVida(Integer vida) {
        this.vida = vida;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(codigo, categoria.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return  nombre;
    }


}
