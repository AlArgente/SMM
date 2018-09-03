/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase Rectangulo que hereda de la clase Shape implementada, para así tener un
 * bloque de atributos junto al Rectángulo, además de algunos métodos comunes
 * con las otras figuras
 * @author alberto
 */
public class Rectangulo extends sm.aadcg.graficos.Shape{
    /**
     * Variable para almacenar el primer punto, donde se inicia, la figura
     */
    private Point2D.Float _p1;
    /**
     * Variable para almacenar el el segundo punto, para poder calcular la figura
     */
    private Point2D.Float _p2;
    /**
     * Variable de tipo Rectangle2D que usaremos para crear el Rectángulo, y así 
     * aprovechar los métodos ya implementados.
     */
    private Rectangle2D r;
    
    
    /**
     * Constructor por defecto con los atributos por defecto y un rectángulo por
     * defecto
     */
    public Rectangulo () {
        super();
        r = new Rectangle2D.Float();
    }
    /**
     * Constructor con parámetros, para inicializar el Rectángulo a un punto inicial
     * y a un bloque de atributos
     * @param x1 x del punto inicial
     * @param y1 y del punto inicial
     * @param x2 x del punto final, se inicializa igual que el inicial para poder 
     * generar la figura
     * @param y2 y del punto final, se inicializa igual que el inicial para poder
     * generar la figura
     * @param atr bloque de atributos de la figura
     */
    public Rectangulo (float x1, float y1, float x2, float y2, Atributtes atr) {
        super(atr);
        r = new Rectangle2D.Float(x1, y1, x2, y2);
        _p1 = new Point2D.Float(x1, y1);
        _p2 = new Point2D.Float(x2, y2);
    }
    /**
     * Getter
     * @return Devuelve un objeto de tipo Rectangle2D para así poder acceder
     * a los métodos que nos ofrece esta figura
     */
    public Rectangle2D get_rectangulo() {
        return this.r;
    }
    /**
     * Setter
     * @param p Nuevo valor para el punto inicial de la figura
     */
    public void set_p1 (Point2D.Float p) {
        this._p1 = p;
    }
    /**
     * Setter
     * @param p Nuevo valor para el punto inicial de la figura
     */
    public void set_p2 (Point2D.Float p) {
        this._p2 = p;
    }
    /**
     * Getter
     * @return Devuelve el punto inicial de la figura
     */
    public Point2D get_p1 () {
        return this._p1;
    }
    /**
     * Getter
     * @return Devuelve el punto final de la figura
     */
    public Point2D get_p2 () {
        return this._p2;
    }
    /**
     * Sobrecarga del método contains de la clase Shape, para comprobar si el punto
     * p está contenido en el rectángulo
     * @param p Punto a comprobar si está o no contenida en el rectángulo
     * @return true||false, dependiendo de si está o no está p en la figura
     */
    @Override
    public boolean contains(Point2D p) {
        return this.r.contains(p);
    }
    /**
     * Sobrecarga el método setLocation de la clase Shape para poder mover la
     * figura de tipo rectángulo en el lienzo
     * @param pos Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    @Override
    public void setLocation(Point2D pos){
        double dx = (pos.getX() + this.r.getX());
        double dy = (pos.getY() + this.r.getY());
        Point2D newp2 = new Point2D.Double(dx, dy);
        this.r.setFrame(newp2.getX(), newp2.getY(), this.r.getWidth(), this.r.getHeight());
    }
    /**
     * Sobrecarga el método draw de la clase Shape para así poder pintar con los
     * los atributos relacionados al rectángulo
     * @param g2d Objeto de Graphics2D que se utilizará para dibujar los atributos
     * de la figura
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()) {
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.r.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.r);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.r);
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()) {
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.r.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.r);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.r);
    }
}
