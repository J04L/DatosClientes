import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class TablaClientesJFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private DefaultTableModel modeloTabla;
    private JTextField nombreText;
    private JTextField idText;
    private JTextField apellidoText;
    private JTextField edadText;
    private JTable table1;
    private JButton crearModificarButton;
    private JMenuBar menu;
    JMenuItem guardarMenuItem ;
    JMenuItem cargarMenuItem ;
    JMenuItem modificarMenuItem ;
    JMenuItem eliminarMenuItem;
    public TablaClientesJFrame(){

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Edad");

        table1.setModel(modeloTabla);

        JMenu fileMenu = new JMenu("File");
        JMenu opcionesMenu = new JMenu("Opciones");

        guardarMenuItem = new JMenuItem("Guardar");
        cargarMenuItem = new JMenuItem("Cargar");
        modificarMenuItem = new JMenuItem("Modificar");
        eliminarMenuItem = new JMenuItem("Eliminar");

        guardarMenuItem.addActionListener(this);
        cargarMenuItem.addActionListener(this);
        modificarMenuItem.addActionListener(this);
        eliminarMenuItem.addActionListener(this);

        menu.add(fileMenu);
        fileMenu.add(guardarMenuItem);
        fileMenu.add(cargarMenuItem);

        menu.add(opcionesMenu);
        opcionesMenu.add(modificarMenuItem);
        opcionesMenu.add(eliminarMenuItem);

        idText.setEditable(false);
        idText.setText(Empresa.listaClientes.size()+1 + "");

        crearModificarButton.addActionListener(this);

        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == crearModificarButton){
            if (estaTodoRellenado()) {
                int id = Empresa.listaClientes.size()+1;
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                int edad = Integer.parseInt(edadText.getText());
                Cliente cliente = new Cliente(id, nombre, apellido, edad);
                addCliente(cliente);
                limpiarTexto();
                JOptionPane.showMessageDialog(null, "Cliente añadido", "add", JOptionPane.INFORMATION_MESSAGE);

                crearModificarButton.setBackground(Color.white);
            }
            else crearModificarButton.setBackground(Color.RED);
        }
        else if(e.getSource() == guardarMenuItem){
            guardar();
        }
        else if(e.getSource() == cargarMenuItem){
            cargar();
        }
        else if(e.getSource() == modificarMenuItem){
            modificar();
        }
        else if(e.getSource() == eliminarMenuItem){
            eliminar();
        }
    }
    private void guardar(){
       try{
           BufferedWriter archivo = new BufferedWriter( new FileWriter("clientes.txt", true));
           for (Cliente cliente : Empresa.listaClientes){
               archivo.write(cliente.toString() + "\n");
           }
           JOptionPane.showMessageDialog(null, "Datos guardados", "Guardar", JOptionPane.INFORMATION_MESSAGE);
           archivo. close();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }
    private void cargar(){
        try{
            BufferedReader archivo = new BufferedReader( new FileReader("clientes.txt"));

            String line = archivo.readLine();
            while (line != null){
                String[] datos = line.split(",");
                Cliente cliente = new Cliente(Integer.parseInt(datos[0]),
                        datos[1], datos[2],
                        Integer.parseInt(datos[3]));
                addCliente(cliente);
                line = archivo.readLine();
            }
            JOptionPane.showMessageDialog(null, "Datos cargados", "File", JOptionPane.INFORMATION_MESSAGE);
            archivo.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Abrir clientes.txt", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    private void modificar(){
        int filaSeleccionada = table1.getSelectedRow();
        Vector datos = modeloTabla.getDataVector().elementAt(filaSeleccionada);
        if (filaSeleccionada !=-1){
            int respuesta = JOptionPane.showConfirmDialog(null, "Quieres modificar esta fila?", "confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION){
                Empresa.listaClientes.set(filaSeleccionada, new Cliente((int)datos.get(0),
                        (String) datos.get(1),
                        (String) datos.get(2),
                        (int)datos.get(3)));
            }
        }
    }
    private void eliminar(){
        int filaSeleccionada = table1.getSelectedRow();
        if (filaSeleccionada != -1){
            int respuesta = JOptionPane.showConfirmDialog(null, "Quieres eliminar esta fila?", "confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION){
                Empresa.listaClientes.remove(filaSeleccionada);
                modeloTabla.removeRow(filaSeleccionada);
            }
        }
    }
    private void limpiarTexto(){
        nombreText.setText("");
        apellidoText.setText("");
        edadText.setText("");
    }
    private void addCliente(Cliente cliente){
        Empresa.listaClientes.add(cliente);
        modeloTabla.addRow(new Object[]{cliente.id, cliente.nombre, cliente.apellido, cliente.edad});
        idText.setText(cliente.id+1+"");
    }
    private boolean estaTodoRellenado(){
        if (nombreText.getText().isEmpty()) return false;
        else if (apellidoText.getText().isEmpty()) return false;
        else if (edadText.getText().isEmpty()) return false;
        try {
            Integer.parseInt(edadText.getText());
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
