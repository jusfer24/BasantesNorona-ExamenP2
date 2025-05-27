//Nombres de Autores: Justin Basantes y Naydelin Norona
public class DefensorEternia {
    public int ID;
    public String nombre;
    public String habilidad;
    public int poder;
    public String region;

    public DefensorEternia(int ID, String nombre, String habilidad, int poder, String region) {
        this.ID = ID;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.poder = poder;
        this.region = region;
    }

    public DefensorEternia() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String gethabilidad() {
        return habilidad;
    }

    public void sethabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getpoder() {
        return poder;
    }

    public void setpoder(int poder) {
        this.poder = poder;
    }

    public String getregion() {
        return region;
    }

    public void setregion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Defensor de Eternia: " + "\n" +
                "• ID: " + ID + "\n" +
                "• Nombre: " + nombre + "\n" +
                "• Habilidad: " + habilidad + "\n" +
                "• Poder: " + poder + "\n" +
                "• Region: " + region + "\n";
    }
}
