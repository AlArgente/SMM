/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import sm.aadcg.image.SepiaOp;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.DataBuffer;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.aadcg.graficos.ColorCellRender;
import sm.aadcg.graficos.LookupTableExtension;
import sm.aadcg.image.CompOp;
import sm.aadcg.image.RedOp;
import sm.aadcg.image.UmbralizationOp;
import sm.image.EqualizationOp;
import sm.image.KernelProducer;
import sm.image.LookupTableProducer;
import sm.image.TintOp;
import sm.sound.SMClipPlayer;
import sm.sound.SMPlayer;
import sm.sound.SMRecorder;
import sm.sound.SMSoundPlayer;
import sm.sound.SMSoundRecorder;

/**
 *
 * @author alberto
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    /**
     * Creates new form VentanaPrincipal
     */

    private BufferedImage imgOrigen;
    private BufferedImage img;
    private Graphics2D g2dvp;
    private int brillo = 0;
    private Point vi_p = new Point(0,0);
    private int size_vi_a = 300;
    private int size_vi_b = 300;
    private int grados = 0;
    private float patronDiscontinuidad[] = {15.0f, 15.0f};
    private Shape clipArea;
    private SMSoundPlayer player = null;
    private SMSoundRecorder recorder = null;
    private boolean audio = false;
    private boolean record = false;
    private ArrayList<String> imagenes_f = new ArrayList();
    private ArrayList<String> audio_f = new ArrayList();
    private ArrayList<String> video_f = new ArrayList();
    File grabatmp = new File("tmp"+".au");
    class ManejadorAudio implements LineListener {
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.START){
                if (player!=null)
                    playSound.setEnabled(false);
                if (recorder!=null) 
                    recordAudio.setEnabled(false);
                if (!stopAudio.isEnabled()) {
                    stopAudio.setEnabled(true);
                }
            }
            if (event.getType() == LineEvent.Type.STOP){
                if (!playSound.isEnabled())
                    playSound.setEnabled(true);
                
                if (!recordAudio.isEnabled())
                    recordAudio.setEnabled(true);
            }
            if (event.getType() == LineEvent.Type.CLOSE){
                
            }
            if (event.getType() == LineEvent.Type.OPEN){
                
            }
        }
    }
    
    private void add_imagen_f(String img_f){
        this.imagenes_f.add(img_f);
    }
    private void add_audio_f(String audio_f){
        this.audio_f.add(audio_f);
    }
    private void add_video_f(String video_f){
        this.video_f.add(video_f);
    }
    
    public VentanaPrincipal() {
        initComponents();
        // Inicializamos el Array de filtros de imágenes
        this.add_imagen_f("jpg");
        this.add_imagen_f("png");
        // Inicializamos el Array de filtros de Audio
        this.add_audio_f("au");
        this.add_audio_f("wav");
        // Inicializamos el Array de filtros de Video|Audio VLC
        this.add_video_f("avi");
        this.add_video_f("mov");
        this.add_video_f("asf");
        this.add_video_f("ogm");
        this.add_video_f("mkv");
        this.add_video_f("mpeg");
        this.add_video_f("mp4");
        this.add_video_f("ogg");
        this.add_video_f("3gp");
        this.add_video_f("mpg");
        this.add_video_f("wmx");
        this.add_video_f("divx");
        this.add_video_f("flv");
        this.add_video_f("mp3");
        this.add_video_f("wma");
        this.add_video_f("aac");
        this.add_video_f("aiff");
        
        this.setSize(1024, 720);
        this.jCheckBoxMenuItem1.setSelected(true);
        nuevo.setToolTipText("Nuevo");
        open.setToolTipText("Abrir");
        save.setToolTipText("Guardar");
        pathButton.setToolTipText("Punto");
        lineButton.setToolTipText("Línea");
        rectangleButton.setToolTipText("Rectángulo");
        ellipseButton.setToolTipText("Elipse");
        curvaButton.setToolTipText("Curva un punto de control");
        areaButton.setToolTipText("Figura personalizada");
        editButton.setToolTipText("Editar");
        jToggleButton12.setToolTipText("Rellenar");
        jToggleButton13.setToolTipText("Transparencia");
        jToggleButton14.setToolTipText("Alisar");
        jSpinner2.setToolTipText("Grosor");
        jComboBox1.setToolTipText("Color de trazo");
        jSpinner1.setToolTipText("Mejora Alisado");
        jComboBox3.setToolTipText("Color fondo");
        fondoButton.setToolTipText("Envia al fondo la figura seleccionada");
        frenteButton.setToolTipText("Trae al frente la figura seleccionada");
        atrasButton.setToolTipText("Envía atrás (una pos) la figura seleccionada");
        adelanteButton.setToolTipText("Trae adelante (una pos) la figura seleccionada");
        listaReproduccion.setToolTipText("Lista de reproducción de audio");
        playSound.setToolTipText("Reproduce el audio seleccionado en la lista de reproducción");
        stopAudio.setToolTipText("Detiene la reproducción del audio seleccionado");
        recordAudio.setToolTipText("Graba un audio");
        webCam.setToolTipText("Abre la webcam del ordenador (si la tiene)");
        webcamCaptura.setToolTipText("Hace una captura sobre lo que se vea en la WebCam");
        sliderBrillo.setToolTipText("Modifica el brillo sobre la imagen");
        jComboBox2.setToolTipText("Aplica el filtro selecionado a la imagen");
        contraste.setToolTipText("Aplica contraste sobre la imagen");
        iluminar.setToolTipText("Ilumina la imagen");
        oscurecer.setToolTipText("Oscurece la imagen");
        sinosoidal.setToolTipText("Aplica la función seno sobre la imagen");
        sepia.setToolTipText("Aplica el filtro sepia sobre la imagen");
        tintadoOp.setToolTipText("Aplica el color de trazo sobre imagen para tintarla");
        jButton1.setToolTipText("Aplica la ecualización sobre la imagen");
        bandas.setToolTipText("Obtiene las bandas de la imagen");
        espaciocolor.setToolTipText("Nueva ventana con la imagen en otro espacio de color");
        jSlider1.setToolTipText("Rota la imagen");
        jButton5.setToolTipText("Rota la imagen 90 grados");
        jButton6.setToolTipText("Rota la imagen 180 grados");
        jButton7.setToolTipText("Rota la imagen 270 grados");
        jButton8.setToolTipText("Zoom + sobre la imagen");
        jButton9.setToolTipText("Zoom - sobre la imagen");
        umbralizacion.setToolTipText("Aplica un umbral sobre los colores de la imagen");
        negativo.setToolTipText("Aplica la operación negativo sobre la imagen");
        duplicar.setToolTipText("Crea una copia de la imagen actual");
        redOp.setToolTipText("Aplica la operación RedOp para localizar zonas rojizas");
        cosenoidal.setToolTipText("Aplica la operación sqrt(1-cos^2(x))");
        compOp.setToolTipText("Aplica la operación CompOp que hace la imagen morada");
        newItem.setToolTipText("Nuevo");
        abrirItem.setToolTipText("Abrir");
        guardarItem.setToolTipText("Guardar");
        modoStroke.setToolTipText("Selecciona el tipo de trazo");
        mediaSnapshoot.setToolTipText("Realiza una captura sobre el reproductor VLC");
        this.setLocationRelativeTo(null);
    }

    public VentanaInternaImage getVentanaInterna() {
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            return vi;
        } 
        return null;
    }
    public void set_vi_size(int a, int b) {
        this.size_vi_a = a;
        this.size_vi_b = b;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        nuevo = new javax.swing.JButton();
        open = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        pathButton = new javax.swing.JToggleButton();
        lineButton = new javax.swing.JToggleButton();
        rectangleButton = new javax.swing.JToggleButton();
        ellipseButton = new javax.swing.JToggleButton();
        curvaButton = new javax.swing.JToggleButton();
        areaButton = new javax.swing.JToggleButton();
        editButton = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        modoStroke = new javax.swing.JComboBox<>();
        Color c[] = {Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        jComboBox1 = new javax.swing.JComboBox<>(c);
        Color c1[] = {Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        jComboBox3 = new javax.swing.JComboBox<>(c1);
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        fondoButton = new javax.swing.JButton();
        frenteButton = new javax.swing.JButton();
        atrasButton = new javax.swing.JButton();
        adelanteButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        playSound = new javax.swing.JButton();
        stopAudio = new javax.swing.JButton();
        listaReproduccion = new javax.swing.JComboBox<>();
        recordAudio = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        webCam = new javax.swing.JButton();
        webcamCaptura = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        mediaSnapshoot = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel3 = new javax.swing.JPanel();
        sliderBrillo = new javax.swing.JSlider();
        jPanel5 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        contraste = new javax.swing.JButton();
        iluminar = new javax.swing.JButton();
        oscurecer = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        sinosoidal = new javax.swing.JButton();
        sepia = new javax.swing.JButton();
        tintadoOp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        negativo = new javax.swing.JButton();
        duplicar = new javax.swing.JButton();
        redOp = new javax.swing.JButton();
        cosenoidal = new javax.swing.JButton();
        compOp = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        bandas = new javax.swing.JButton();
        espaciocolor = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        umbralizacion = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newItem = new javax.swing.JMenuItem();
        abrirItem = new javax.swing.JMenuItem();
        guardarItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        imageData = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo.png"))); // NOI18N
        nuevo.setFocusable(false);
        nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        nuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoKeyPressed(evt);
            }
        });
        jToolBar1.add(nuevo);

        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        open.setFocusable(false);
        open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        jToolBar1.add(open);

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        save.setFocusable(false);
        save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jToolBar1.add(save);
        jToolBar1.add(jSeparator2);

        pathButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/dibujar2.png"))); // NOI18N
        pathButton.setFocusable(false);
        pathButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pathButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(pathButton);

        lineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/linea.png"))); // NOI18N
        lineButton.setFocusable(false);
        lineButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lineButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(lineButton);

        rectangleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rectangulo.png"))); // NOI18N
        rectangleButton.setFocusable(false);
        rectangleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rectangleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectangleButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(rectangleButton);

        ellipseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/elipse.png"))); // NOI18N
        ellipseButton.setFocusable(false);
        ellipseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ellipseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ellipseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ellipseButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(ellipseButton);

        curvaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Cruve.png"))); // NOI18N
        curvaButton.setFocusable(false);
        curvaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        curvaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        curvaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curvaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(curvaButton);

        areaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OwnShape.png"))); // NOI18N
        areaButton.setFocusable(false);
        areaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        areaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        areaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(areaButton);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccion.png"))); // NOI18N
        editButton.setFocusable(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editButton);
        jToolBar1.add(jSeparator1);

        modoStroke.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "continuo", "discontinuo" }));
        modoStroke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoStrokeActionPerformed(evt);
            }
        });
        jToolBar1.add(modoStroke);

        jComboBox1.setRenderer(new ColorCellRender());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        jComboBox3.setRenderer(new ColorCellRender());
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox3);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner2.setMaximumSize(new java.awt.Dimension(38, 26));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });
        jToolBar1.add(jSpinner2);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.100000024f)));
        jSpinner1.setMaximumSize(new java.awt.Dimension(38, 26));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jToolBar1.add(jSpinner1);

        jToggleButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rellenar.png"))); // NOI18N
        jToggleButton12.setFocusable(false);
        jToggleButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton12);

        jToggleButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/transparencia.png"))); // NOI18N
        jToggleButton13.setFocusable(false);
        jToggleButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton13);

        jToggleButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/alisar.png"))); // NOI18N
        jToggleButton14.setFocusable(false);
        jToggleButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton14ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton14);

        fondoButton.setText("Fondo");
        fondoButton.setFocusable(false);
        fondoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fondoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fondoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondoButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(fondoButton);

        frenteButton.setText("Frente");
        frenteButton.setFocusable(false);
        frenteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        frenteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        frenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frenteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(frenteButton);

        atrasButton.setText("Atrás");
        atrasButton.setFocusable(false);
        atrasButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        atrasButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        atrasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(atrasButton);

        adelanteButton.setText("Adelante");
        adelanteButton.setFocusable(false);
        adelanteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adelanteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        adelanteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adelanteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(adelanteButton);
        jToolBar1.add(jSeparator3);

        playSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play24x24.png"))); // NOI18N
        playSound.setFocusable(false);
        playSound.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playSound.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        playSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playSoundActionPerformed(evt);
            }
        });
        jToolBar1.add(playSound);

        stopAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/stop24x24.png"))); // NOI18N
        stopAudio.setFocusable(false);
        stopAudio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopAudio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stopAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopAudioActionPerformed(evt);
            }
        });
        jToolBar1.add(stopAudio);

        listaReproduccion.setMaximumRowCount(20);
        jToolBar1.add(listaReproduccion);

        recordAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/record24x24.png"))); // NOI18N
        recordAudio.setFocusable(false);
        recordAudio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recordAudio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        recordAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordAudioActionPerformed(evt);
            }
        });
        jToolBar1.add(recordAudio);
        jToolBar1.add(jSeparator4);

        webCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Camara.png"))); // NOI18N
        webCam.setFocusable(false);
        webCam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        webCam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        webCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webCamActionPerformed(evt);
            }
        });
        jToolBar1.add(webCam);

        webcamCaptura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Capturar.png"))); // NOI18N
        webcamCaptura.setFocusable(false);
        webcamCaptura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        webcamCaptura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        webcamCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webcamCapturaActionPerformed(evt);
            }
        });
        jToolBar1.add(webcamCaptura);
        jToolBar1.add(jSeparator5);

        mediaSnapshoot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Capturar.png"))); // NOI18N
        mediaSnapshoot.setFocusable(false);
        mediaSnapshoot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mediaSnapshoot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mediaSnapshoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaSnapshootActionPerformed(evt);
            }
        });
        jToolBar1.add(mediaSnapshoot);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jToolBar2.setRollover(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brillo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        sliderBrillo.setMaximum(255);
        sliderBrillo.setMinimum(-255);
        sliderBrillo.setValue(0);
        sliderBrillo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sliderBrillo.setPreferredSize(new java.awt.Dimension(100, 27));
        sliderBrillo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderBrilloStateChanged(evt);
            }
        });
        sliderBrillo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sliderBrilloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sliderBrilloFocusLost(evt);
            }
        });
        jPanel3.add(sliderBrillo);

        jToolBar2.add(jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel5.setRequestFocusEnabled(false);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media", "Binomial", "Enfoque", "Relieve", "Fronteras" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(75, 25));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox2);

        jToolBar2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contraste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        contraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/contraste.png"))); // NOI18N
        contraste.setPreferredSize(new java.awt.Dimension(40, 32));
        contraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasteActionPerformed(evt);
            }
        });
        jPanel6.add(contraste);

        iluminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/iluminar.png"))); // NOI18N
        iluminar.setPreferredSize(new java.awt.Dimension(40, 32));
        iluminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iluminarActionPerformed(evt);
            }
        });
        jPanel6.add(iluminar);

        oscurecer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/oscurecer.png"))); // NOI18N
        oscurecer.setPreferredSize(new java.awt.Dimension(40, 32));
        oscurecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oscurecerActionPerformed(evt);
            }
        });
        jPanel6.add(oscurecer);

        jToolBar2.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        sinosoidal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sinusoidal.png"))); // NOI18N
        sinosoidal.setMaximumSize(new java.awt.Dimension(46, 32));
        sinosoidal.setMinimumSize(new java.awt.Dimension(46, 32));
        sinosoidal.setPreferredSize(new java.awt.Dimension(40, 32));
        sinosoidal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinosoidalActionPerformed(evt);
            }
        });
        jPanel7.add(sinosoidal);

        sepia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sepia.png"))); // NOI18N
        sepia.setMaximumSize(new java.awt.Dimension(46, 32));
        sepia.setMinimumSize(new java.awt.Dimension(46, 32));
        sepia.setPreferredSize(new java.awt.Dimension(40, 32));
        sepia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sepiaActionPerformed(evt);
            }
        });
        jPanel7.add(sepia);

        tintadoOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/tintar.png"))); // NOI18N
        tintadoOp.setMaximumSize(new java.awt.Dimension(46, 32));
        tintadoOp.setMinimumSize(new java.awt.Dimension(46, 32));
        tintadoOp.setPreferredSize(new java.awt.Dimension(40, 32));
        tintadoOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tintadoOpActionPerformed(evt);
            }
        });
        jPanel7.add(tintadoOp);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ecualizar.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(46, 32));
        jButton1.setMinimumSize(new java.awt.Dimension(46, 32));
        jButton1.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1);

        negativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/negativo.png"))); // NOI18N
        negativo.setPreferredSize(new java.awt.Dimension(40, 32));
        negativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negativoActionPerformed(evt);
            }
        });
        jPanel7.add(negativo);

        duplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/duplicar.png"))); // NOI18N
        duplicar.setMaximumSize(new java.awt.Dimension(46, 32));
        duplicar.setMinimumSize(new java.awt.Dimension(46, 32));
        duplicar.setPreferredSize(new java.awt.Dimension(40, 32));
        duplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicarActionPerformed(evt);
            }
        });
        jPanel7.add(duplicar);

        redOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rredcircle.png"))); // NOI18N
        redOp.setPreferredSize(new java.awt.Dimension(40, 32));
        redOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redOpActionPerformed(evt);
            }
        });
        jPanel7.add(redOp);

        cosenoidal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cosenoidal.png"))); // NOI18N
        cosenoidal.setPreferredSize(new java.awt.Dimension(40, 32));
        cosenoidal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cosenoidalActionPerformed(evt);
            }
        });
        jPanel7.add(cosenoidal);

        compOp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rmorada.png"))); // NOI18N
        compOp.setPreferredSize(new java.awt.Dimension(40, 32));
        compOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compOpActionPerformed(evt);
            }
        });
        jPanel7.add(compOp);

        jToolBar2.add(jPanel7);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Color", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        bandas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/bandas.png"))); // NOI18N
        bandas.setPreferredSize(new java.awt.Dimension(40, 32));
        bandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bandasActionPerformed(evt);
            }
        });
        jPanel10.add(bandas);

        espaciocolor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "sRGB", "YCC", "GREY" }));
        espaciocolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espaciocolorActionPerformed(evt);
            }
        });
        jPanel10.add(espaciocolor);

        jToolBar2.add(jPanel10);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rotación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        jSlider1.setMajorTickSpacing(360);
        jSlider1.setMaximum(360);
        jSlider1.setMinorTickSpacing(90);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(0);
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSlider1.setMaximumSize(new java.awt.Dimension(100, 27));
        jSlider1.setPreferredSize(new java.awt.Dimension(100, 27));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSlider1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSlider1FocusLost(evt);
            }
        });
        jPanel8.add(jSlider1);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion90.png"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion180.png"))); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rotacion270.png"))); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton7);

        jToolBar2.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escala", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aumentar.png"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disminuir.png"))); // NOI18N
        jButton9.setToolTipText("");
        jButton9.setPreferredSize(new java.awt.Dimension(40, 32));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9);

        jToolBar2.add(jPanel9);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Umbralización", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));

        umbralizacion.setMaximum(255);
        umbralizacion.setValue(127);
        umbralizacion.setPreferredSize(new java.awt.Dimension(100, 27));
        umbralizacion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                umbralizacionStateChanged(evt);
            }
        });
        umbralizacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                umbralizacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                umbralizacionFocusLost(evt);
            }
        });
        jPanel11.add(umbralizacion);

        jToolBar2.add(jPanel11);

        jPanel2.add(jToolBar2, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Barra de estado");
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);

        jLabel2.setText("CoordenadasMouse");
        jPanel4.add(jLabel2, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        escritorio.setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Archivo");

        newItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        newItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo.png"))); // NOI18N
        newItem.setText("Nuevo");
        newItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemActionPerformed(evt);
            }
        });
        jMenu1.add(newItem);
        newItem.getAccessibleContext().setAccessibleDescription("");

        abrirItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        abrirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        abrirItem.setText("Abrir");
        abrirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirItem);
        abrirItem.getAccessibleContext().setAccessibleName("abrirItem");

        guardarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        guardarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        guardarItem.setText("Guardar");
        guardarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarItemActionPerformed(evt);
            }
        });
        jMenu1.add(guardarItem);
        guardarItem.getAccessibleContext().setAccessibleName("guardarItem");

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ver");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Barra de estados");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Barra de formas");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("Barra de atributos");
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem3);

        jMenuBar1.add(jMenu2);

        imageData.setText("Imagen");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Tamaño nueva imagen");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        imageData.add(jMenuItem2);

        jMenuBar1.add(imageData);

        ayuda.setText("Ayuda");

        about.setText("Acerca de");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        ayuda.add(about);

        jMenuBar1.add(ayuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = new VentanaInternaImage(this);
        this.escritorio.add(vi);
        VentanaInternaImage vi2;
        vi2 = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi2 != null) {
            vi_p = vi2.getLocation();
            vi_p.setLocation(vi_p.x+15, vi_p.y+15);
            vi.setLocation(vi_p);
        }
        VI_Size vi_size = new VI_Size(this);
        vi_size.setVisible(true);
        // BufferedImage img;
        // img = new BufferedImage(300,300, BufferedImage.TYPE_INT_ARGB);
        img = new BufferedImage(size_vi_a, size_vi_b, BufferedImage.TYPE_INT_RGB);
        g2dvp = img.createGraphics();
        // System.out.println(rect.toString() + "\n" + g2dvp.getStroke().toString());
        g2dvp.setPaint(Color.WHITE);
        g2dvp.fillRect(0, 0, img.getHeight(), img.getWidth());        
        vi.getLienzo().setImage(img);
        vi.setVisible(true);
    }//GEN-LAST:event_newItemActionPerformed

    private void pathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathButtonActionPerformed
        // TODO add your handling code here:
        if (this.pathButton.isSelected()) {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.curvaButton.setSelected(false);
                this.areaButton.setSelected(false);
                vi.getLienzo().reset_cnt_curva();
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("trazo");
                this.jLabel1.setText("Trazo libre");
            }
        }
    }//GEN-LAST:event_pathButtonActionPerformed

    private void lineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineButtonActionPerformed
        // TODO add your handling code here:
        if (this.lineButton.isSelected()) {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                this.pathButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.curvaButton.setSelected(false);
                this.areaButton.setSelected(false);
                vi.getLienzo().reset_cnt_curva();
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("linea");
                this.jLabel1.setText("Línea");
            }
        }
    }//GEN-LAST:event_lineButtonActionPerformed

    private void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleButtonActionPerformed
        // TODO add your handling code here:
        if (this.rectangleButton.isSelected()) {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                this.lineButton.setSelected(false);
                this.pathButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.curvaButton.setSelected(false);
                this.areaButton.setSelected(false);
                vi.getLienzo().reset_cnt_curva();
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("rectangulo");
                this.jLabel1.setText("Rectángulo");
            }
        }
    }//GEN-LAST:event_rectangleButtonActionPerformed

    private void ellipseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ellipseButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi!=null) {
            if (this.ellipseButton.isSelected()) {
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.pathButton.setSelected(false);
                this.editButton.setSelected(false);
                this.curvaButton.setSelected(false);
                this.areaButton.setSelected(false);
                vi.getLienzo().reset_cnt_curva();
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("ellipse");
                this.jLabel1.setText("Elipse");
            }
        }
    }//GEN-LAST:event_ellipseButtonActionPerformed

    private void guardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarItemActionPerformed
        // TODO add your handling code here:
        // Saving file
        VentanaInternaImage vi=(VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi != null) {
            JFileChooser dlg = new JFileChooser();
            dlg.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
            int resp = dlg.showSaveDialog(this);
            
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    if (img != null) {
                        File f = dlg.getSelectedFile();
                        String filename = f.getName();
                        String file_extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                        System.out.println(file_extension);
                        ImageIO.write(img, file_extension, f);
                        vi.setTitle(f.getName());
                    }
                }catch (Exception ex) {
                    System.err.println("Error al guardar la imagen");
                }
            }
        }
    }//GEN-LAST:event_guardarItemActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = new VentanaInternaImage(this);
        if (vi!=null){
            this.escritorio.add(vi);
            VentanaInternaImage vi2;
            vi2 = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi2 != null) {
                vi_p = vi2.getLocation();
                vi_p.setLocation(vi_p.x+15, vi_p.y+15);
                vi.setLocation(vi_p);
            }
            VI_Size vi_size = new VI_Size(this);
            vi_size.setVisible(true);
            // BufferedImage img;
            // img = new BufferedImage(300,300, BufferedImage.TYPE_INT_ARGB);
            img = new BufferedImage(size_vi_a, size_vi_b, BufferedImage.TYPE_INT_RGB);
            g2dvp = img.createGraphics();
            // System.out.println(rect.toString() + "\n" + g2dvp.getStroke().toString());
            g2dvp.setPaint(Color.WHITE);
            g2dvp.fillRect(0, 0, img.getHeight(), img.getWidth());        
            vi.getLienzo().setImage(img);
            vi.setVisible(true);
        }
    }//GEN-LAST:event_nuevoActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // TODO add your handling code here:
        // Opening file
        JFileChooser dlg = new JFileChooser();
        // Aplying filters
        dlg.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
        dlg.setFileFilter(new FileNameExtensionFilter("Audio", "wav", "au"));
        dlg.setFileFilter(new FileNameExtensionFilter("Audio", "mp3", "wma",
                            "aac","mp4", "wav","au", "aiff", "ogg","aac"));
        dlg.setFileFilter(new FileNameExtensionFilter("Video", "avi", "mov", "asf",
                            "ogm", "mkv", "mpeg", "mp4", "ogg", "3gp", "mpg",
                            "wmw", "divx", "flv"));
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                String filename = f.getName();
                String file_extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                if (this.imagenes_f.contains(file_extension)){
                    BufferedImage img = ImageIO.read(f);
                    VentanaInternaImage vi = (VentanaInternaImage) new VentanaInternaImage(this);
                    vi.getLienzo().set_clip(img.getWidth(), img.getHeight());
                    vi.getLienzo().setImage(img);
                    this.escritorio.add(vi);
                    vi.setTitle(f.getName());
                    vi.setVisible(true);
                } else if (this.audio_f.contains(file_extension)) {
                    String name = dlg.getSelectedFile().toString();
                    File f1 = new File(name){
                        @Override
                        public String toString(){
                            return this.getName();
                        }
                    };
                    this.listaReproduccion.addItem(f1);
                    this.listaReproduccion.setSelectedItem(f1);
                } else if (this.video_f.contains(file_extension)) {
                    VentanaInternaMedia vm = VentanaInternaMedia.getInstance(f, this);
                    this.escritorio.add(vm);
                    vm.setTitle("VLC Player: " + filename.substring(0, filename.lastIndexOf(".")));
                    vm.setVisible(true);
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                        "No se ha podido abrir el archivo, porque no se ha reconocido"
                                + " la extensión del mismo. Pruebe con otro que sí "
                                + "pueda ser abierto.",
                        "Error al abrir el archivo",
                        JOptionPane.WARNING_MESSAGE);
                }
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }
        } else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                "No se ha podido abrir el archivo, porque no se ha reconocido"
                        + "la extensión del mismo. Pruebe con otro que sí "
                        + "pueda ser abierto.",
                "Error al abrir el archivo",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_openActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        // Saving file
        VentanaInternaImage vi=(VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi != null) {
            JFileChooser dlg = new JFileChooser();
            dlg.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
            int resp = dlg.showSaveDialog(this);
            
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    if (img != null) {
                        File f = dlg.getSelectedFile();
                        String filename = f.getName();
                        String file_extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                        System.out.println(file_extension);
                        ImageIO.write(img, file_extension, f);
                        vi.setTitle(f.getName());
                    }
                }catch (Exception ex) {
                    System.err.println("Error al guardar la imagen");
                }
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi!=null) {
            if (!vi.getLienzo().get_editar()) {
                vi.getLienzo().set_editar(true);
                this.pathButton.setSelected(false);
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.curvaButton.setSelected(false);
                this.areaButton.setSelected(false);
                this.jLabel1.setText("Editar");
                vi.getLienzo().repaint();
            } else {
                this.pathButton.setSelected(true);
                vi.getLienzo().set_modo_dibujo("trazo");
                vi.getLienzo().set_editar(false);
                vi.getLienzo().repaint();
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            vi.getLienzo().set_grosor((int) this.jSpinner2.getValue());
            // System.out.println("Grosor = " + vi.getLienzo().get_grosor());
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            /*            if (!vi.getLienzo().get_shape().get_atr().get_fill()){
            vi.getLienzo().get_shape().get_atr().set_fill(true);
            } else {
            vi.getLienzo().get_shape().get_atr().set_fill(false);
            }*/
            if (!vi.getLienzo().get_fill()) {
                vi.getLienzo().set_fill(true);
            } else {
                vi.getLienzo().set_fill(false);
            }
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            if (!vi.getLienzo().get_transparencia()) {
                vi.getLienzo().set_transparencia(true);
            } else {
                vi.getLienzo().set_transparencia(false);
            }
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton14ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi != null) {
            if (!vi.getLienzo().get_alisar()) {
                vi.getLienzo().set_alisar(true);
            } else {
                vi.getLienzo().set_alisar(false);
            }
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton14ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi!=null) {
            ComboBoxModel<Color> b = this.jComboBox1.getModel();
            Object b1 = b.getSelectedItem();
            vi.getLienzo().set_color_trace((Color)b1);
            // vi.getLienzo().get_shape().get_atr().set_color_trace((Color)b1);
            // System.out.println(b1.toString());
            // this.jComboBox1.getModel().getElementAt();
        }
        this.repaint();        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (!this.jCheckBoxMenuItem1.isSelected()) {
            this.jLabel1.setVisible(false);
        } else {
            this.jLabel1.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void abrirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirItemActionPerformed
        // TODO add your handling code here:
        // Opening file
        JFileChooser dlg = new JFileChooser();
        // Aplying filters
        dlg.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
        dlg.setFileFilter(new FileNameExtensionFilter("Audio", "wav", "au"));
        dlg.setFileFilter(new FileNameExtensionFilter("Audio", "mp3", "wma",
                            "aac","mp4", "wav","au", "aiff", "ogg","aac"));
        dlg.setFileFilter(new FileNameExtensionFilter("Video", "avi", "mov", "asf",
                            "ogm", "mkv", "mpeg", "mp4", "ogg", "3gp", "mpg",
                            "wmw", "divx", "flv"));
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                String filename = f.getName();
                String file_extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                if (this.imagenes_f.contains(file_extension)){
                    BufferedImage img = ImageIO.read(f);
                    VentanaInternaImage vi = (VentanaInternaImage) new VentanaInternaImage(this);
                    vi.getLienzo().set_clip(img.getWidth(), img.getHeight());
                    vi.getLienzo().setImage(img);
                    this.escritorio.add(vi);
                    vi.setTitle(f.getName());
                    vi.setVisible(true);
                } else if (this.audio_f.contains(file_extension)) {
                    String name = dlg.getSelectedFile().toString();
                    File f1 = new File(name){
                        @Override
                        public String toString(){
                            return this.getName();
                        }
                    };
                    this.listaReproduccion.addItem(f1);
                    this.listaReproduccion.setSelectedItem(f1);
                } else if (this.video_f.contains(file_extension)) {
                    VentanaInternaMedia vm = VentanaInternaMedia.getInstance(f, this);
                    this.escritorio.add(vm);
                    vm.setTitle("VLC Player: " + filename.substring(0, filename.lastIndexOf(".")));
                    vm.setVisible(true);
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                        "No se ha podido abrir el archivo, porque no se reconoce"
                                + " la extensión. Pruebe con otro que archivo que "
                                + "pueda ser abierto.",
                        "Extensión no reconocida",
                        JOptionPane.WARNING_MESSAGE);
                }
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }
        } else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                "No se ha podido abrir el archivo, o bien no se ha reconocido"
                        + " la extensión del mismo, o ha habido algún error."
                        + " Pruebe otra vez.",
                "Error al abrir el archivo",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_abrirItemActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (!this.jCheckBoxMenuItem2.isSelected()) {
            this.pathButton.setVisible(false);
            this.lineButton.setVisible(false);
            this.rectangleButton.setVisible(false);
            this.ellipseButton.setVisible(false);
            this.editButton.setVisible(false);
            this.jSeparator2.setVisible(false);
        } else {
            this.pathButton.setVisible(true);
            this.lineButton.setVisible(true);
            this.rectangleButton.setVisible(true);
            this.ellipseButton.setVisible(true);
            this.editButton.setVisible(true);
            this.jSeparator2.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (!this.jCheckBoxMenuItem3.isSelected()) {
            this.jToggleButton12.setVisible(false);
            this.jToggleButton13.setVisible(false);
            this.jToggleButton14.setVisible(false);
            this.jSpinner2.setVisible(false);
            this.jComboBox1.setVisible(false);
            this.jSeparator1.setVisible(false);
        } else {
            this.jToggleButton12.setVisible(true);
            this.jToggleButton13.setVisible(true);
            this.jToggleButton14.setVisible(true);
            this.jSpinner2.setVisible(true);
            this.jComboBox1.setVisible(true);
            this.jSeparator1.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        VI_Size vi_size = new VI_Size(this);
        vi_size.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void nuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nuevoKeyPressed

    private void sliderBrilloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusGained
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage)(escritorio.getSelectedFrame());
        if (vi != null) {
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgOrigen = new BufferedImage(cm,raster,alfaPre,null);
        }
    }//GEN-LAST:event_sliderBrilloFocusGained

    private void sliderBrilloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sliderBrilloFocusLost
        // TODO add your handling code here:
        imgOrigen = null;
    }//GEN-LAST:event_sliderBrilloFocusLost

    private void sliderBrilloStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderBrilloStateChanged
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage)(escritorio.getSelectedFrame());
        if (vi!=null) {
            this.brillo = this.sliderBrillo.getValue();
            RescaleOp rop =  new RescaleOp(1.0F, brillo ,null);
            rop.filter(imgOrigen,vi.getLienzo().getImage());
            // this.repaint();
            this.escritorio.repaint();
        }
    }//GEN-LAST:event_sliderBrilloStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi!= null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            Kernel k = null;
            String option = (String) this.jComboBox2.getSelectedItem();
            switch (option) {
                case "Media":
                    k = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3);
                    break;
                case "Binomial":
                    k = KernelProducer.createKernel(KernelProducer.TYPE_BINOMIAL_3x3);
                    break;
                case "Enfoque":
                    k = KernelProducer.createKernel(KernelProducer.TYPE_ENFOQUE_3x3);
                    break;
                case "Relieve":
                    k = KernelProducer.createKernel(KernelProducer.TYPE_RELIEVE_3x3);
                    break;
                case "Fronteras":
                    k = KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3);
                    break;
                default:
                    k = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3);
                    break;
            }
            ConvolveOp cop = new ConvolveOp(k);
            BufferedImage imgDest = cop.filter(imgSource, null);
            vi.getLienzo().setImage(imgDest);
            // System.out.println(b1.toString());
            // this.jComboBox1.getModel().getElementAt();
            this.repaint();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void contrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasteActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    LookupTable lt = LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_SFUNCION);
                    LookupOp lop = new LookupOp (lt, null);
                    lop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_contrasteActionPerformed

    private void iluminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iluminarActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    LookupTable lt = LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_LOGARITHM);
                    LookupOp lop = new LookupOp (lt, null);
                    lop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_iluminarActionPerformed

    private void oscurecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oscurecerActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    LookupTable lt = LookupTableProducer.createLookupTable(LookupTableProducer.TYPE_POWER);
                    LookupOp lop = new LookupOp (lt, null);
                    lop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_oscurecerActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage)(escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    this.grados = this.jSlider1.getValue();
                    double r = Math.toRadians(grados);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r, c.x, c.y);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgOrigen, imgSource);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
        // Poner null y hacer un setimagen en el lienzo correspondiente
    }//GEN-LAST:event_jSlider1StateChanged
    
    private void sinosoidalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinosoidalActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    LookupTable lte = LookupTableExtension.seno(180.0);
                    LookupOp lop = new LookupOp (lte, null);
                    lop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_sinosoidalActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    AffineTransform at = AffineTransform.getScaleInstance(1.25,1.25);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    AffineTransform at = AffineTransform.getScaleInstance(0.75,0.75);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    double r = Math.toRadians(90);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r, c.x, c.y);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    double r = Math.toRadians(180);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r, c.x, c.y);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    double r = Math.toRadians(270);
                    Point c = new Point(imgSource.getWidth()/2, imgSource.getHeight()/2);
                    AffineTransform at = AffineTransform.getRotateInstance(r, c.x, c.y);
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage imgdest = atop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgdest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jSlider1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSlider1FocusGained
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage)(escritorio.getSelectedFrame());
        if (vi != null) {
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgOrigen = new BufferedImage(cm,raster,alfaPre,null);
        }        
    }//GEN-LAST:event_jSlider1FocusGained

    private void bandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bandasActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            
            /*float [][] m = {{1.0F,0.0F,0.0F},{0.0F,0.0F,1.0F},{0.0F,1.0F,0.0F}};
            BandCombineOp bcop = new BandCombineOp(m, null);
            WritableRaster rasterDest = bcop.filter(vi.getLienzo().getImage().getRaster(), null);*/
            
            for (int i=0; i<vi.getLienzo().getImage().getRaster().getNumBands(); ++i) {
                BufferedImage imgDest = vi.getLienzo().getBanda(vi.getLienzo().getImage(), i);
                VentanaInternaImage vi2 = new VentanaInternaImage(this);
                VentanaInternaImage vi3;
                vi3 = (VentanaInternaImage)escritorio.getSelectedFrame();
                this.escritorio.add(vi2);
                vi2.getLienzo().setImage(imgDest);
                vi2.setVisible(true);        
                vi_p = vi3.getLocation();
                vi_p.setLocation(vi_p.x+15, vi_p.y+15);
                vi2.setLocation(vi_p);
            }
            
        }
    }//GEN-LAST:event_bandasActionPerformed

    private void sepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sepiaActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    SepiaOp seop = new SepiaOp();
                    BufferedImage imgDest = null;
                    imgDest = seop.filter(imgSource, imgDest);
                    vi.getLienzo().setImage(imgDest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_sepiaActionPerformed

    private void espaciocolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espaciocolorActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)this.escritorio.getSelectedFrame();
        if (vi!=null) {
            String color_space = (String) this.espaciocolor.getSelectedItem();
            BufferedImage src = vi.getLienzo().getImage();
            BufferedImage imgOut = null;
            ColorConvertOp cop = null;
            switch(color_space) {
                case "sRGB":
                    if (src.getColorModel().getColorSpace().isCS_sRGB()) {
                        imgOut = src;
                    } else {
                        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
                        cop = new ColorConvertOp(cs, null);
                    }
                    break;
                case "YCC":
                    if (src.getColorModel().getColorSpace().isCS_sRGB() ||
                            src.getColorModel().getColorSpace().getType() == ColorSpace.CS_GRAY) {
                        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_PYCC);
                        cop = new ColorConvertOp(cs, null);
                    } else {
                        imgOut = src;
                    }
                    break;
                case "GREY":
                    if (src.getColorModel().getColorSpace().isCS_sRGB() || 
                            src.getColorModel().getColorSpace().getType() == ColorSpace.CS_PYCC) {
                        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
                        cop = new ColorConvertOp(cs, null);
                    } else {
                        imgOut = src;
                    }
                    break;
                default:
                    if (src.getColorModel().getColorSpace().isCS_sRGB()) {
                        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_PYCC);
                        cop = new ColorConvertOp(cs, null);
                    } else if (src.getColorModel().getColorSpace().getType() == ColorSpace.CS_GRAY ||
                            src.getColorModel().getColorSpace().getType() == ColorSpace.CS_PYCC) {
                        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
                        cop = new ColorConvertOp(cs, null);
                    }
                    break;
            }


            imgOut = cop.filter(src, null);
            // Mostramos la imagen final por pantalla
            VentanaInternaImage vi2 = new VentanaInternaImage(this);
            this.escritorio.add(vi2);
            vi2.getLienzo().setImage(imgOut);
            vi2.setVisible(true);
            vi_p = vi.getLocation();
            vi_p.setLocation(vi_p.x+15, vi_p.y+15);
            vi2.setLocation(vi_p);
        }
    }//GEN-LAST:event_espaciocolorActionPerformed

    private void jSlider1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSlider1FocusLost
        // TODO add your handling code here:
        imgOrigen = null;
    }//GEN-LAST:event_jSlider1FocusLost

    private void tintadoOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tintadoOpActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            Color c = vi.getLienzo().get_color_trace();
            if (c==null) {
                c = Color.BLACK;
            }
            // Color c = Color.BLACK; //PROVISIONAL
            TintOp tintado = new TintOp(c, 0.5f);
            tintado.filter(imgSource, imgSource);
            this.repaint();
        }
    }//GEN-LAST:event_tintadoOpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                EqualizationOp ecualizacion = new EqualizationOp();
                ecualizacion.filter(imgSource, imgSource);
                vi.repaint();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void umbralizacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umbralizacionFocusGained
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage)(escritorio.getSelectedFrame());
        if (vi != null) {
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            imgOrigen = new BufferedImage(cm,raster,alfaPre,null);
        }
    }//GEN-LAST:event_umbralizacionFocusGained

    private void umbralizacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umbralizacionFocusLost
        // TODO add your handling code here:
        imgOrigen = null;
    }//GEN-LAST:event_umbralizacionFocusLost

    private void umbralizacionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_umbralizacionStateChanged
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    UmbralizationOp umop = new UmbralizationOp(this.umbralizacion.getValue());
                    BufferedImage imgDest = umop.filter(imgSource, null);
                    vi.getLienzo().setImage(imgDest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_umbralizacionStateChanged

    private void playSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playSoundActionPerformed
        // TODO add your handling code here:
        File f = (File)this.listaReproduccion.getSelectedItem();
        if(f!=null) {
            // player = new SMClipPlayer(f);
            player = new SMSoundPlayer(f);
            player.addLineListener(new ManejadorAudio());
            if(player!=null) {
                player.play();
                this.audio = true;
            }
        }
    }//GEN-LAST:event_playSoundActionPerformed

    private void stopAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopAudioActionPerformed
        // TODO add your handling code here:
        if(player!=null) {
            this.player.stop();
            this.player = null;
        } else if(recorder!=null){
            this.recorder.stop();
            JFileChooser dlg = new JFileChooser();
            dlg.setFileFilter(new FileNameExtensionFilter("Audio(.au)", "wav", "au"));
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    String name = dlg.getSelectedFile().toString();
                    File f = new File(name + ".au"){
                    @Override
                     public String toString(){
                        return this.getName();
                        }
                     };
                    this.grabatmp.renameTo(f);
                    this.listaReproduccion.addItem(f);
                    this.listaReproduccion.setSelectedItem(f);
                }catch (Exception ex) {
                    System.err.println("Error al guardar el archivo de audio");
                }
                /*File f = new File(dlg.getSelectedFile()+".jpg");
                        String filename = f.getName();
                        String file_extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                        ImageIO.write(img, file_extension, f);*/                        
            } else  {
                this.grabatmp.delete();
            }
            
            this.recorder = null;
            // this.grabatmp = null;
        }
    }//GEN-LAST:event_stopAudioActionPerformed

    private void recordAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordAudioActionPerformed
        // TODO add your handling code here:
        recorder = new SMSoundRecorder(this.grabatmp);
        recorder.addLineListener(new ManejadorAudio());
        if (recorder!=null) {
            this.recorder.record();
            this.recordAudio.setEnabled(false);
        }
    }//GEN-LAST:event_recordAudioActionPerformed

    private void webCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webCamActionPerformed
        // TODO add your handling code here:
        VentanaInternaWebCam v = VentanaInternaWebCam.getInstance(this);
        if(v!=null){
            v.setTitle("WebCam");
            this.escritorio.add(v);
            v.setVisible(true);
        }
    }//GEN-LAST:event_webCamActionPerformed

    private void webcamCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webcamCapturaActionPerformed
        // TODO add your handling code here:
        VentanaInternaWebCam v;
        try {
            v = (VentanaInternaWebCam) this.escritorio.getSelectedFrame();
            if (v != null) {
                BufferedImage img = v.getCamera().getImage();
                if (img != null) {
                    VentanaInternaImage vi = new VentanaInternaImage(this);
                    vi.getLienzo().setImage(img);
                    this.escritorio.add(vi);
                    vi.setTitle("Captura WebCam");
                    vi.setLocation(v.getX()+10, v.getY()+10);
                    vi.setVisible(true);
                }
            }
        }catch(ClassCastException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_webcamCapturaActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi!=null) {
            ComboBoxModel<Color> b = this.jComboBox3.getModel();
            Object b1 = b.getSelectedItem();
            // vi.getLienzo().set_color_trace((Color)b1);
            // vi.getLienzo().get_shape().get_atr().set_fill(true);
            // vi.getLienzo().get_shape().get_atr().set_color_fill((Color)b1);
            vi.getLienzo().set_color_fill((Color)b1);
            // System.out.println(b1.toString());
            // this.jComboBox1.getModel().getElementAt();
        }
        this.repaint();   
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            vi.getLienzo().set_grosor((int) this.jSpinner2.getValue());
            // System.out.println("Grosor = " + vi.getLienzo().get_grosor());
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void curvaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curvaButtonActionPerformed
        // TODO add your handling code here:
        if (this.curvaButton.isSelected()) {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                this.lineButton.setSelected(false);
                this.pathButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.areaButton.setSelected(false);
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("curva");
                this.jLabel1.setText("Curva");
            }
        } else {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                vi.getLienzo().reset_cnt_curva();
            }
        }
    }//GEN-LAST:event_curvaButtonActionPerformed

    private void areaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaButtonActionPerformed
        // TODO add your handling code here:
        if (this.areaButton.isSelected()) {
            VentanaInternaImage vi;
            vi = (VentanaInternaImage)escritorio.getSelectedFrame();
            if (vi!=null) {
                this.lineButton.setSelected(false);
                this.pathButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.curvaButton.setSelected(false);
                vi.getLienzo().set_editar(false);
                vi.getLienzo().set_modo_dibujo("area");
                vi.getLienzo().reset_cnt_curva();
                this.jLabel1.setText("Area");
            }
        }
    }//GEN-LAST:event_areaButtonActionPerformed

    private void fondoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondoButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            vi.getLienzo().set_fondo();
            vi.repaint();
        }
    }//GEN-LAST:event_fondoButtonActionPerformed

    private void frenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frenteButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            vi.getLienzo().set_frente();
            vi.repaint();
        }
    }//GEN-LAST:event_frenteButtonActionPerformed

    private void atrasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            vi.getLienzo().set_atras();
            vi.repaint();
        }
    }//GEN-LAST:event_atrasButtonActionPerformed

    private void adelanteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adelanteButtonActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            vi.getLienzo().set_adelante();
            vi.repaint();
        }
    }//GEN-LAST:event_adelanteButtonActionPerformed

    private void negativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negativoActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            // Color c = vi.getLienzo().get_color();
            LookupTable lte = LookupTableExtension.negativo();
            LookupOp neg = new LookupOp(lte, null);
            BufferedImage imgDest = neg.filter(imgSource, null);
            vi.getLienzo().setImage(imgDest);
            this.repaint();
        }
    }//GEN-LAST:event_negativoActionPerformed

    private void duplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicarActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            boolean alfaPre = vi.getLienzo().getImage().isAlphaPremultiplied();
            BufferedImage imgCopia = new BufferedImage(cm, raster, alfaPre, null);
            VentanaInternaImage vi2 = new VentanaInternaImage(this);
            escritorio.add(vi2);
            vi2.setLocation(vi.getX()+5, vi.getY()+5);
            vi2.getLienzo().setImage(imgCopia);
            vi2.getLienzo().repaint();
            vi2.setTitle("Imagen Copia");
            vi2.setVisible(true);
        }
    }//GEN-LAST:event_duplicarActionPerformed

    private void redOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redOpActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    RedOp reop = new RedOp();
                    BufferedImage imgDest = null;
                    imgDest = reop.filter(imgSource, imgDest);
                    vi.getLienzo().setImage(imgDest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_redOpActionPerformed

    private void cosenoidalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cosenoidalActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    LookupTable lte = LookupTableExtension.own(180.0);
                    LookupOp lop = new LookupOp (lte, null);
                    lop.filter(imgSource, imgSource);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_cosenoidalActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(this);
        JOptionPane.showMessageDialog(frame,
            "SM Práctica final\n" + "Versión 0.2\n" + 
                    "Práctica realizada por Alberto Argente del Castillo Garrido",
            "Acerca de",
            JOptionPane.PLAIN_MESSAGE
            );
    }//GEN-LAST:event_aboutActionPerformed

    private void compOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compOpActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) (escritorio.getSelectedFrame());
        if (vi != null) {
            BufferedImage imgSource = vi.getLienzo().getImage();
            if(imgSource!=null){
                try{
                    // LookupOp lp = new LookupOp();
                    CompOp coop = new CompOp();
                    BufferedImage imgDest = null;
                    imgDest = coop.filter(imgSource, imgDest);
                    vi.getLienzo().setImage(imgDest);
                    vi.getLienzo().repaint();
                } catch(IllegalArgumentException e){
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_compOpActionPerformed

    private void modoStrokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoStrokeActionPerformed
        // TODO add your handling code here:
        VentanaInternaImage vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            vi.getLienzo().set_mode_stroke((String)this.modoStroke.getSelectedItem());
            vi.repaint();
        }
    }//GEN-LAST:event_modoStrokeActionPerformed

    private void mediaSnapshootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaSnapshootActionPerformed
        // TODO add your handling code here:
        VentanaInternaMedia vm;
        try {
            vm = (VentanaInternaMedia) this.escritorio.getSelectedFrame();
            if (vm != null) {
                BufferedImage img = vm.screenshoot();
                if (img != null) {
                    VentanaInternaImage vi = new VentanaInternaImage(this);
                    vi.getLienzo().setImage(img);
                    this.escritorio.add(vi);
                    vi.setTitle("Captura Media");
                    vi.setLocation(vm.getX()+10, vm.getY()+10);
                    vi.setVisible(true);
                }
                img = null;
                vm = null;
            }
        }catch(ClassCastException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_mediaSnapshootActionPerformed
     
    
    
    public void set_imgx_imgy (int imgx, int imgy) {
        VentanaInternaImage vi;
        vi = (VentanaInternaImage)escritorio.getSelectedFrame();
        vi.set_imgx(imgx);
        vi.set_imgy(imgy);
        
    }
    public void set_jlabel2 (int x, int y) {
        this.jLabel2.setText("(" + x + ", " + y + ")");
    }
    public void set_lienzo_atributos(String s, Color c ,boolean transparencia,
            boolean fill, float grosor, boolean alisar, boolean editar, Color c1,
            String s1){
        switch (s) {
            case "trazo":
                this.pathButton.setSelected(true);
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.jLabel1.setText("trazo");
                break;
            case "linea":
                this.pathButton.setSelected(false);
                this.lineButton.setSelected(true);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.jLabel1.setText("Línea");
                break;
            case "rectangulo":
                this.pathButton.setSelected(false);
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(true);
                this.ellipseButton.setSelected(false);
                this.editButton.setSelected(false);
                this.jLabel1.setText("Rectangulo");
                break;
            case "ellipse":
                this.pathButton.setSelected(false);
                this.lineButton.setSelected(false);
                this.rectangleButton.setSelected(false);
                this.ellipseButton.setSelected(true);
                this.editButton.setSelected(false);
                this.jLabel1.setText("Punto");
                break;
        }
        if (editar) {
            this.pathButton.setSelected(false);
            this.lineButton.setSelected(false);
            this.rectangleButton.setSelected(false);
            this.ellipseButton.setSelected(false);
            this.editButton.setSelected(true);
            this.jLabel1.setText("Editar");
        }
        if (transparencia) {
            this.jToggleButton13.setSelected(true);
        } else {
            this.jToggleButton13.setSelected(false);
        }
        if (fill) {
            this.jToggleButton12.setSelected(true);
        } else {
            this.jToggleButton12.setSelected(false);
        }
        if (alisar) {
            this.jToggleButton14.setSelected(true);
        } else {
            this.jToggleButton14.setSelected(false);
        }
        
        this.jComboBox1.setSelectedItem(c);
        this.jComboBox3.setSelectedItem(c1);
        this.modoStroke.setSelectedItem(s1);
    }
    
    public void set_new_clipArea(int x, int y) {
        VentanaInternaImage vi;
        vi = (VentanaInternaImage) escritorio.getSelectedFrame();
        if (vi!=null) {
            ColorModel cm = vi.getLienzo().getImage().getColorModel();
            WritableRaster raster = vi.getLienzo().getImage().copyData(null);
            // img = new BufferedImage(cm,raster,alfaPre,null);
            img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
            BufferedImage img1 = new BufferedImage(cm,raster, true, null);
            img1.setData(raster);
            g2dvp = img.createGraphics();
            g2dvp.setPaint(Color.WHITE);
            g2dvp.fillRect(0, 0, x, y);
            vi.getLienzo().setImage(img);
            vi.getLienzo().set_clip(x, y);
            vi.set_imgx(x);
            vi.set_imgy(y);
            vi.getLienzo().repaint();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem abrirItem;
    private javax.swing.JButton adelanteButton;
    private javax.swing.JToggleButton areaButton;
    private javax.swing.JButton atrasButton;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton bandas;
    private javax.swing.JButton compOp;
    private javax.swing.JButton contraste;
    private javax.swing.JButton cosenoidal;
    private javax.swing.JToggleButton curvaButton;
    private javax.swing.JButton duplicar;
    private javax.swing.JToggleButton editButton;
    private javax.swing.JToggleButton ellipseButton;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JComboBox<String> espaciocolor;
    private javax.swing.JButton fondoButton;
    private javax.swing.JButton frenteButton;
    private javax.swing.JMenuItem guardarItem;
    private javax.swing.JButton iluminar;
    private javax.swing.JMenu imageData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JComboBox<Color> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<Color> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToggleButton lineButton;
    private javax.swing.JComboBox<File> listaReproduccion;
    private javax.swing.JButton mediaSnapshoot;
    private javax.swing.JComboBox<String> modoStroke;
    private javax.swing.JButton negativo;
    private javax.swing.JMenuItem newItem;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton open;
    private javax.swing.JButton oscurecer;
    private javax.swing.JToggleButton pathButton;
    private javax.swing.JButton playSound;
    private javax.swing.JButton recordAudio;
    private javax.swing.JToggleButton rectangleButton;
    private javax.swing.JButton redOp;
    private javax.swing.JButton save;
    private javax.swing.JButton sepia;
    private javax.swing.JButton sinosoidal;
    private javax.swing.JSlider sliderBrillo;
    private javax.swing.JButton stopAudio;
    private javax.swing.JButton tintadoOp;
    private javax.swing.JSlider umbralizacion;
    private javax.swing.JButton webCam;
    private javax.swing.JButton webcamCaptura;
    // End of variables declaration//GEN-END:variables
}
