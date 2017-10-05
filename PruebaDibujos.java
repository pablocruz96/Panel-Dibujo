//Prueba del Panel de Dibujo

package tareas;

//Importando las Bibliotecas a Ocupar
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

//Clase Principal
public final class PruebaDibujos {

    private final JFrame ventana;   //Creando la Ventana
    private final JPanel panelNorte;//Creando Panel Superior
    private final PanelDibujo panel;//Creando Panel de Dibujo
    private final JLabel posicion;  //Creando Label indicando posicion del mouse
    
    //Clase Manejadora
    public PruebaDibujos(){
        ventana = new JFrame("Ventana de dibujo");//Nombrando la Ventana
        panelNorte = new JPanel();  //Declarando Panel Superior
        ventana.setIconImage(       //Agregando un icono a la Ventana
            new ImageIcon(getClass().getResource("paint.png")).getImage());
        posicion = new JLabel("");  //Declarando Label
        panel = new PanelDibujo();  //Declarando Panel de Dibujo
        
        //Metodos a Llamar para crear la ventana
        armado();
        asignar();
        personalizar();
        mostrar();
    }//Fin del Constructor
    
    //Metodo armado
    public void armado(){
        ventana.setLayout(new BorderLayout());   //Declarando Marco a la Ventana
        
        //Agregando componentes a la Ventana
        ventana.add(panel, BorderLayout.CENTER);
        ventana.add(posicion, BorderLayout.SOUTH);
        ventana.add(panelNorte, BorderLayout.NORTH);
        
    }//Fin de Metodo armado
    
    //Metodo personalizar
    public void personalizar(){
        ventana.setSize(800, 600);          //Declarando Tamaño a la Ventana
        ventana.setLocationRelativeTo(null);//
        ventana.setResizable(false);     //Hacer la Ventana no espandible
        panel.setBackground(Color.WHITE);//Declarando color al Fondo del panel
    }//Fin de Metodo personalizar
    
    //Metodo asignar
    public void asignar(){
        
        //Agregando Oyende de Mouse
        panel.addMouseMotionListener(new posicionMouse());
    }//Fin del Metodo asignar
    
    //Metodo mostrar
    public void mostrar(){
        ventana.setVisible(true);//Haciendo la Ventana Visible
        
        //Haciendo la Ventana Cerrable
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Clase Interna posicionMouse con implementacion de un oyente
    public class posicionMouse implements MouseMotionListener{

        //Metodo mouseDragged
        @Override
        public void mouseDragged(MouseEvent me) {
            
            //Texto a mostrar del Label
            posicion.setText("(" + me.getX() + ", " + me.getY() + ")");
        }//Fin del metodo mouseDragged

        //Metodo mouseMoved
        @Override
        public void mouseMoved(MouseEvent me) {
            
            //Texto a mostrar del Label
            posicion.setText("(" + me.getX() + ", " + me.getY() + ")");
        }//Fin del metodo mouseMoved
    }//Fin de la Clase Interna posicionMouse
    
    //Metodo main
    public static void main(String[] args) {
        try {} catch (Exception e) {} //Exception para Evitar Errores al Dibujar
       PruebaDibujos v = new PruebaDibujos();//Llamando a la clase PruebaDibujos
    }//Fin Metodo main
}//Fin de la Clase Principal

/* * * * * * * * * * * * * * * * * * * * * * *
 *  Instituto Tecnologico de Leon            *
 *  Ingenieria en Sistemas Computacionales   *
 *  Intengrantes:                            *
 *  Olvera Rivera Maria Josefina             *
 *  Ramos Zuñiga Amado                       *
 *  Cruz Meza Pablo Antonio                  *
 *  Fecha de Entrega: 06/10/17               *
 *  Nombre del trabajo: Panel de Dibujo      *
 * * * * * * * * * * * * * * * * * * * * * * */