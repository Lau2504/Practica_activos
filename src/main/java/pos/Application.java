package pos;

import pos.data.*;
import pos.logic.Service;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.desktop.AppForegroundListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Application {
    public static void main(String[] args) {
        try {
            Data data = XmlPersister.instance().load();
            System.out.println("Datos cargados correctamente");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception ex) {};

        window = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.getInstance().stop();
            }
        });

        pos.presentation.activos.Model activosModel = new pos.presentation.activos.Model();
        pos.presentation.activos.View activosView = new pos.presentation.activos.View();

        activosController = new pos.presentation.activos.Controller(activosView, activosModel);

        tabbedPane.addTab("Activos",activosView.getPanel());

        window.setSize(900,450);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("Registro de activos");
        window.setVisible(true);


    }

    public static pos.presentation.activos.Controller activosController;

    public static JFrame window;


    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
