/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.RenderingHints;
import java.awt.Stroke;

/**
 * Clase contenedora de atributos que representarán la figura que será dibujada.
 * @author alberto
 */
public class Atributtes {
    /**
     * Variable de tipo Color que se usará para dibujar el color de traza de la
     * figura pintada
     */
    private Color _c_trace = Color.BLACK;
    /**
     * Variable de tipo Color que se usará para dibujar el relleno de la figura
     * pintada
     */
    private Color _c_fill = Color.BLACK;
    /**
     * Variable de tipo String para determinar el tipo de trazo que se va a dibujar,
     * por defecto contínuo, puede ser o contínuo o discontínuo
     */
    private String _t_stroke = "continuo";
    /**
     * Variable booleana que si está a true dibujará el relleno de la figura
     * con el color que tenga _c_fill, por defecto está a false
     */
    private boolean _fill = false;
    /**
     * Variable booleana que si está a true aplicará un suavizado sobre la figura
     * para que así se visualice mejor, por defecto está a false
     */
    private boolean _smooth = false;
    /**
     * Variable de tipo float que se usará para aplicar más o menos transparencia
     * a la figura dibujada, por defecto vale 0.5
     */
    private float _transparent = 0.5f;
    /**
     * Variable booleana que si está a true aplicará la transparencia sobre la
     * figura, por defecto a false
     */
    private boolean _transparency = false;
    /**
     * Variable booleana que si está a true permitirá mover la figura y además
     * dibujar un rectángulo alrededor de está indicando que ha sido seleccionada,
     * por defecto a false
     */
    private boolean _edit = false;
    /**
     * Variable entera que indicará el grosor con el que se dibujará la figura,
     * por defecto es 1
     */
    private int _thickness = 1;
    /**
     * Variable de tipo Stroke que dibujará el trazo de la figura, por defecto
     * contínuo, en función al grosor indicado
     */
    private Stroke _stroke = new BasicStroke(_thickness);
    /**
     * Variable de tipo Stroke, variable auxiliar para dibujar un trazo continuo
     */
    private Stroke _stroke_continuo = new BasicStroke(_thickness);
    /**
     * Array de float con el cuál se define un patrón de discontinuidad con el 
     * que se dibujará la figura
     */
    private float patronDiscontinuidad[] = {5.0f, 5.0f};
    /**
     * Variable de tipo Stroke, variable auxiliar para dibujar un trazo discontinuo
     */
    private Stroke _stroke_discontinuo = new BasicStroke(_thickness, 
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER, 1.0f,
                                patronDiscontinuidad, 0.0f);
    /**
     * Variable de tipo Composite que aplicará una transparencia u otra a la imagen
     */
    private Composite _comp = null;//AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    /**
     * Variable de tipo RenderingHints que permitirá aplicar suavizado
     */
    private RenderingHints _render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
    
    
    /**
     * Getter
     * @return El modo de Stroke que se está aplicando, puede ser continuo o discontinuo
     */
    public String get_modo_stroke() {
        return this._t_stroke;
    }
    /**
     * Setter, recibe un string para determinar el tipo de trazo que tendrá la
     * figura y después se asigna el tipo de trazo en función del valor de s,
     * que puede ser continuo o discontinuo.
     * @param s Modo de stroke, contínuo o discontínuo
     */
    public void set_modo_stroke(String s) {
        this._t_stroke = s;
        Stroke aux = null;
        if (_t_stroke == "continuo"){
            aux = new BasicStroke(_thickness);
        }
        if (_t_stroke == "discontinuo") {
            aux = new BasicStroke(_thickness, 
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER, 1.0f,
                                patronDiscontinuidad, 0.0f);
        }
        this._stroke = aux;
    }
    /**
     * Setter, para aplicar un color de trazo con el que dibujar la figura
     * @param c Color que se usará para dibujar el trazo 
     */
    public void set_color_trace(Color c) {
        this._c_trace = c;
    }
    /**
     * Getter
     * @return devuelve el color con el que se está dibujando el trazo de la figura
     */
    public Color get_color_trace() {
        return this._c_trace;
    }
    /**
     * Setter, asigna un nuevo color al relleno de la figura
     * @param c nuevo color con el que se dibujará el relleno
     */
    public void set_color_fill(Color c) {
        this._c_fill = c;
    }
    /**
     * Getter
     * @return devuelve el color de relleno que se está usando en la figura
     */
    public Color get_color_fill() {
        return this._c_fill;
    }
    /**
     * Setter, se usa para activar/desactivar el dibujado del relleno de la figura
     * @param f true||false, según se quiera aplicar el color de relleno
     */
    public void set_fill(boolean f) {
        this._fill = f;
    }
    /**
     * Getter
     * @return devuelve si se está o no dibujando el relleno de la figura
     */
    public boolean get_fill() {
        return this._fill;
    }
    /**
     * Setter, método para aplicar la transparencia en función de un valor [0,1]
     * @param f Variable de tipo float para aplicar más o menos transparencia
     */
    public void set_transparent(float f) {
        this._transparent = f;
        _comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this._transparent);
    }
    /**
     * Getter
     * @return Devuelve el grado de transparencia que se está usando en la figura,
     * por defecto 0.5
     */
    public float get_transparent() {
        return this._transparent;
    }
    /**
     * Setter, activa o desactiva la transparencia sobre la figura dibujada
     * @param b true||false 
     */
    public void set_transparency(boolean b) {
        this._transparency = b;
    }
    /**
     * Getter
     * @return Devuelve si se está o no aplicando la transparencia
     */
    public boolean get_transparency() {
        return this._transparency;
    }
    /**
     * Setter, permite aplicar o no el suavizado
     * @param s true||false para aplicar o no el suavizado en la figura
     */
    public void set_smooth(boolean s) {
        this._smooth = s;
    }
    /**
     * Getter
     * @return devuelve si se está aplicando o no el suavizado en la figura
     */
    public boolean get_smooth() {
        return this._smooth;
    }
    /**
     * Setter, tener seleccionada o no la figura para así poder mover la figura,
     * y además para poder dibujar un rectángulo alrededor de la misma en caso 
     * de que sea true
     * @param e true||false para poder mover o no la figura
     */
    public void set_edit(boolean e) {
        this._edit = e;
    }
    /**
     * Getter
     * @return devuelve si se puede mover o no la figura
     */
    public boolean get_edit() {
        return this._edit;
    }
    /**
     * Setter, permite cambiar el grosor del Stroke de la figura
     * @param th Grosor que se aplicará sobre la figura
     */
    public void set_thickness(int th){
        this._thickness = th;
        this.set_modo_stroke(_t_stroke);
        // this._stroke = new BasicStroke(th);
    }
    /**
     * Getter
     * @return devuelve el grosor que se está usando en la figura
     */
    public int get_thickness() {
        return this._thickness;
    }
    /**
     * Setter, permite asignar un Stroke directamente sobre la figura
     * @param st Stroke que se asignará a la figura
     */
    public void set_stroke(Stroke st) {
        this._stroke = st;
    }
    /**
     * Setter, permite asignar un Stroke a la figura en función de un grosor dado
     * @param th Grosor que se desea tener en el trazo
     */
    public void set_stroke(int th) {
        this._thickness = th;
        this.set_modo_stroke(_t_stroke);
    }
    /**
     * Getter
     * @return Devuelve el Stroke actual de la figura
     */
    public Stroke get_stroke() {
        return this._stroke;
    }
    /**
     * Setter, permite asignar un nuevo tipo de transparencia
     * @param comp Variable de tipo Composite que será asignada a la figura
     */
    public void set_comp(Composite comp) {
        this._comp  = comp;
    }
    /**
     * Setter, permite asignar un nuevo tipod e transparencia en función de un parámetro,
     * que hará que tenga más o menos transparencia
     * @param t el grado de transparencia que se aplicará [0,1]
     */
    public void set_comp(float t) {
        this._transparent = t;
        _comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this._transparent);
    }
    /**
     * Getter
     * @return devuelve el tipo de Composite asignado a la figura
     */
    public Composite get_comp() {
        return this._comp;
    }
    /**
     * Setter, aplica un nuevo tipo de suavizado a la figura
     * @param re Variable de RenderingHints que será aplicada en la figura
     */
    public void set_render(RenderingHints re) {
        this._render = re;
    } 
    /**
     * Getter
     * @return Devuelve el tipo de suavizado que se está aplicando
     */
    public RenderingHints get_render() {
        return this._render;
    }
    /**
     * Constructor por defecto de la clase contenedora
     */
    public Atributtes() {
        this._c_trace = Color.BLACK;
        this._c_fill = Color.BLACK;
        this._t_stroke = "continuo";
        this._fill = false;
        this._smooth = false;
        this._transparent = 0.5f;
        this._transparency = false;
        this._edit = false;
        this._thickness = 1;
        this._stroke = new BasicStroke(_thickness);
        this._comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        this._render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

    }
    /**
     * Método toString de la clase contenedora
     * @return Un String de los atributos de la figura
     */
    @Override
    public String toString() {
        return this._c_trace.toString() + this._c_fill.toString() + 
                this._fill + this._smooth + this._transparency + 
                this._transparent + this._comp + this._edit + 
                this._thickness + this._stroke.toString() + this._render.toString();
    }
    
    /**
     * Constructor con parámetros para el caso de que no sea una línea
     * @param c1 color de trazo
     * @param c2 color de relleno
     * @param f si se aplica o no el color de relleno
     * @param s si se aplica o no el suavizado
     * @param t si se aplica o no la transparencia
     * @param e si se puede o no mover
     * @param th grosor del trazo
     * @param t2 grado de transparencia
     * @param ts tipo de trazo 
     */
    public Atributtes(Color c1, Color c2, boolean f, boolean s, boolean t, boolean e,
            int th, float t2, String ts) {
        this._c_trace = c1;
        this._c_fill = c2;
        this._fill = f;
        this._smooth = s;
        this._transparency = t;
        this._transparent = t2;
        this._comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this._transparent);
        this._edit = e;
        this._thickness = th;
        this._t_stroke = ts;
        if (ts == "continuo") {
            this._stroke = new BasicStroke(_thickness);
        } else if (ts == "discontinuo") {
            this._stroke = new BasicStroke(_thickness, 
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER, 1.0f,
                                patronDiscontinuidad, 0.0f            
            );
        }
        this._render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
    }
    // Para el caso de que sea una línea || punto
    /**
     * Constructor por atributos para el caso de que sea una línea
     * @param c1 color de trazo
     * @param s si se aplica o no el suavizado
     * @param t si se aplica o no la transparencia
     * @param e si se puede o no mover la figura
     * @param th grosor del trazo 
     * @param t2 grado de transparencia que se aplicará sobre la figura
     */
    public Atributtes(Color c1, boolean s, boolean t, boolean e,
            int th, float t2) {
        this._c_trace = c1;
        this._c_fill = null;
        this._fill = false;
        this._smooth = s;
        this._transparency = t;
        this._transparent = t2;
        this._comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this._transparent);
        this._edit = e;
        this._thickness = th;
        this._t_stroke = "continuo";
        this._stroke = new BasicStroke(this._thickness);
        this._render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    /**
     * Constructor con atributos, en este caso recibe un bloque de atributos entero
     * @param atr Bloque de atributos con el que se inicializará la figura
     */
    public Atributtes (Atributtes atr) {
        this._c_trace = atr.get_color_trace();
        this._c_fill = atr.get_color_fill();
        this._fill = atr.get_fill();
        this._smooth = atr.get_smooth();
        this._transparency = atr.get_transparency();
        this._transparent = atr.get_transparent();
        this._comp = atr.get_comp();
        this._edit = atr.get_edit();
        this._thickness = atr.get_thickness();
        this._stroke = atr.get_stroke();
        this._render = atr.get_render();
        this._t_stroke = atr.get_modo_stroke();
    }
}
