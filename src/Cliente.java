public class Cliente {
    int id;
    String nombre;
    String apellido;
    int edad;

    public Cliente(int id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return id+","+nombre+","+apellido+","+edad;
    }
}
