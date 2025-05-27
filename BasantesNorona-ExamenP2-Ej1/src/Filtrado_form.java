import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Filtrado_form {
    private JComboBox regionb_box;
    private JTable orden_table;
    private JButton buscar_button2;
    private JScrollPane orden_scroll;
    public JPanel JPanel_orden;
    private JComboBox habilidad_box2;
    private JButton buscar_button3;
    private JTextArea conthab_text;
    private ListaSimple listR;

    public Filtrado_form(ListaSimple list) {
        String[] columnas = {"ID", "Nombre", "Habilidad", "Poder", "Region"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        orden_table.setModel(modelo);

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

        buscar_button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar tabla
                modelo.setRowCount(0);
                listR = filtrarYOrdenarPorPoder(list, (String) regionb_box.getSelectedItem());
                for (int i = 0; i < listR.size(); i++) {
                    DefensorEternia d = listR.get(i);
                    modelo.addRow(new Object[]{
                            d.getID(),
                            d.getnombre(),
                            d.gethabilidad(),
                            d.getpoder(),
                            d.getregion()
                    });
                }
            }
        });

        buscar_button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conthab_text.setText("El total de defensores que tienen esa habilidad son " + contarRec(list, (String) habilidad_box2.getSelectedItem(), 0));
            }
        });
    }

    public static ListaSimple filtrarYOrdenarPorPoder(ListaSimple lista, String regionExcluida) {
        ListaSimple nuevaLista = new ListaSimple();

        for (int i = 0; i < lista.size(); i++) {
            DefensorEternia d = lista.get(i);
            if (d.getregion().equals(regionExcluida)) {
                nuevaLista.insertarAlFinal(d);
            }
        }
        bubbleSort(nuevaLista);
        return nuevaLista;
    }

    public static void bubbleSort(ListaSimple list) { //De manera Ascendente
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getpoder() < list.get(j + 1).getpoder()) {
                    // Intercambiar arr[j] y arr[j + 1]
                    DefensorEternia aux = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, aux);
                }
            }
        }
    }

    private static int contarRec(ListaSimple lista, String habilidad, int index) {
        if (index >= lista.size()) return 0;

        DefensorEternia v = lista.get(index);
        if (v.gethabilidad().equals(habilidad)) {
            return 1 + contarRec(lista, habilidad, index + 1); //Recursividad
        } else {
            return contarRec(lista, habilidad, index + 1); //Recursividad
        }
    }

}
