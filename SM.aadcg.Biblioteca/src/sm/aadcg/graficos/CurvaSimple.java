/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Clase CurvaSimple que hereda de la clase Shape implementada, para así tener 
 * un bloque de atributos junto a la curva con un punto de control además de 
 * algunos métodos comunes con las otras figuras
 * @author alberto
 */
public class CurvaSimple extends sm.aadcg.graficos.Shape{
    /**
     * Variable de tipo QuadCurve para que tendrá las propiedades de la curva
     */
    private QuadCurve2D qv;
    
    /**
     * Constructor por defecto de la curva con los atributos por defecto y la
     * QuadCurve por defecto
     */
    public CurvaSimple() {
        super();
        qv = new QuadCurve2D.Double();
    }
    /**
     * Constructor con parámetros para inicializar la curva con los atributos
     * del lienzo y poder inicializar la curva en función del punto clickado
     * @param p punto donde el usuario ha clickado en el lienzo para crear la figura
     * a partir de este
     * @param atr bloque de atributos de la figura
     */
    public CurvaSimple(Point2D p, Atributtes atr) {
        super(atr);
        qv = new QuadCurve2D.Double(p.getX(), p.getY(), p.getX(),
                p.getY(), p.getX(), p.getY());
    }
    
    /**
     * Getter
     * @return devuelve un objeto de tivo QuadCurve2D para así poder acceder
     * a los métodos que nos permiten dibujar la figura
     */
    public QuadCurve2D get_curva() {
        return this.qv;
    }
    
    /**
     * Sobrecarga el método draw de la clase Shape para así poder pintar con los
     * los atributos relacionados a la curva
     * @param g2d Objeto de Graphics2D que se utilizará para dibujar los atributos
     * de la figura
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()){
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.qv.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.qv);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.qv);
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()){
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.qv.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.qv);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.qv);
    }
    
    /**
     * Sobrecarga del método contains de la clase Shape, para comprobar si el punto
     * p está contenido en la curva
     * @param p Punto a comprobar si está o no contenida la curva
     * @return true||false, dependiendo de si está o no está p en la figura
     */
    @Override
    public boolean contains(Point2D p) {
        return this.qv.contains(p);
    }
    
    /**
     * Sobrecarga el método setLocation de la clase Shape para poder mover la
     * figura de tipo QuadCurve en el lienzo
     * @param pos Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    @Override
    public void setLocation(Point2D pos){
        Point2D newp1 = new Point2D.Double(this.qv.getX1()+pos.getX(), this.qv.getY1()+pos.getY());
        Point2D newp2 = new Point2D.Double(this.qv.getX2()+pos.getX(), this.qv.getY2()+pos.getY());
        this.qv.setCurve(newp1, this.qv.getCtrlPt(), newp2);
    }
    
   
}
