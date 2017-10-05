//Crear un Panel de Dibujo

package tareas;

//Importando Bibliotecas
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

//Clase Principal
public class PanelDibujo extends JPanel{
    
    //Creando Componentes y Atributos a ocupar
    private final Point[] puntos = new Point[10000];
    private final Point[] puntos2 = new Point[10000];
    private final JComboBox jcbColor, jcbFig;
    private final JCheckBox jcbFill;
    private final String colores[] = 
        {"Negro", "Rojo", "Azul", "Verde", "Rosa", "Amarillo", "Naranja"};
    private final String figuras[] = {"Linea", "Rectangulo/Cuadrado", "Circulo"};
    private final String rellenar[] = {"No", "Si"};
    private final String colorFigura[] = new String[10000];
    private final String formaFigura[] = new String[10000];
    private final String rellenarFigura[] = new String[10000];
    private final JButton und, clear;
    private final JPanel pnorte;
    public int contador = 0;
    
    //Metodo Constructor
    public PanelDibujo(){
        
       //Declarando Componentes con algunos atributos
       und = new JButton("Undo");
       clear = new JButton("Clear");
       jcbColor = new JComboBox(colores);
       jcbFill = new JCheckBox("Rellenar Figura");
       jcbFig = new JComboBox(figuras);
       pnorte = new JPanel();
       
       //Agregando Componentes al panel
       pnorte.add(und);
       pnorte.add(clear);
       pnorte.add(jcbFig);
       pnorte.add(jcbColor);
       pnorte.add(jcbFill);
       
       add(pnorte, BorderLayout.NORTH);//Agregando Panel a la Ventana
       pnorte.setLayout(new GridLayout(1, 6, 10, 10));//Declarando Marco a Panel
       
       //Agregando Oyentes de Eventos
       addMouseMotionListener(new Evento());
       addMouseListener(new Event());
       und.addActionListener(new undo());
       clear.addActionListener(new clear());
    }//Fin del Metodo Constructor
    
