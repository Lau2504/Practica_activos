package pos.logic;

import pos.data.Data;
import pos.data.XmlPersister;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;


public class Service {
    private static Service instance;
    private Data data;
    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }
    public static Service getInstance() {
        if (instance == null) instance= new Service();
        return instance;
    }



    public void stop(){
        //aqui se guardan los datos del data del xml
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println("No se pudo guardar");
        }
    }

    //====CRUD de Activos===
    public void create(Activo a)throws Exception{
        //Cuando el model esta en modo crear en el controller se le da un objeto en el view para que se cree un activo con esos datos
        // Metodo se usa en el controller en el edit y en el view en el boton de guardar con los datos que se recogieron en los JText

        Activo result = data.getActivos().stream().filter(x -> x.getCodigo().equals(a.getCodigo())).findFirst().orElse(null);
        if(result == null){
            data.getActivos().add(a);
        }
        else{
            throw new Exception("Activo ya existe");
        }
    }
    public Activo read(Activo a)throws Exception{ //Busca el activo y lo devuelve
        Activo result = data.getActivos().stream().filter(x -> x.getCodigo().equals(a.getCodigo())).findFirst().orElse(null);
        if(result != null){ return result;}
        else throw new Exception("Activo no existe");
    }
    public void update(Activo a)throws Exception{ //borra el objeto que estaba en la lista y lo vuelve a poner con las actualizaciones
        Activo result;
        try{
            result= this.read(a);
            data.getActivos().remove(result);
            data.getActivos().add(a);
        }catch(Exception ex){
            throw new Exception("Activo no existe");
        }
    }
    public void delete(Activo a)throws Exception{ //lo borra
        data.getActivos().remove(a);
    }
    public List<Activo> search(Activo a) {
        return data.getActivos().stream()
                .filter(i -> i.getNombre() != null && i.getNombre().contains(a.getNombre()))
                .sorted(Comparator.comparing(Activo::getNombre))
                .collect(Collectors.toList());
    }


    //==========CATEGORIAS=======

    public void create(Categoria e) throws Exception {
        Categoria result = data.getCategorias().stream()
                .filter(i -> i.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getCategorias().add(e);
        } else {
            throw new Exception("Categoría ya existe");
        }
    }

    public Categoria read(Categoria e) throws Exception {
        Categoria result = data.getCategorias().stream()
                .filter(i -> i.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Categoría no existe");
        }
    }

    public void update(Categoria e) throws Exception {
        Categoria result;
        try {
            result = this.read(e);
            data.getCategorias().remove(result);
            data.getCategorias().add(e);
        } catch (Exception ex) {
            throw new Exception("Categoría no existe");
        }
    }

    public void delete(Categoria e) throws Exception {
        data.getCategorias().remove(e);
    }

    public List<Categoria> search(Categoria e) {
        return data.getCategorias().stream()
                .filter(i -> i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(Categoria::getNombre))
                .collect(Collectors.toList());
    }

    public List<Categoria> getCategorias() {
        return data.getCategorias();
    }

}

