package pos.presentation.activos;

import pos.Application;
import pos.logic.Activo;
import pos.logic.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class View implements PropertyChangeListener {

    private JPanel panel;
    private JPanel activos;
    private JTextField codigoTextField;
    private JButton consultarButton;
    private JButton limpiarButton;
    private JTextField activoTextField;
    private JTextField fabricacionTextField;
    private JTextField valorTextField;
    private JComboBox comboBoxCategorias;
    private JTextField edadTextField;
    private JTextField depreciacionTextField;
    private JTextField valorActualTextField;
    private JButton guardarButton;
    private JLabel codigoJLabel;
    private JLabel activoJLabel;
    private JLabel fabricacionJLabel;
    private JLabel valorJLabel;
    private JLabel categoriasJLabel;
    private JLabel edadJLabel;
    private JLabel depreciacionJLabel;
    private JLabel valorActualJLabel;

    Model model;
    Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this); //el view se agrega como un listener del model asi que cuando algo
        //cambia en el model se le notifica al view y este muestra lo que cambio
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validar()) {
                    Activo a = take();
                    if(a!=null){
                        try{
                            controller.save(a);
                            JOptionPane.showMessageDialog(panel, "Datos guardados exitosamente", "",JOptionPane.INFORMATION_MESSAGE);
                            controller.clear();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { controller.clear();}
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Activo a = new Activo();
                    a.setCodigo(codigoTextField.getText());
                    controller.edit(a);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validar() {
        boolean valido = true;
        if (codigoTextField.getText().isEmpty()) {
            valido = false;
            codigoJLabel.setBorder(Application.BORDER_ERROR);
            codigoJLabel.setToolTipText("Codigo requerido");
        } else {
            codigoJLabel.setBorder(null);
            codigoJLabel.setToolTipText(null);
        }
        if (activoTextField.getText().isEmpty()) {
            valido = false;
            activoJLabel.setBorder(Application.BORDER_ERROR);
            activoJLabel.setToolTipText("Activo requerido");
        } else {
            activoJLabel.setBorder(null);
            activoJLabel.setToolTipText(null);
        }
        if (fabricacionTextField.getText().isEmpty()) {
            valido = false;
            fabricacionJLabel.setBorder(Application.BORDER_ERROR);
            fabricacionJLabel.setToolTipText("Fabricacion requerido");
        } else {
            fabricacionJLabel.setBorder(null);
            fabricacionJLabel.setToolTipText(null);
        }
        if (valorTextField.getText().isEmpty()) {
            valido = false;
            valorJLabel.setBorder(Application.BORDER_ERROR);
            valorJLabel.setToolTipText("Valor requerido");
        } else {
            valorJLabel.setBorder(null);
            valorJLabel.setToolTipText(null);
        }

        if(comboBoxCategorias.getSelectedIndex() == -1){
            valido = false;
            categoriasJLabel.setBorder(Application.BORDER_ERROR);
            categoriasJLabel.setToolTipText("Categoria requerida");
        }
        else {
            categoriasJLabel.setBorder(null);
            categoriasJLabel.setToolTipText(null);
        }
        return valido;
    }
    public Activo take() {
        try{
            Activo a = new Activo();
            a.setCodigo(codigoTextField.getText());
            a.setNombre(activoTextField.getText());
            a.setAnioFabricacion(Integer.parseInt(fabricacionTextField.getText()));
            a.setValor(Double.parseDouble(valorTextField.getText()));
            a.setCategoria((Categoria)comboBoxCategorias.getSelectedItem());
            return a;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Verifique el tipo de los valores ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
//    public void updateCategorias(List<Categoria> categorias) {
//        DefaultComboBoxModel<Categoria> combito = new DefaultComboBoxModel<>(model.getCategorias().toArray(()));
//        comboBoxCategorias.setModel(combito);
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case Model.CURRENT:
                codigoTextField.setText(model.getCurrent().getCodigo());
                valorTextField.setText(""+model.getCurrent().getValor());
                fabricacionTextField.setText(""+model.getCurrent().getAnioFabricacion());
                activoTextField.setText(model.getCurrent().getNombre());
                comboBoxCategorias.setSelectedItem(model.getCurrent().getCategoria());

                if(model.getMode()== Application.MODE_EDIT){
                    codigoTextField.setEnabled(false);
                    //consultarButton.setEnabled(true);
                }
                else{
                    codigoTextField.setEnabled(true);
                   // consultarButton.setEnabled(false);
                }
                codigoJLabel.setBorder(null);
                codigoJLabel.setToolTipText(null);
                activoJLabel.setBorder(null);
                activoJLabel.setToolTipText(null);
                fabricacionJLabel.setBorder(null);
                fabricacionJLabel.setToolTipText(null);
                valorJLabel.setBorder(null);
                valorJLabel.setToolTipText(null);
                panel.setBorder(null);
                panel.setToolTipText(null);
                categoriasJLabel.setBorder(null);
                categoriasJLabel.setToolTipText(null);
                break;
                case Model.CATEGORIAS:
                    comboBoxCategorias.setModel(new DefaultComboBoxModel(model.getCategorias().toArray()));
                    break;

        }
        this.panel.revalidate();
    }
}
