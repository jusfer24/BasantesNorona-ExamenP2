import javax.swing.*;
import java.util.LinkedList;

// Literal 1: Estructura y Registro de Guerreras
public class ListaGuerreras {
    // Lista doblemente enlazada para almacenar guerreras
    private LinkedList<GuerreraBrightMoon> lista = new LinkedList<>();

    // (1b) Agrega una guerrera si su ID no existe ya en la lista
    // Inserta la guerrera en orden creciente de ID
    public boolean agregar(GuerreraBrightMoon g) {
        for (GuerreraBrightMoon guerrera : lista) {
            if (guerrera.getId() == g.getId()) {
                return false; // Ya existe una guerrera con ese ID
            }
        }
        // Inserta ordenado por ID
        int i = 0;
        while (i < lista.size() && lista.get(i).getId() < g.getId()) {
            i++;
        }
        lista.add(i, g);
        return true;
    }

    // (1b) Elimina una guerrera por su ID, mostrando mensaje en un JTextArea
    public void eliminar(int id, JTextArea salida) {
        boolean encontrada = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                lista.remove(i);
                salida.append("Guerrera eliminada.\n");
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            salida.append("Guerrera no encontrada.\n");
        }
    }

    // (2a) Búsqueda binaria: este metodo devuelve el objeto guerrera por su ID
    public GuerreraBrightMoon buscarPorID(int id) {
        for (GuerreraBrightMoon g : lista) {
            if (g.getId() == id) return g;
        }
        return null;
    }

    // (2a) Metodo auxiliar de búsqueda, devuelve índice de la guerrera en la lista
    public int buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) return i;
        }
        return -1;
    }

    // (3c) Ordena la lista por nivel de estrategia de forma descendente (metodo selección)
    public void ordenarPorNivelEstrategia(JTextArea salida) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).getNivelEstrategia() > lista.get(maxIdx).getNivelEstrategia()) {
                    maxIdx = j;
                }
            }
            GuerreraBrightMoon temp = lista.get(i);
            lista.set(i, lista.get(maxIdx));
            lista.set(maxIdx, temp);
        }
        salida.append("Lista ordenada por Nivel de Estrategia.\n");
    }


    public void mostrarLista(JTextArea salida) {
        salida.setText("");
        for (GuerreraBrightMoon g : lista) {
            salida.append("ID: " + g.getId() + ", Alias: " + g.getAlias() +
                    ", Poder: " + g.getPoderBatalla() + ", Estrategia: " +
                    g.getNivelEstrategia() + ", Ubicación: " + g.getUbicacion() + "\n");
        }
    }


    public String formatoLista() {
        StringBuilder sb = new StringBuilder();
        for (GuerreraBrightMoon g : lista) {
            sb.append("ID: ").append(g.getId()).append("\n")
                    .append("Alias: ").append(g.getAlias()).append("\n")
                    .append("Poder: ").append(g.getPoderBatalla()).append("\n")
                    .append("Estrategia: ").append(g.getNivelEstrategia()).append("\n")
                    .append("Ubicación: ").append(g.getUbicacion()).append("\n\n");
        }
        return sb.toString();
    }

    // (3b) Filtra guerreras por poder de batalla (ComboBox) — FALTA implementar
    public LinkedList<GuerreraBrightMoon> filtrarPorPoder(String poder) {
        LinkedList<GuerreraBrightMoon> filtradas = new LinkedList<>();
        for (GuerreraBrightMoon g : lista) {
            if (g.getPoderBatalla().equalsIgnoreCase(poder)) {
                filtradas.add(g);
            }
        }
        return filtradas;
    }

    // (4a) Metodo público para iniciar el conteo recursivo por ubicación
    public int contarPorUbicacion(String ubicacion) {
        return contarPorUbicacion(ubicacion, 0);
    }

    // (4a) Metodo privado recursivo sin acumuladores globales
    private int contarPorUbicacion(String ubicacion, int index) {
        if (index >= lista.size()) return 0;
        GuerreraBrightMoon g = lista.get(index);
        int suma = g.getUbicacion().equals(ubicacion) ? 1 : 0;
        return suma + contarPorUbicacion(ubicacion, index + 1);
    }

    // Devuelve la lista completa
    public LinkedList<GuerreraBrightMoon> getLista() {
        return lista;
    }
}
