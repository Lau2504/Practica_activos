package pos.presentation.activos;
import pos.Application;
import pos.logic.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Controller {
    View view;
    Model model;
    // Solo el Controller se comunica con el Service
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        //para que ya el view tenga su model y pueda recibir los eventos -Josue

        this.model.init(Service.getInstance().getCategorias()); //Aqui podria recibir un Activo en vez de la lista del Service
            for ( Categoria cat : model.getCategorias() ) {
                System.out.println(cat.getNombre());
            }

        view.setController(this);
        view.setModel(this.model);
        this.model.setCategorias(Service.getInstance().getCategorias());
    }
    public void search(Activo filter){//No se usa el filter porque solo se busca por codigo y es uno
        //Solo se utiliza si hay un table model
    }

    public void save(Activo activo) throws Exception {
        switch (model.getMode()){
            case Application.MODE_CREATE :
                Service.getInstance().create(activo);
                break;
            case Application.MODE_EDIT:
                Service.getInstance().update(activo);
                break;
        }

    }

    public void edit(Activo activo){ //para editar el que tenga el mismo codigo
      try{
          model.setMode(Application.MODE_EDIT);
          model.setCurrent(Service.getInstance().read(activo));
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }


    public void delete(Activo activo) throws Exception {
        Service.getInstance().delete(model.getCurrent());
        //preguntar a Fer
        //no es necesario llamar al search porque ese metodo actualiza el table model y no hay
    }

    public void clear(){
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Activo());
    }
}
