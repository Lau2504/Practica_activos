package pos.presentation.activos;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel  {
    private int mode;
    private Activo current;
//     private Activo filter; //no lo ocupo
//    private List<Activo> listaActivos; //no ocupo
    private List<Categoria> listaCategorias;

    public Model() {}

    public void init(List<Categoria> listaCategorias) {

        this.current =new Activo();
        this.listaCategorias = listaCategorias;
        this.mode = Application.MODE_CREATE;
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
//        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
//        firePropertyChange(FILTER);
        firePropertyChange(CATEGORIAS);
    }
    public void setMode(int mode) {
        this.mode = mode;
    }
    public int getMode() {
        return mode;
    }
    public void setCurrent(Activo current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }
    public Activo getCurrent() {
        return current;
    }
//    public void setFilter(Activo filter) {
//        this.filter = filter;
//        firePropertyChange(FILTER);
//    }
//    public Activo getFilter() {
//        return filter;
//    }
//
//    public List<Activo> getListaActivos() {
//        return listaActivos;
//    }
//    public void setListaActivos(List<Activo> listaActivos) {
//        this.listaActivos = listaActivos;
//        firePropertyChange(LIST);
//    }
    public void setCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
        firePropertyChange(CATEGORIAS);
    }
    public List<Categoria> getCategorias() {
        return listaCategorias;
    }

    public static final String LIST = "listaActivos";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
    public static final String CATEGORIAS = "categorias";
}
