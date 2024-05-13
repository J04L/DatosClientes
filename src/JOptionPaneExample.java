import javax.swing.JOptionPane;

public class JOptionPaneExample {
    public static void main(String[] args) {
        // Mostrar un mensaje de diálogo de información
        JOptionPane.showMessageDialog(null, "Este es un mensaje de diálogo de información", "Información", JOptionPane.INFORMATION_MESSAGE);

        // Mostrar un mensaje de diálogo de advertencia
        JOptionPane.showMessageDialog(null, "Este es un mensaje de diálogo de advertencia", "Advertencia", JOptionPane.WARNING_MESSAGE);

        // Mostrar un mensaje de diálogo de error
        JOptionPane.showMessageDialog(null, "Este es un mensaje de diálogo de error", "Error", JOptionPane.ERROR_MESSAGE);

        // Mostrar un mensaje de diálogo de pregunta con opciones de sí/no
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.out.println("El usuario eligió Sí");
        } else {
            System.out.println("El usuario eligió No");
        }

        // Mostrar un mensaje de diálogo de entrada de texto
        String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:");
        if (nombre != null) {
            System.out.println("El nombre ingresado es: " + nombre);
        } else {
            System.out.println("No se ingresó ningún nombre.");
        }
    }
}
