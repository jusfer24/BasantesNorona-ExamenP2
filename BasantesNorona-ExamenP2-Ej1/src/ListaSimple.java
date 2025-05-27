public class ListaSimple {
    Nodo primero;

    public ListaSimple() {
        this.primero = null;
    }

    public void insertarAlFinal(DefensorEternia dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public boolean contieneID(int id) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.dato.getID() == id) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public int size() {
        int count = 0;
        Nodo actual = primero;
        while (actual != null) {
            count++;
            actual = actual.siguiente;
        }
        return count;
    }

    public DefensorEternia get(int index) {
        int count = 0;
        Nodo actual = primero;
        while (actual != null) {
            if (count == index) return actual.dato;
            count++;
            actual = actual.siguiente;
        }
        return null;
    }

    public void set(int index, DefensorEternia dato) {
        int count = 0;
        Nodo actual = primero;
        while (actual != null) {
            if (count == index) {
                actual.dato = dato;
                return;
            }
            count++;
            actual = actual.siguiente;
        }
    }

//    public void insertarOrdenado(DefensorEternia dato) {
//        Nodo nuevo = new Nodo(dato);
//        if (primero == null || dato.getID() < primero.dato.getID()) {
//            nuevo.siguiente = primero;
//            primero = nuevo;
//        } else {
//            Nodo actual = primero;
//            while (actual.siguiente != null && actual.siguiente.dato.getID() < dato.getID()) {
//                actual = actual.siguiente;
//            }
//            nuevo.siguiente = actual.siguiente;
//            actual.siguiente = nuevo;
//        }
//    }
}
