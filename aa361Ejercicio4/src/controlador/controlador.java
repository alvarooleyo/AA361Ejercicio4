package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//se importa modelo e interfaz
import modelo.modelo;
import vista.interfaz;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class controlador implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    interfaz vista ;
    
    /** instancia a nuestro modelo */
    modelo modelo = new modelo();

    /** Se declaran en un ENUM las acciones que se realizan desde la
     * interfaz de usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC
    {
        agregar, nuevo, mostrar, buscar, aceptar
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public controlador( interfaz vista )
    {
        this.vista = vista;
    }

    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btnAgregar.setActionCommand( "agregar" );
        this.vista.btnAgregar.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btnNuevo.setActionCommand( "nuevo" );
        this.vista.btnNuevo.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btnBuscar.setActionCommand( "buscar" );
        this.vista.btnBuscar.addActionListener(this);
        
        this.vista.btnAceptar.setActionCommand("aceptar");
        this.vista.btnAceptar.addActionListener(this);
    }

    //Eventos que suceden por el mouse
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tabla.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtNombre.setText( String.valueOf( this.vista.tabla.getValueAt(fila, 1) ));
                this.vista.txtPaterno.setText( String.valueOf( this.vista.tabla.getValueAt(fila, 2) ));
                this.vista.txtMaterno.setText( String.valueOf( this.vista.tabla.getValueAt(fila, 3) ));
                this.vista.txtEmail.setText( String.valueOf( this.vista.tabla.getValueAt(fila, 4) ));
             }
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) { }
 
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
           
            case agregar:
                //añade un nuevo registro
                if ( this.modelo.agregaPersona(
                        this.vista.txtNombre.getText(),
                        this.vista.txtPaterno.getText() ,
                        this.vista.txtMaterno.getText(),
                        this.vista.txtEmail.getText() ) )
                {
                    this.vista.tabla.setModel( this.modelo.getTablaPersona());
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                    this.vista.txtNombre.setText("");
                    this.vista.txtPaterno.setText("") ;
                    this.vista.txtMaterno.setText("");
                    this.vista.txtEmail.setText("");
                    
                    this.vista.tabla.setModel(this.modelo.getTablaPersona());
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                
                break;
            
            case nuevo: 
                
                this.vista.txtNombre.setText("");
                this.vista.txtPaterno.setText("") ;
                this.vista.txtMaterno.setText("");
                this.vista.txtEmail.setText("");
                
        }
    }

}
