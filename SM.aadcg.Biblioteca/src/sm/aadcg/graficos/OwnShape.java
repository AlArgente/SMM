/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase OwnShape que hereda de la clase Shape implementada, para así tener un 
 * bloque te atributos junto a la figura, además de los métodos comunes con las
 * otras figuras. Esta figura es de tipo Area, y está formada por un rectángulo
 * con una ellipse encima.
 * @author alberto
 */
public class OwnShape extends sm.aadcg.graficos.Shape{
    
    /**
     * Variables de tipo árena donde almacenaremos la figura
     */
    private Area area, area1, area2;
    /**
     * Variable de tipo Rectangle2D que formará la base de la figura
     */
    private Rectangle2D r;
    /**
     * Variable de tipo Ellipse2D que estará encima del rectángulo
     */
    private Ellipse2D e;
    /**
     * Variable de tipo Ellipse2D diseñada para estar en el interor de la
     * anterior ellipse (NO USADA POR EL MOMENTO)
     */
    private Ellipse2D e1;
    
    /**
     * Constructor por defecto, inicializa por defecto los atributos y las 3 
     * figuras utilizadas
     */
    public OwnShape() {
        super();
        r = new Rectangle2D.Float();
        e = new Ellipse2D.Float();
        e1 = new Ellipse2D.Float();
    }
    /**
     * Constructor por parámetros, inicializa las figuras con los mismos parámetros,
     * y respecto al mismo punto inicial p
     * @param p punto inicial de la figura
     * @param atr bloque de atributos dela figura
     */
    public OwnShape(Point p, Atributtes atr) {
        super(atr);
        r = new Rectangle2D.Float(p.x, p.y, p.x, p.y);
        e = new Ellipse2D.Float(p.x, p.y, p.x, p.y);
        e1 = new Ellipse2D.Float(p.x, p.y, p.x, p.y);
        area = new Area(r);
        // area = new Area(e);
        area1 = new Area(e);
        area2 = new Area(e1);
        
    }
    /**
     * Getter
     * @return Devuelve un objeto de tipo Area, para así poder acceder a los 
     * métodos que nos ofrece esta figura
     */
    public Area get_area() {
        return this.area;
    }
    
    /**
     * Getter
     * @return Devuelve un objeto de tipo Rectangle2D para así poder acceder
     * a los métodos que nos ofrece esta figura
     */
    public Rectangle2D get_rectangle() {
        return this.r;
    }
    /**
     * Getter
     * @return Devuelve un objeto de tipo Ellipse2D para así poder acceder
     * a los métodos que nos ofrece esta figura
     */
    public Ellipse2D get_ellipse() {
        return this.e;
    }
    /**
     * Sobrecarga del método contains de la clase Shape, para comprobar si el punto
     * p está contenido en el area
     * @param p Punto a comprobar si está o no contenida en el area
     * @return true||false, dependiendo de si está o no está p en la figura
     */
    @Override
    public boolean contains(Point2D p) {
        return this.area.contains(p);
    }
    /**
     * Sobrecarga el método setLocation de la clase Shape para poder mover la
     * figura de tipo area en el lienzo, de forma que situamos primero la ellipse
     * por encima del rectángulo
     * @param pos Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    @Override
    public void setLocation(Point2D pos){
        double dx = (pos.getX() + this.e.getX());
        double dy = (pos.getY() + this.e.getY());
        Point2D newp2 = new Point2D.Double(dx, dy);
        this.e.setFrame(newp2.getX(), newp2.getY(), this.e.getWidth(), this.e.getHeight());;
        double dx1 = (pos.getX() + this.r.getX());
        double dy1 = (pos.getY() + this.r.getY());
        Point2D newp1 = new Point2D.Double(dx1, dy1);
        this.r.setFrame(newp1.getX(), newp1.getY(), this.r.getWidth(), this.r.getHeight());
        area = new Area(r);
        area1 = new Area(e);
        area.add(area1);
        // this.area.setFrame(newp2.getX(), newp2.getY(), this.r.getWidth(), this.r.getHeight());
    }
    /**
     * Sobrecarga el método draw de la clase Shape para así poder pintar con los
     * los atributos relacionados al aŕea
     * @param g2d Objeto de Graphics2D que se utilizará para dibujar los atributos
     * de la figura
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_edit()) {
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.area.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.area);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.area);
    }
    
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_edit()) {
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.area.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.area);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.area);
    }
    
    /**
     * Método que se utiliza para poder actualizar a la vez la ellipse y el 
     * rectángulo y así poder dibujar correctamente la figura en función a un 
     * punto
     * @param p punto para definir el punto final de la figura
     */
    public void setFrameFromDiagonal(Point p) {
        this.e.setFrameFromDiagonal(this.e.getX(), this.e.getY(), (p.x), (p.y)/2 + 2);
        this.r.setFrameFromDiagonal(this.e.getX(), (p.y)/2 -2, p.x, (p.y));
        /*this.e1.setFrameFromDiagonal(this.e.getX() + (this.e.getX())/4,
        this.e.getY() + (this.e.getY())/4, (p.x)-(p.x)/4,
        ((p.y)/2+2)/4);*/
        area = new Area(r);
        area1 = new Area(e);
        area.add(area1);
        /*area.add(area2);*/
    }
    
}
