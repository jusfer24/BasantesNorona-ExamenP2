import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registro_form {
    private JTextField ID_field;
    private JTextField name_field;
    private JComboBox habilidad_box;
    private JSpinner power_spinner;
    private JComboBox region_box;
    private JButton add_button;
    private JTable defensores_table;
    private JPanel JPanel_general;
    private JScrollPane defensores_scroll;
    private JTextField buscar_field;
    private JButton buscar_buttton;
    private JButton Ir_button;

    public registro_form() {
        ListaSimple list = new ListaSimple();

        DefensorEternia d1 = new DefensorEternia(201, "He-Man", "⚔️ Espada de Poder", 10, "🏰 Castillo Grayskull");
        DefensorEternia d2 = new DefensorEternia(202, "Man-At-Arms", "🔧 Tecnología Avanzada", 8, "👑 Villa Real");
        DefensorEternia d3 = new DefensorEternia(203, "Teela", "🧠 Estrategia Bélica", 9, "🌳 Bosque de la Eternidad");
        DefensorEternia d4 = new DefensorEternia(204, "Sorceress", "✨ Magia Ancestral", 9, "🏰 Castillo Grayskull");
        DefensorEternia d5 = new DefensorEternia(205, "Stratos", "🛡️ Fuerza Titanes", 7, "🌪️ Torre del Viento");
        DefensorEternia d6 = new DefensorEternia(206, "Roboto", "🔧 Tecnología Avanzada", 8, "🏔️ Montañas Místicas");
        DefensorEternia d7 = new DefensorEternia(207, "Buzz-Off", "🧠 Estrategia Bélica", 7, "🌳 Bosque de la Eternidad");
        DefensorEternia d8 = new DefensorEternia(208, "Clamp Champ", "🛡️ Fuerza Titanes", 8, "👑 Villa Real");
        DefensorEternia d9 = new DefensorEternia(209, "Zodac", "✨ Magia Ancestral", 9, "🏔️ Montañas Místicas");
        DefensorEternia d10 = new DefensorEternia(210, "King Randor", "🧠 Estrategia Bélica", 8, "👑 Villa Real");

        list.insertarAlFinal(d1);
        list.insertarAlFinal(d2);
        list.insertarAlFinal(d3);
        list.insertarAlFinal(d4);
        list.insertarAlFinal(d5);
        list.insertarAlFinal(d6);
        list.insertarAlFinal(d7);
        list.insertarAlFinal(d8);
        list.insertarAlFinal(d9);
        list.insertarAlFinal(d10);

        // Definir columnas y modelo
        String[] columnas = {"ID", "Nombre", "Habilidad", "Poder", "Region"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        defensores_table.setModel(modelo);

        for (int i = 0; i < list.size(); i++) {
            DefensorEternia d = list.get(i);
            modelo.addRow(new Object[]{
                    d.getID(),
                    d.getnombre(),
                    d.gethabilidad(),
                    d.getpoder(),
                    d.getregion()
            });
        }

        power_spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1)); //Set de las configuraciones del JSpinner
        ((JSpinner.DefaultEditor) power_spinner.getEditor()).getTextField().setEditable(false); //Modificacion de sus estados

        JComponent editor = power_spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) { //Comprueba si el editor es instancia del JSpinner de default
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor; //Instancia las configuraciones del editor
            spinnerEditor.getTextField().setBackground(new Color(0, 0, 0));
            spinnerEditor.getTextField().setForeground(new Color(253, 241, 0));
        }

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefensorEternia vn = new DefensorEternia(
                        Integer.parseInt(ID_field.getText()), //ID del Defensor
                        name_field.getText(), //Nombre del Defensor
                        (String) habilidad_box.getSelectedItem(), //Habilidad del Defensor
                        (int) power_spinner.getValue(), //Poder del defensor
                        (String) region_box.getSelectedItem() //Region del defensor
                );

                if (list.contieneID(vn.getID())) {
                    JOptionPane.showMessageDialog(null,"Error: Ya existe un defensor con el ID " + vn.getID(), "ID Existente", JOptionPane.ERROR_MESSAGE);
                } else {
                    list.insertarAlFinal(vn);
                    modelo.setRowCount(0);
                    for (int i = 0; i < list.size(); i++) {
                        DefensorEternia d = list.get(i);
                        modelo.addRow(new Object[]{
                                d.getID(),
                                d.getnombre(),
                                d.gethabilidad(),
                                d.getpoder(),
                                d.getregion()
                        });
                    }
                    JOptionPane.showMessageDialog(null,"Defensor insertado correctamente", "Exito en la insercion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buscar_buttton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busquedalineal(list, buscar_field.getText());
            }
        });

        Ir_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Filtrado_form");
                frame.setContentPane(new Filtrado_form(list).JPanel_orden);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void busquedalineal(ListaSimple lista, String valor) {
        int idBuscado = Integer.parseInt(valor);
        Nodo actual = lista.primero;
        boolean existe=true;

        for (int i = 0; i < lista.size(); i++) {
            if (actual.dato.getID() == idBuscado) {
                JOptionPane.showMessageDialog(null,actual.dato);
                existe=false;
            }
            actual = actual.siguiente;
        }
        if (existe){
            JOptionPane.showMessageDialog(null,"No existe ese defensor con ese ID","Error al encontrar Defensor", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro_Form");
        frame.setContentPane(new registro_form().JPanel_general);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
