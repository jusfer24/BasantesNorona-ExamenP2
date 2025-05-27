import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class RegistroGUI {
    public JPanel JPanelRegistro;
    private JComboBox<String> comboBoxPoder;
    private JComboBox<String> comboBoxEstrategia;
    private JComboBox<String> comboBoxUbicacion;
    private JButton agregarGuerrerasButton;
    private JTextArea textAreaRResultado;
    private JTextField txtID;
    private JTextField txtAlias;
    private JTable tableResultado;
    private JTextField textField1;
    private JButton filtrarButton;
    private JButton buscarButton;

    public ListaGuerreras listaGuerreras = new ListaGuerreras();
    private DefaultTableModel modeloTabla;

    public RegistroGUI() {
        // Inicializa el modelo de la tabla
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Alias", "Poder", "Estrategia", "Ubicación"}, 0
        );
        tableResultado.setModel(modeloTabla);

        // Cargar guerreras de ejemplo
        cargarGuerrerasEjemplo();
        llenarTabla();

        agregarGuerrerasButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtID.getText().trim());
                String alias = txtAlias.getText().trim();
                String poder = (String) comboBoxPoder.getSelectedItem();
                int estrategia = Integer.parseInt((String) comboBoxEstrategia.getSelectedItem());
                String ubicacion = (String) comboBoxUbicacion.getSelectedItem();

                if (alias.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Alias no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (listaGuerreras.buscarPorID(id) != null) {
                    JOptionPane.showMessageDialog(null, "Ya existe una guerrera con ese ID.", "ID Duplicado", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                GuerreraBrightMoon guerrera = new GuerreraBrightMoon(id, alias, poder, estrategia, ubicacion);
                listaGuerreras.agregar(guerrera);
                llenarTabla();
                JOptionPane.showMessageDialog(null, "Guerrera agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        });



        buscarButton.addActionListener(e -> {
            try {
                int idBuscado = Integer.parseInt(textField1.getText().trim());
                GuerreraBrightMoon g = listaGuerreras.buscarPorID(idBuscado);


                if (g != null) {

                    JOptionPane.showMessageDialog(null, "Guerrera encontrada:\n" + g);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró una guerrera con ID " + idBuscado, "No encontrada", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un ID numérico válido para buscar.");
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("FiltradoGUI");
                frame.setContentPane(new FiltradoGUI(listaGuerreras).JPanelFiltrado);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private void llenarTabla() {
        modeloTabla.setRowCount(0);
        for (GuerreraBrightMoon g : listaGuerreras.getLista()) {
            modeloTabla.addRow(new Object[]{
                    String.valueOf(g.getId()), g.getAlias(), g.getPoderBatalla(),
                    String.valueOf(g.getNivelEstrategia()), g.getUbicacion()
            });
        }
    }

    private void cargarGuerrerasEjemplo() {
        listaGuerreras.agregar(new GuerreraBrightMoon(201, "Adora", "💫 Luz Curativa", 9, "🌙 Fortaleza de Bright Moon"));
        listaGuerreras.agregar(new GuerreraBrightMoon(202, "Glimmer", "💥 Furia de Cristal", 8, "🗼 Torre de la Luna"));
        listaGuerreras.agregar(new GuerreraBrightMoon(203, "Bow", "🔬 Tecnología Rebelde", 7, "🌲 Whispering Woods"));
        listaGuerreras.agregar(new GuerreraBrightMoon(204, "Perfuma", "🌪️ Manipulación del Clima", 6, "💎 Torre de Cristal"));
        listaGuerreras.agregar(new GuerreraBrightMoon(205, "Frosta", "🕶️ Sigilo Total", 8, "🌊 Bahía del Espejo"));
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de Guerreras");
        frame.setContentPane(new RegistroGUI().JPanelRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        frame.setVisible(true);
    }
}
