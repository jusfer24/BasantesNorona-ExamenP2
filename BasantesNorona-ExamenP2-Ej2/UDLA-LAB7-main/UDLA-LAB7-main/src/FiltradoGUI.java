import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class FiltradoGUI {
    private JComboBox<String> comboBoxPoder2;
    private JButton filtrarButton;
    public JPanel JPanelFiltrado;
    private JTable table1;
    private JComboBox<String> comboBoxUbicacion;
    private JButton conteoButton;
    private JTextArea textArea1;
    private ListaGuerreras listR;

    public FiltradoGUI(ListaGuerreras list) {
        listR = list;

        String[] columnas = {"ID", "Alias", "Poder", "Estrategia", "Ubicación"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        table1.setModel(modelo);

        comboBoxUbicacion.addItem("Todas");
        for (GuerreraBrightMoon g : listR.getLista()) {
            if (((DefaultComboBoxModel<String>)comboBoxUbicacion.getModel()).getIndexOf(g.getUbicacion()) == -1) {
                comboBoxUbicacion.addItem(g.getUbicacion());
            }
        }

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ubicacionSeleccionada = (String) comboBoxUbicacion.getSelectedItem();
                LinkedList<GuerreraBrightMoon> filtradas = filtrarPorUbicacion(listR.getLista(), ubicacionSeleccionada);
                LinkedList<GuerreraBrightMoon> ordenadas = ordenarPorEstrategiaDesc(filtradas);

                modelo.setRowCount(0);

                for (GuerreraBrightMoon g : ordenadas) {
                    modelo.addRow(new Object[]{
                            String.valueOf(g.getId()),
                            g.getAlias(),
                            g.getPoderBatalla(),
                            String.valueOf(g.getNivelEstrategia()),
                            g.getUbicacion()
                    });
                }
            }
        });

        conteoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ubicacionSeleccionada = (String) comboBoxUbicacion.getSelectedItem();

                if (ubicacionSeleccionada == null || ubicacionSeleccionada.equals("Todas")) {
                    textArea1.setText("Seleccione una ubicación válida para contar.");
                    return;
                }

                int total = contarPorUbicacionRec(listR.getLista(), ubicacionSeleccionada, 0);
                textArea1.setText("Total de guerreras en '" + ubicacionSeleccionada + "': " + total);
            }
        });
    }

    private static LinkedList<GuerreraBrightMoon> filtrarPorUbicacion(LinkedList<GuerreraBrightMoon> lista, String ubicacion) {
        if (ubicacion == null || ubicacion.equals("Todas")) {
            return new LinkedList<>(lista);
        }
        LinkedList<GuerreraBrightMoon> resultado = new LinkedList<>();
        for (GuerreraBrightMoon g : lista) {
            if (g.getUbicacion().equalsIgnoreCase(ubicacion)) {
                resultado.add(g);
            }
        }
        return resultado;
    }

    private static LinkedList<GuerreraBrightMoon> ordenarPorEstrategiaDesc(LinkedList<GuerreraBrightMoon> lista) {
        LinkedList<GuerreraBrightMoon> nuevaLista = new LinkedList<>(lista);
        int n = nuevaLista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nuevaLista.get(j).getNivelEstrategia() < nuevaLista.get(j + 1).getNivelEstrategia()) {
                    GuerreraBrightMoon temp = nuevaLista.get(j);
                    nuevaLista.set(j, nuevaLista.get(j + 1));
                    nuevaLista.set(j + 1, temp);
                }
            }
        }
        return nuevaLista;
    }

    private static int contarPorUbicacionRec(LinkedList<GuerreraBrightMoon> lista, String ubicacion, int index) {
        if (index >= lista.size()) return 0;
        GuerreraBrightMoon g = lista.get(index);
        if (g.getUbicacion().equalsIgnoreCase(ubicacion)) {
            return 1 + contarPorUbicacionRec(lista, ubicacion, index + 1);
        } else {
            return contarPorUbicacionRec(lista, ubicacion, index + 1);
        }
    }

    private void createUIComponents() {}
}
