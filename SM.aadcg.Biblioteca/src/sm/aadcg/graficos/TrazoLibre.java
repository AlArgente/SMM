/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.GeneralPath;

/**
 * Clase TrazoLibre que hereda de la clase Shape implementada, para así tener
 * un bloque de atributos junto al GeneralPath además de algunos métodos comunes
 * con las otras figuras
 * @author alberto
 */
public class TrazoLibre extends sm.aadcg.graficos.Shape{
    /**
     * Variable de tipo GeneralPath que se usará para guardar los puntos de la 
     * misma y aprovechar los métodos que trae implementados
     */
    private GeneralPath gp;
    /**
     * Variable auxiliar para poder iniciar el GeneralPath con un mínimo número
     * de puntos
     */
    private int gp_tam = 10;
    
    /**
     * Constructor por defecto del trazo libre que inicializar los atributos por
     * defecto y el GeneralPath por defecto
     */
    public TrazoLibre() {
        super();
        gp = new GeneralPath();
    }
    
    /**
     * Constructor con atributos, inicializa la figura con un bloque de atributos
     * e inicializa el GeneralPath de tipo WIND_EVEN_ODD y con un tamaño inicial
     * de 10
     * @param atr bloque de atributos para inicializar la figura
     */
    public TrazoLibre(Atributtes atr) {
        super(atr);
        gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD, this.gp_tam);
    }
    
    /**
     * Getter, método que permite acceder al GeneralPath para poder actualizar
     * la figura conforme la vayamos creando
     * @return devuelve el objeto de tipo GeneralPath
     */
    public GeneralPath get_trazo() {
        return this.gp;
    }
    
    /**
     * Sobrecarga del método contains de la clase Shape, para comprobar si el punto
     * p está contenido en el GeneralPath
     * @param p Punto a comprobar si está o no contenido en el GeneralPath
     * @return true||false, dependiendo de si está o no está p en la figura
     */
    @Override
    public boolean contains(Point2D p) {
        return this.gp.contains(p);
    }
    
    /**
     * Sobrecarga el método draw de la clase Shape para así poder pintar con los
     * los atributos relacionados al GeneralPath
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
            g2d.draw(this.gp.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.gp);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.gp);
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.get_atr().get_color_trace());
        if (this.get_atr().get_transparency()) {
           g2d.setComposite(this.get_atr().get_comp());
        }
        if (this.get_atr().get_edit()) {
            g2d.setStroke(this.get_round_stroke());
            g2d.draw(this.gp.getBounds2D());
        }
        g2d.setStroke(this.get_atr().get_stroke());
        if (this.get_atr().get_fill()) {
            g2d.setPaint(this.get_atr().get_color_fill());
            g2d.fill(this.gp);
        }
        if (this.get_atr().get_smooth()) g2d.setRenderingHints(this.get_atr().get_render());
        g2d.draw(this.gp);
    }
    
    /**
     * Sobrecarga el método setLocation de la clase Shape para poder mover la
     * figura de tipo GeneralPath en el lienzo
     * @param p Punto que se utilizará para actualizar dónde dibujamos la figura
     */
    @Override
    public void setLocation(Point2D p) {
        
    }
    
}
