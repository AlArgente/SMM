/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.iu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import sm.aadcg.graficos.Shape;
import java.util.ArrayList;
import sm.aadcg.graficos.Atributtes;
import sm.aadcg.graficos.CurvaSimple;
import sm.aadcg.graficos.Ellipse;
import sm.aadcg.graficos.Line;
import sm.aadcg.graficos.OwnShape;
import sm.aadcg.graficos.Rectangulo;
import sm.aadcg.graficos.TrazoLibre;

/**
 * Clase Lienzo que hereda de JPanel. Esta clase es utilizada para dibujar
 * figuras con todos sus atributos, y para controlar los eventos del Lienzo para
 * así poder dibujar las figuras, ya sean por un paso, dos pasos o tantos como
 * lo requiera la figura. Además se han creado variables auxiliares para poder
 * marcar en la ventana principal qué tipo de figura se está dibujando, si 
 * se está o no aplicando la transparencia, suavizado, etc...
 * @author alberto
 */
public class Lienzo extends javax.swing.JPanel {
    /**
     * Array de figuras para guardar todas las figuras que dibujemos
     */
    private ArrayList<Shape> vShape = new ArrayList();
    /**
     * Variable de tipo Shape (propia) que servirá como auxiliar para poder
     * acceder a los atributos de la figura y mover la figura
     */
    private Shape s;
    /**
     * Variables para definir el clipArea
     */
    private int s_x, s_y = 300;
    /**
     * Variable para definir la zona de dibujo
     */
    private Rectangle2D.Float clipArea = new Rectangle2D.Float(0,0,s_x,s_y);
    /**
     * Variable que indica el modo de dibujo seleccionado, por defecto el trazo,
     * o modo libre
     */
    private String _modo_dibujo = "trazo";
    /**
     * Variable que indica el tipo de trazo que se está realizando, por defecto
     * continuo
     */
    private String _modo_stroke = "continuo";
    /**
     * Variable para almacenar los atributos e inicializar las figuras
     */
    private Atributtes atr;
    /**
     * Variable para saber si estamos seleccionando una imagen o no
     */
    private boolean _editar = false;
    /**
     * Variable que determina si se está aplicando suavizado o no sobre la figura
     */
    private boolean suavizado = false;
    /**
     * Variable que determina si se está aplicando o no la transparencia sobre
     * la figura
     */
    private boolean transp = false;
    /**
     * Variable que determina si se dibuja el relleno o no 
     */
    private boolean fill = false;
    /**
     * Punto auxiliar que se usará para poder mover la figura desde donde el 
     * usuario haya clickado
     */
    private Point pAux;
    /**
     * Variable auxiliar de tipo Line, para poder definir líneas y dibujarlas
     * y poder cambiar sus atributos y moverla
     */
    private Line l;
    /**
     * Variable auxiliar de tipo Rectangulo, para poder crear un rectángulo
     * y posteriormente pintarlo y poder cambiar sus atributos y moverlo
     */
    private Rectangulo r;
    /**
     * Variable auxiliar de tipo ellipse, para poder crear una ellipse y
     * posteriormente pintarla y poder cambiar sus atributos y moverla
     */
    private Ellipse e;
    /**
     * Variable Auxiliar de Trazo libre, para poder crear la figura de TrazoLibre,
     * y poder pintarla, cambiar sus atributos y moverla
     */
    private TrazoLibre t;
    /**
     * Variable auxiliar para poder crear los atributos con un color de trazo,
     * por defecto a BLACK
     */
    private Color c = Color.BLACK;
    /**
     * Variable auxiliar para poder crear los atributos con un color de relleno,
     * por defecto a BLACK
     */
    private Color c_fill = Color.BLACK;
    /**
     * Variable auxiliar que indica el grosor con el que se están pintando las 
     * figuras
     */
    private int grosor = 1;
    /**
     * Variable auxiliar que indica el grado de transparencia que se está
     * aplicando sobre las figuras dibujadas
     */
    private float transparencia = 0.5F;
    /**
     * Variable auxiliar para poder dibujar la curva con un punto de control
     */
    private int cnt_curva = 0;
    /**
     * Variable auxiliar de tipo CurvaSimple para poder dibujar la curva de 
     * control y cambiar sus atributos y moverla
     */
    private CurvaSimple curv;
    /**
     * Variable auxiliar de tipo OwnShape para poder dibujar la figura personalizada,
     * y poder moverla y modificar sus atributos.
     */
    private OwnShape area;
    /**
     * Setter, para hacer un set del tamaño del clipArea
     * @param x valor x de la esquina inferior derecha
     * @param y valor y de la esquina inferior derecha
     */
    public void set_size(int x, int y) {
        this.s_x = x;
        this.s_y = y;
    }
    /**
     * Setter, en función del stroke que se asigne, la figura se creará con un
     * trazo continuo o un trazo discontinuo
     * @param stroke tipo de stroke
     */
    public void set_mode_stroke(String stroke) {
        this._modo_stroke = stroke;
        if (s!=null){
            if (s.get_atr().get_modo_stroke()=="discontinuo") {
                this.s.get_atr().set_modo_stroke(_modo_stroke);
            } else {
                this.s.get_atr().set_modo_stroke(_modo_stroke);
            }
        }
    }
    /**
     * Getter
     * @return devuelve el tipo de trazo con el que se están dibujando
     * las figuras
     */
    public String get_mode_stroke(){
        return this._modo_stroke;
    }
    /**
     * Método para resetear la variable auxiliar para dibujar la curva
     * con un punto de control
     */
    public void reset_cnt_curva() {
        this.cnt_curva = 0;
    }
    /**
     * Setter, método para indicar que se aplica el relleno en la figura 
     * seleccionada
     * @param f true||false
     */
    public void set_fill(boolean f) {
        this.fill = f;
        if (s!=null)this.s.get_atr().set_fill(f);
    }
    /**
     * Getter
     * @return devuelve si se está o no dibujando el relleno en las figuras. 
     */
    public boolean get_fill() {
        return (s!=null)?s.get_atr().get_fill():this.fill;
    }
    /**
     * Setter, método para aplicar el suavizado sobre la figura seleccionada,
     * si existe, o sobre las que se van a dibujar
     * @param a true||false
     */
    public void set_alisar(boolean a) {
        this.suavizado = a;
        if (s!=null) this.s.get_atr().set_smooth(a);
    }
    /**
     * Getter
     * @return devuelve si se está aplicando el suavizado sobre la figura 
     * seleccionada
     */
    public boolean get_alisar() {
        return (s!=null)?s.get_atr().get_smooth():this.suavizado;
    }
    /**
     * Setter, método para indicar el tipo de grosor a dibujar las nuevas 
     * figuras, y para aplicarlo en la figura seleccionada
     * @param g grosor
     */
    public void set_grosor(int g) {
        this.grosor = g;
        if (s!=null) this.s.get_atr().set_thickness(g);
    }
    /**
     * Getter
     * @return el grososr con el que se está pintando las figuras
     */
    public int get_grosor() {
        return (s!=null)?this.s.get_atr().get_thickness():this.grosor;
    }
    /**
     * Setter, método para aplicar mayor o menor grado de transparencia a la 
     * figura seleccionada o a las figuras que serán pintadas
     * @param f grado de transparencia [0,1]
     */
    public void set_transparencia(float f) {
        this.transparencia = f;
        if (s!=null) this.s.get_atr().set_transparent(f);
    }
    /**
     * Setter, método para aplicar la transparencia sobre la figura seleccionada
     * y las siguientes figuras pintadas
     * @param t true||false
     */
    public void set_transparencia(boolean t) {
        this.transp = t;
        if (s!=null) this.s.get_atr().set_transparency(t);
    }
    /**
     * Getter
     * @return Devuelve si se está aplicando transparencia en la figura seleccionada
     * o en las figuras a dibujar
     */
    public boolean get_transparencia() {
        return (s==null)?this.transp:s.get_atr().get_transparency();
    }
    /**
     * Setter, método para poder mover y editar los atributos de la figura
     * seleccionada o para desseleccionar
     * @param e true||false
     */
    public void set_editar(boolean e) {
        this._editar = e;
        if (s!=null) this.s.get_atr().set_edit(e);
        // this.s.get_atr().set_edit(e);
    }
    /**
     * Getter
     * @return deuvelve el modo de dibujo, es decir, el tipo de figura que se
     * está dibujando
     */
    public String get_modo_dibujo() {
        return this._modo_dibujo;
    }
    /**
     * Setter, para indicar qué tipo de figura vamos a dibujar ahora
     * @param s tipo de figura a dibujar
     */
    public void set_modo_dibujo(String s) {
        this._modo_dibujo = s;
    }
    /**
     * Getter
     * @return devuelve true||false, si se está o no editando la figura 
     * seleccionada
     */
    public boolean get_editar() {
        if (s!=null) {
            return this.s.get_atr().get_edit();
        }
        return this._editar;
    }
    /**
     * Setter, método para aplicar el color de traza sobre el lienzo y las figuras
     * @param c color del trazo
     */
    public void set_color_trace(Color c) {
        if (s!=null) {
            this.c = c;
            this.s.get_atr().set_color_trace(c);
        } else {
            this.c = c;
        }
    }
    /**
     * Setter, método para aplicar el color de relleno sobre el lienzo y las 
     * figuras
     * @param c color del relleno
     */
    public void set_color_fill(Color c) {
        if (s!=null) {
            this.c_fill = c;
            this.s.get_atr().set_color_fill(c);
        } else {
            this.c_fill = c;
        }
    }
    /**
     * Getter
     * @return devuelve el color de la traza de la figura, y si no existe el 
     * último que se usó en el lienzo
     */
    public Color get_color_trace() {
        return (s!=null)?this.s.get_atr().get_color_trace():c;
    }
    /**
     * Getter
     * @return devuelve el color del relleno de la figura, y si no existe el
     * último que se usó en el lienzo
     */
    public Color get_color_fill() {
        return (s!=null)?this.s.get_atr().get_color_fill():c_fill;
    }
    /**
     * Getter
     * @return devuelve la figura seleccionada
     */
    public Shape get_shape() {
        return (s==null)?null:s;
    }
    
    
    /**
     * Constructor por defecto, inicializa los componentes
     */
    public Lienzo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 153, 153));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento creado tanto para cerar figuras como para poder moverlas. Si estamos
     * moviendo figuras, entonces en este evento seleccionaremos la figura donde
     * hayamos hecho el pressed y sino crearemos una figura en función del modo
     * de dibujo que se encuentre activo en el lienzo con el punto donde se 
     * realizó el pressed
     * @param evt Lo usaremos para obtener el punto del pressed
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        if (this._editar) {
            s = getSelectedShape(evt.getPoint());
            this.pAux = evt.getPoint();
        } else {
            this.createShape(_modo_dibujo, evt.getPoint());
        }
    }//GEN-LAST:event_formMousePressed
    /**
     * Evento creato tanto para poder finalizar la creación de figuras como
     * para poder moverlas. Si estamos moviendo figuras la moveremos en función
     * de donde el usuario haya hecho el pressed, y si por el contrario estamos
     * creando las figuras se mandará la información restante para que se cree
     * correctamente según el tamaño que le esté indicando el usuario. Con este
     * evento, conseguimos mostrar al usuario cómo quedará la figura antes de que
     * este suelte el botón del ratón
     * @param evt lo usaremos para utilizar el punto del dragged
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        if (this._editar) {
            Point actual = new Point((int)evt.getPoint().getX() - (int)this.pAux.getX(),
                        (int)evt.getY() - (int)this.pAux.getY());
            try {
                s.setLocation(actual);
            } catch(NullPointerException e) {
                System.out.println("No se ha cogido ninguna figura.");
            }
            setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
            this.pAux = evt.getPoint();
        } else {
            this.updateShape(_modo_dibujo, evt.getPoint());
        }
        this.repaint();
    }//GEN-LAST:event_formMouseDragged
    /**
     * Evento generado para poder dibujar las figuras y terminar de moverlas
     * o de crearlas justo cuando suele el botón izquierdo del ratón, además se
     * le ha dado la utilidad de que ayude a poder dibujar la curva con un 
     * punto de control
     * @param evt lo usaremos para llamar al dragged
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        this.formMouseDragged(evt);
        if (this._modo_dibujo == "curva") {
            if (this.cnt_curva == 0) {
                this.cnt_curva++;
            } else {
                this.cnt_curva = 0;
            }
        }
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseReleased
    /**
     * Método que se usa para crear las figuras, inicializa la figura indicada
     * con el punto inicial en el que el usuario haya realizado el click. Al crear
     * la figura la añade al vector de figuras, de forma que posteriormente 
     * podremos realizar operaciones sobre la figura
     * @param mode tipo de figura a dibujar
     * @param p punto inicial para crear la figura
     */
    public void createShape(String mode, Point p) {
        // atr =  new Atributtes();
        atr =  new Atributtes(this.c, this.c_fill, this.fill, this.suavizado, 
                this.transp,this._editar, this.grosor, this.transparencia, this._modo_stroke);
        switch(this._modo_dibujo) {
                case "linea":
                    l = new Line(p, p, atr);
                    vShape.add(l);
                    s = l;
                    break;
                case "rectangulo":
                    r = new Rectangulo(p.x, p.y, p.x, p.y, atr);
                    vShape.add(r);
                    s = r;
                    // rectangle = new Rectangulo(p.x, p.y, p.x, p.x);
                    // vShape.add(rectangle);
                    break;
                case "ellipse":
                    e = new Ellipse(p.x, p.y, p.x, p.y, atr);
                    vShape.add(e);
                    s = e;
                    // ellipse = new Ellipse(p.x, p.y, p.x, p.x);
                    // vShape.add(ellipse);
                    break;
                case "trazo":
                    t = new TrazoLibre(atr);
                    this.t.get_trazo().moveTo(p.x, p.y);
                    vShape.add(t);
                    s = t;
                    break;
                case "curva":
                    if (this.cnt_curva == 0) {
                        curv = new CurvaSimple(p,atr);
                        vShape.add(curv);
                        s = curv;
                    } else if (this.cnt_curva == 1) {
                        this.updateShape(this._modo_dibujo, p);
                    }
                    break;
                case "area":
                    System.out.println("Llego");
                    area = new OwnShape(p, atr);
                    vShape.add(area);
                    s = area;
                    break;
                default:

                    break;
        }
    }
    /**
     * Método para terminar de dibujar la figura indicada, dándole como punto
     * el último punto, aquel en el que el usuario soltó el botón izquierdo del ratón,
     * de forma que este último punto terminará de definir la figura
     * @param mode figura a dibujar
     * @param p punto final de la figura
     */
    public void updateShape(String mode, Point p) {
        switch(this._modo_dibujo) {
                case "linea":
                    this.l.get_line().setLine(this.l.get_line().getP1(), p);
                    break;
                case "rectangulo":
                    this.r.get_rectangulo().setFrameFromDiagonal(this.r.get_p1(), p);
                    break;
                case "ellipse":
                    this.e.get_ellipse().setFrameFromDiagonal(this.e.get_p1(), p);
                    break;
                case "trazo":
                    this.t.get_trazo().lineTo(p.x, p.y);
                    break;
                case "curva":
                    if (this.cnt_curva == 0) {
                        this.curv.get_curva().setCurve(this.curv.get_curva().getX1(),
                                this.curv.get_curva().getY1(), p.x, p.y, p.x, p.y);
                    } else if (this.cnt_curva == 1){
                        /*if (this.curv.get_curva().contains(p)) {
                        this.curv.get_curva().setCurve(this.curv.get_curva().getX1(),
                        this.curv.get_curva().getY1(), p.x, p.y,
                        this.curv.get_curva().getX2(), this.curv.get_curva().getY2());
                        }*/
                        
                        this.curv.get_curva().setCurve(this.curv.get_curva().getX1(),
                                this.curv.get_curva().getY1(), this.curv.get_curva().getCtrlX(),
                                this.curv.get_curva().getCtrlY(), p.x, p.y);
                    }
                    break;
                case "area":
                    this.area.setFrameFromDiagonal(p);
                    /*System.out.println("Llego al update");
                    this.area.get_ellipse().setFrameFromDiagonal(
                    this.area.get_ellipse().getX(),
                    this.area.get_ellipse().getY(), (p.x)/2, p.y);
                    System.out.println("Llego al update");
                    this.area.get_rectangle().setFrameFromDiagonal((p.x)/2,
                    this.area.get_ellipse().getY(), p.x, p.y);*/
                    break;
                default:

                    break;
        }
    }
    /**
     * Setter, método para poder variar el clipArea
     * @param x valor x de la esquina inferior derecha
     * @param y valor y de la esquina inferior derecha
     */
    public void set_clip(int x, int y){
        this.clipArea = new Rectangle2D.Float(0, 0, x, y);
    }
    /**
     * Sobrecarga del método paint para poder dibujar todas las figuras que
     * se encuentren en el array de Shapes, vShape, además del clipArea
     * @param g Graphics g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setClip(clipArea);
        
        for(Shape s:vShape) {
            // s.draw(g2d);
            s.paint(g2d);
        }
    }
    /**
     * Getter, método para poder seleccionar las figuras que hemos creado
     * previamente
     * @param p punto sobre el cual el usuario hizo el pressed
     * @return true||false, śi hay o no figura
     */
    private Shape getSelectedShape(Point2D p){
        for (Shape s:vShape) {
            if(s.contains(p)) return s;
        }
        return null;
    }
    /**
     * Método para mandar una figura al fondo, es decir, para poner la figura
     * en el inicio del vector, de forma que se vea por debajo de las demás en
     * caso de superponerla con otras
     */
    public void set_fondo() {
        if (s!=null) {
            int i = vShape.indexOf(s);
            if (i!=0) {
                ArrayList<Shape> aux = new ArrayList();
                aux.add(s);
                for(Shape s1:vShape) {
                    if(s1!=s){
                        aux.add(s1);
                    }
                }
                vShape = aux;
            }
        }
    }
    /**
     * Método para mandar una figura al frente, es decir, para poner la figura
     * en el final del vector, de forma que se vea por encima de las demás en
     * caso de superponerla con otras
     */
    public void set_frente() {
        if (s!=null) {
            int i = vShape.indexOf(s);
            if (i!=vShape.size()-1) {
                ArrayList<Shape> aux = new ArrayList();
                for(Shape s1:vShape) {
                    if(s1!=s){
                        aux.add(s1);
                    }
                }
                aux.add(s);
                vShape = aux;
            }
        }
    }
    /**
     * Método para enviar una posición atrás la figura seleccionada, de forma
     * que la enviamos una posición alante en el vector
     */
    public void set_atras() {
        if (s!=null) {
            int i = vShape.indexOf(s);
            if (i!=0) {
                ArrayList<Shape> aux = new ArrayList();
                for (int j = 0; j<vShape.size(); ++j) {
                    if (i-1 == j) {
                        aux.add(s);
                        aux.add(vShape.get(j));
                        ++j;
                    } else {
                        aux.add(vShape.get(j));
                    }
                }
                vShape = aux;
            }
        }
    }
    /**
     * Método para enviar una posición alante la figura seleccionada, de forma
     * que la enviamos a una posición atrás en el vector
     */
    public void set_adelante() {
        if (s!=null) {
            int i = vShape.indexOf(s);
            if (i!=vShape.size()-1) {
                ArrayList<Shape> aux = new ArrayList();
                for (int j = 0; j<vShape.size(); ++j) {
                    if (i == j) {
                        aux.add(vShape.get(j+1));
                        aux.add(s);
                        ++j;
                    } else {
                        aux.add(vShape.get(j));
                    }
                }
                vShape = aux;
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