    //Metodo paintComponent servira para dibujar en el panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Contador que nos servira para dibujar en el panel
        for (int i = 0; i < contador; i++) {
            color(colorFigura[i], g);
            if (puntos[i].x > puntos2[i].x) {
                if (puntos[i].y > puntos2[i].y) {
                    if (formaFigura[i].equals("Linea")) {
                        figura(formaFigura[i], g, puntos[i].x, puntos[i].y, 
                        puntos2[i].x, puntos2[i].y, rellenarFigura[i]);
                    } else {
                       figura(formaFigura[i], g, puntos2[i].x, puntos2[i].y,
                       Math.abs(puntos2[i].x - puntos[i].x),
                       Math.abs(puntos[i].y - puntos2[i].y),rellenarFigura[i]);
                    }
                } else {
                    if (formaFigura[i].equals("Linea")) {
                        figura(formaFigura[i], g, puntos[i].x, puntos[i].y,
                        puntos2[i].x, puntos2[i].y, rellenarFigura[i]);
                    } else {
                       figura(formaFigura[i], g, puntos2[i].x, puntos[i].y,
                       Math.abs(puntos2[i].x - puntos[i].x),
                       Math.abs(puntos[i].y - puntos2[i].y), rellenarFigura[i]);
                    }
                }
            } else {
                if (puntos[i].y > puntos2[i].y) {
                    if (formaFigura[i].equals("Linea")) {
                        figura(formaFigura[i], g, puntos[i].x, puntos[i].y,
                        puntos2[i].x, puntos2[i].y, rellenarFigura[i]);
                    } else {
                       figura(formaFigura[i], g, puntos[i].x, puntos2[i].y,
                       Math.abs(puntos2[i].x - puntos[i].x),
                       Math.abs(puntos[i].y - puntos2[i].y), rellenarFigura[i]);
                    }
                } else {
                    if (formaFigura[i].equals("Linea")) {
                        figura(formaFigura[i], g, puntos[i].x, puntos[i].y,
                        puntos2[i].x, puntos2[i].y, rellenarFigura[i]);
                    } else {
                       figura(formaFigura[i], g, puntos[i].x, puntos[i].y,
                       Math.abs(puntos2[i].x - puntos[i].x),
                       Math.abs(puntos[i].y - puntos2[i].y), rellenarFigura[i]);
                    }
                }
            }
        }
    }//Fin del Metodo paintComponent
    
    //Metodo figura para dibujar una figura en el panel
    private void figura(String fig, Graphics g, int a,
                        int b, int c, int d, String fi){
        
        //Switch para elegir Figuras
        switch(fig){
            case "Linea": g.drawLine(a, b, c, d); break;//Dibujando una Linea
            case "Rectangulo/Cuadrado":     //Dibujando un rectangulo
                if (fi.equals("Si")) {
                    g.fillRect(a, b, c, d);
                } else {
                    g.drawRect(a, b, c, d);
                }
                break;
            case "Circulo":                 //Dibujando una Circuferencia
                if (fi.equals("Si")) {
                    g.fillOval(a, b, c, d);
                } else {
                    g.drawOval(a, b, c, d);
                }
                break;
        }
    }//Fin del Metodo figura
    
    //Metodo color para declarar clores al dibujar
    private void color(String color, Graphics g){
        
        //Switch para declarar un color al dibujar
        switch(color){
            case "Negro": g.setColor(Color.BLACK); break;
            case "Rojo": g.setColor(Color.RED); break;
            case "Azul": g.setColor(Color.BLUE); break;
            case "Verde": g.setColor(Color.GREEN); break;
            case "Rosa": g.setColor(Color.PINK); break;
            case "Amarillo": g.setColor(Color.YELLOW); break;
            case "Naranja": g.setColor(Color.ORANGE); break;
        }
    }//Fin del Metodo color
    
    //Clase Interna Adaptadora Event
    private class Event extends MouseAdapter{
        
        //Metoso mousePressed
        @Override
        public void mousePressed(MouseEvent me){
            
            //Contador de puntos
            if (contador < puntos.length) {
                puntos[contador] = me.getPoint();
                
                //Formando Figura
                formaFigura[contador] = jcbFig.getSelectedItem().toString();
                
                //Declarando un color
                colorFigura[contador] = jcbColor.getSelectedItem().toString();
                
                //Declarar la figura si se rellena o no
                rellenarFigura[contador] = 
                    jcbFill.isSelected()?rellenar[1]:rellenar[0];
                
                //Contando puntos
                contador++;
            }
        }//Fin del Metodo mousePressed
        
        //Metodo mouseRelased
        @Override
        public void mouseReleased(MouseEvent me){
            
            //Decicion para dibujar en el Panel
            if (contador < puntos.length) {
                puntos2[contador - 1] = me.getPoint();                
                repaint();
            }
        }//Fin del Metodo mouseReleased
    }//Fin de la Clase Interna Adaptadora Event
    
    //Clase Interna Adaptadora Evento
    private class Evento extends MouseMotionAdapter{
        
        //Metodo mouseDragged
        @Override
        public void mouseDragged(MouseEvent me) {
            
            //Decicion para dibujar en el Panel
            if (contador < puntos.length) {
                puntos2[contador - 1] = me.getPoint();
                repaint();
            }
        }//Fin del Metodo mouseDragged
    }//Fin de la Clase Interna Adaptadora Evento
    
    //Clase Interna undo
    private class undo implements ActionListener{

        //Metodo actionPerformed
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            //Decicion para borrar la ultima accion que realizo sobre el panel
            if (contador == 0) {
                repaint();
            } else {
                contador--;
                repaint();
            }
        }//Fin del Metodo actionPerformed
    }//Fin de la Clase Interna undo
    
    //Clase Interna clear
    private class clear implements ActionListener{

        //Metodo actionPerformed
        @Override
        public void actionPerformed(ActionEvent ae) {
            contador = 0;//Borrar todoas las acciones que realizaron el en panel
            repaint();
        }//Fin del Metodo actionPerformed
    }//Fin de la Clase Interna clear
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