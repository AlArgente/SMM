/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 * Clase padre del resto de las figuras implementadas, la cual contiene un bloque
 * de atributos de la figura que será generada, y un atributo de tipo Stroke para
 * así dibujar el rectángulo que redondee la figura
 * @author alberto
 */
public class Shape {
    /**
     * Atributos de la figura, se usará la clase contenedora Atributtes para 
     * así almacenar todos los atributos de la figura
     */
    private Atributtes _atr;
    /**
     * Variable de tipo Shape, para ser usada en caso de ser necesario en las
     * siguientes clases (NO USADA)
     */
    private java.awt.Shape s;
    /**
     * Array de floats que define el patrón de discontinuidad que rodeará las
     * figuras a la hora de ser editadas
     */
    private float patronDiscontinuidad[] = {5.0f, 5.0f};
    /**
     * Variable de tipo Stroke para poder dibujar el patrón de discontinuidad que
     * será dibujado cuando la figura esté seleccionada
     */
    private Stroke st = new BasicStroke(1.0f, 
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER, 1.0f,
                                patronDiscontinuidad, 0.0f);
    
    /**
     * Constructor por defecto de la clase Shape
     * Crea un objeto Shape con los atributos por defecto.
     */
    public Shape() {
        this._atr = new Atributtes();
    }
    /**
     * Crea un constructor con un conjunto de atributos definidos
     * @param atr Atributos que tendrá la figura de tipo Atributtes
     */
    public Shape(Atributtes atr) {
        this._atr = atr;
    }
    /**
     * Constructor con dos atributos
     * @param _s Atributo para asignar un Shape s
     * @param atr Atributos que tendrá la figura de tipo Atributtes
     */
    public Shape(java.awt.Shape _s, Atributtes atr){
        this.s = _s;
        this._atr = atr;
    }
    
    /**
     * Getter, se acceden a los atributos del objeto Shape
     * @return Bloque de atributos de la figura
     */
    public Atributtes get_atr() {
        return this._atr;
    }
    
    /**
     * Setter, permite asignar un bloque de atributos a la figura
     * @param atr Nuevo bloque de atributos que se asignarán a la figura
     */
    public void set_atr(Atributtes atr) {
        this._atr = atr;
    }
    
    /**
     * Getter, se coge el stroke que rodeará a las figuras al seleccionarlas
     * @return variable de tipo Stroke para bordear la figura al editarla
     */
    public Stroke get_round_stroke() {
        return this.st;
    }
    
    /**
     * Método draw que se implementará en las subclases para que cada una
     * dibuja lo necesario
     * @param g2d Objeto de Graphics2D que se utilizará para dibujar
     */
    public void draw(Graphics2D g2d) {
        
    }
            
    /**
     * Setter, se utilizará para poder mover la posición en el Lienzo de la 
     * figura seleccionada
     * @param pos Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    public void setLocation(Point2D pos) {
        
    }
    
    /**
     * Método para comprobar si el punto p está en la sigura seleccionada, que
     * será implementado en cada figura
     * @param p Punto a comprobar
     * @return devuelve false
     */
    public boolean contains(Point2D p) {
        return false;
    }
    
    /**
     * Método para comprobar si el punto p está cerca de la figura. Se usará con
     * la línea para poder moverla
     * @param p punto para comprobar si está cerca de la figura
     * @return devuelve false
     */
    public boolean isNear(Point2D p) {
        return false;
    }
    
    public void paint(Graphics2D g2d){
        
    }
}
