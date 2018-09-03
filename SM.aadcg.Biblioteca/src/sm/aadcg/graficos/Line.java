/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase Line que hereda de la clase Shape implementada, para así tener un
 * bloque de atributos junto a la Linea, además de algunos métodos comunes
 * con las otras figuras
 * @author alberto
 */
public class Line extends sm.aadcg.graficos.Shape {
    /**
     * Variable de tipo Line2D que usaremos para crear la línea, y así aprovechar
     * los métodos ya implementados
     */
    private Line2D l;
    
    /**
     * Constructor por defecto con los atributos por defecto y un rectángulo por
     * defecto
     */
    public Line(){
        super();
        l = new Line2D.Float();
    }
    /**
     * Constructor con parámetros, para inicializar la línea a un punto inicial
     * y a un bloque de atributos
     * @param p punto inicial de la línea
     * @param p1 punto final de la línea, se utiliza para poder crear la figura
     * @param atr bloque de atributos de la figura
     */
    public Line (Point p, Point p1, Atributtes atr) {
        super(atr);
        l = new Line2D.Float(p,p1);
    }
    /**
     * Getter
     * @return Devuelve un objeto de tipo Line2D para así poder acceder a los
     * métodos que nos ofrece esta figura
     */
    public Line2D get_line() {
        return this.l;
    }
    
    /**
     * Sobrecarga el método setLocation de la clase Shape para poder mover la
     * figura de tipo línea en el lienzo
     * @param pos Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    @Override
    public void setLocation(Point2D pos) {
        Point2D newp1 = new Point2D.Double(this.l.getX1()+pos.getX(), this.l.getY1()+pos.getY());
        Point2D newp2 = new Point2D.Double(this.l.getX2()+pos.getX(), this.l.getY2()+pos.getY());
        this.l.setLine(newp1,newp2);
    }
    /**
     * Sobrecarga del método isNear de la clase Shape, para comprobar si el punto
     * p seleccionado está cerca de la figura y así poder moverla, para ello
     * calcula la distancia del punto respecto a la línea, si la distancia es 
     * menor o igual a 2 entonces se detecta la línea, además como un punto
     * es un caso particular la la línea con ambos puntos iguales, se añade una
     * pequeña mejora para que si P1 y P2 (puntos de la línea) son iguales, 
     * entonces la distancia de p respecto a P1 debe ser de 4
     * @param p punto que se usará para comprobar si se puede o no coger la línea
     * @return true||false, dependiendo de si está cerca ono de la línea
     */
    @Override
    public boolean isNear(Point2D p) {
        // mejorarlo para poder mover puntos
        // if this.getP1 = this.getP2 ==> medir distancia entre punto y punto y no entre punto y linea
        if (this.l.getP1().equals(this.l.getP2())) {
            return this.l.getP1().distance(p) <= 4.0;
        } else {
            return this.l.ptLineDist(p)<=2.0;
        }
    }
    /**
     * Sobrecarga del método contains de la clase Shape, para comprobar si el punto
     * p está contenido en la línea
     * @param p Punto a comprobar si está o no contenida la línea
     * @return true||false, dependiendo de si está o no está p en la figura
     */
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }
    /**
     * Sobrecarga el método draw de la clase Shape para así poder pintar con los
     * los atributos relacionados a la línea
     * @param g2d Objeto de Graphics2D que se utilizará para dibujar los atributos
     * de la figura
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()){
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.l.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setColor(this.get_atr().get_color_fill());
            g2d.fill(l);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.l);
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()){
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.l.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setColor(this.get_atr().get_color_fill());
            g2d.fill(l);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.l);
    }
}
