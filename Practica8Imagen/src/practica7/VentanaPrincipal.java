/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.aadcg.graficos.ColorCellRender;

/**
 *
 * @author alberto
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    private Point vi_p = new Point(0,0);
    private int size_vi_a = 300;
    private int size_vi_b = 300;
    public VentanaPrincipal() {
        initComponents();
        this.setSize(1024, 720);
        this.jCheckBoxMenuItem1.setSelected(true);
        // this.jPanel4.setVisible(false);
        jButton1.setToolTipText("Nuevo");
        jButton2.setToolTipText("Abrir");
        jButton3.setToolTipText("Guardar");
        jToggleButton1.setToolTipText("Punto");
        jToggleButton2.setToolTipText("Línea");
        jToggleButton3.setToolTipText("Rectángulo");
        jToggleButton4.setToolTipText("Elipse");
        jToggleButton11.setToolTipText("Editar");
        jToggleButton12.setToolTipText("Rellenar");
        jToggleButton13.setToolTipText("Transparencia");
        jToggleButton14.setToolTipText("Alisar");
        jSpinner2.setToolTipText("Grosor");
        jComboBox1.setToolTipText("Color");
        this.setLocationRelativeTo(null);
    }

    public VentanaInterna getVentanaInterna() {
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        Color c[] = {Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        jComboBox1 = new javax.swing.JComboBox<>(c);
        jSpinner2 = new javax.swing.JSpinner();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator2);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/punto.png"))); // NOI18N
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton1);

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/linea.png"))); // NOI18N
        jToggleButton2.setFocusable(false);
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton2);

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rectangulo.png"))); // NOI18N
        jToggleButton3.setFocusable(false);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton3);

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/elipse.png"))); // NOI18N
        jToggleButton4.setFocusable(false);
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton4);

        jToggleButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccion.png"))); // NOI18N
        jToggleButton11.setFocusable(false);
        jToggleButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton11);
        jToolBar1.add(jSeparator1);

        jComboBox1.setRenderer(new ColorCellRender());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner2.setMaximumSize(new java.awt.Dimension(38, 26));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });
        jToolBar1.add(jSpinner2);

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

        jPanel1.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

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

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi = new VentanaInterna(this);
        this.escritorio.add(vi);
        VentanaInterna vi2;
        vi2 = (VentanaInterna)escritorio.getSelectedFrame();
        if (vi2 != null) {
            vi_p = vi2.getLocation();
            vi_p.setLocation(vi_p.x+15, vi_p.y+15);
            vi.setLocation(vi_p);
        } 
        vi.setVisible(true);
        BufferedImage img;
        // img = new BufferedImage(300,300, BufferedImage.TYPE_INT_ARGB);
        img = new BufferedImage(size_vi_a, size_vi_b, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dvp = img.createGraphics();
        g2dvp.setPaint(Color.WHITE);
        g2dvp.fillRect(0, 0, img.getWidth(), img.getHeight());
        vi.getLienzo().setImage(img);
    }//GEN-LAST:event_newItemActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton1.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton2.setSelected(false);
            this.jToggleButton3.setSelected(false);
            this.jToggleButton4.setSelected(false);
            this.jToggleButton11.setSelected(false);
            vi.getLienzo().set_editar(false);
            vi.getLienzo().set_modo_dibujo("punto");
            this.jLabel1.setText("Punto");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton2.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton1.setSelected(false);
            this.jToggleButton3.setSelected(false);
            this.jToggleButton4.setSelected(false);
            this.jToggleButton11.setSelected(false);
            vi.getLienzo().set_editar(false);
            vi.getLienzo().set_modo_dibujo("linea");
            this.jLabel1.setText("Línea");
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton3.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton2.setSelected(false);
            this.jToggleButton1.setSelected(false);
            this.jToggleButton4.setSelected(false);
            this.jToggleButton11.setSelected(false);
            vi.getLienzo().set_editar(false);
            vi.getLienzo().set_modo_dibujo("rectangulo");
            this.jLabel1.setText("Rectángulo");
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        
        if (this.jToggleButton4.isSelected()) {
            this.jToggleButton2.setSelected(false);
            this.jToggleButton3.setSelected(false);
            this.jToggleButton1.setSelected(false);
            this.jToggleButton11.setSelected(false);
            vi.getLienzo().set_editar(false);
            vi.getLienzo().set_modo_dibujo("ellipse");
            this.jLabel1.setText("Elipse");
        }
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void guardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarItemActionPerformed
        // TODO add your handling code here:
        // Saving file
        VentanaInterna vi=(VentanaInterna) escritorio.getSelectedFrame();
        if (vi != null) {
            JFileChooser dlg = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes(.jpg)", "jpg");
            dlg.setFileFilter(filter);
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    if (img != null) {
                        File f = new File(dlg.getSelectedFile()+".jpg");
                        ImageIO.write(img, "jpg", f);
                        vi.setTitle(f.getName());
                    }
                }catch (Exception ex) {
                    System.err.println("Error al guardar la imagen");
                }
            }
        }
    }//GEN-LAST:event_guardarItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi = new VentanaInterna(this);
        this.escritorio.add(vi);
        VentanaInterna vi2;
        vi2 = (VentanaInterna)escritorio.getSelectedFrame();
        if (vi2 != null) {
            vi_p = vi2.getLocation();
            vi_p.setLocation(vi_p.x+15, vi_p.y+15);
            vi.setLocation(vi_p);
        } 
        vi.setVisible(true);
        BufferedImage img;
        // img = new BufferedImage(300,300, BufferedImage.TYPE_INT_ARGB);
        img = new BufferedImage(size_vi_a, size_vi_b, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dvp = img.createGraphics();
        g2dvp.setPaint(Color.WHITE);
        g2dvp.fillRect(0, 0, img.getWidth(), img.getHeight());
        vi.getLienzo().setImage(img);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Opening file
        JFileChooser dlg = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes(.jpg)", "jpg");
        dlg.setFileFilter(filter);
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                VentanaInterna vi = new VentanaInterna(this);
                vi.getLienzo().setImage(img);
                this.escritorio.add(vi);
                vi.setTitle(f.getName());
                vi.setVisible(true);
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Saving file
        VentanaInterna vi=(VentanaInterna) escritorio.getSelectedFrame();
        if (vi != null) {
            JFileChooser dlg = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes(.jpg)", "jpg");
            dlg.setFileFilter(filter);
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    if (img != null) {
                        File f = new File(dlg.getSelectedFile()+".jpg");
                        ImageIO.write(img, "jpg", f);
                        vi.setTitle(f.getName());
                    }
                }catch (Exception ex) {
                    System.err.println("Error al guardar la imagen");
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_editar()) {
            vi.getLienzo().set_editar(true);
            this.jToggleButton1.setSelected(false);
            this.jToggleButton2.setSelected(false);
            this.jToggleButton3.setSelected(false);
            this.jToggleButton4.setSelected(false);
            this.jLabel1.setText("Editar");
            vi.getLienzo().repaint();
        } else {
            vi.getLienzo().set_editar(false);
        }
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        vi.getLienzo().set_grosor((int) this.jSpinner2.getValue());
        System.out.println("Grosor = " + vi.getLienzo().get_grosor());
        vi.getLienzo().repaint();
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_fill()) {
            vi.getLienzo().set_fill(true);
        } else {
            vi.getLienzo().set_fill(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_transparencia()) {
            vi.getLienzo().set_transparencia(true);
        } else {
            vi.getLienzo().set_transparencia(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton14ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_alisar()) {
            vi.getLienzo().set_alisar(true);
        } else {
            vi.getLienzo().set_alisar(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_jToggleButton14ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        ComboBoxModel<Color> b = this.jComboBox1.getModel();
        Object b1 = b.getSelectedItem();
        vi.getLienzo().set_color((Color)b1);
        // System.out.println(b1.toString());
        // this.jComboBox1.getModel().getElementAt();
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
        JFileChooser dlg = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes(.jpg)", "jpg");
        dlg.setFileFilter(filter);
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                VentanaInterna vi = new VentanaInterna(this);
                vi.getLienzo().setImage(img);
                this.escritorio.add(vi);
                vi.setTitle(f.getName());
                vi.setVisible(true);
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }
        }
    }//GEN-LAST:event_abrirItemActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (!this.jCheckBoxMenuItem2.isSelected()) {
            this.jToggleButton1.setVisible(false);
            this.jToggleButton2.setVisible(false);
            this.jToggleButton3.setVisible(false);
            this.jToggleButton4.setVisible(false);
            this.jToggleButton11.setVisible(false);
            this.jSeparator2.setVisible(false);
        } else {
            this.jToggleButton1.setVisible(true);
            this.jToggleButton2.setVisible(true);
            this.jToggleButton3.setVisible(true);
            this.jToggleButton4.setVisible(true);
            this.jToggleButton11.setVisible(true);
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

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1KeyPressed

    public void set_imgx_imgy (int imgx, int imgy) {
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        vi.set_imgx(imgx);
        vi.set_imgy(imgy);
        
    }
    public void set_jlabel2 (int x, int y) {
        this.jLabel2.setText("(" + x + ", " + y + ")");
    }
    public void set_lienzo_atributos(String s, Color c ,boolean transparencia,
            boolean fill, float grosor, boolean alisar){
        switch (s) {
            case "punto":
                this.jToggleButton1.setSelected(true);
                this.jToggleButton2.setSelected(false);
                this.jToggleButton3.setSelected(false);
                this.jToggleButton4.setSelected(false);
                this.jToggleButton11.setSelected(false);
                this.jLabel1.setText("Punto");
                break;
            case "linea":
                this.jToggleButton1.setSelected(false);
                this.jToggleButton2.setSelected(true);
                this.jToggleButton3.setSelected(false);
                this.jToggleButton4.setSelected(false);
                this.jToggleButton11.setSelected(false);
                this.jLabel1.setText("Línea");
                break;
            case "rectangulo":
                this.jToggleButton1.setSelected(false);
                this.jToggleButton2.setSelected(false);
                this.jToggleButton3.setSelected(true);
                this.jToggleButton4.setSelected(false);
                this.jToggleButton11.setSelected(false);
                this.jLabel1.setText("Rectangulo");
                break;
            case "ellipse":
                this.jToggleButton1.setSelected(false);
                this.jToggleButton2.setSelected(false);
                this.jToggleButton3.setSelected(false);
                this.jToggleButton4.setSelected(true);
                this.jToggleButton11.setSelected(false);
                this.jLabel1.setText("Punto");
                break;
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
        
        JButton b = new JButton();
        ComboBoxModel<Color> cb = this.jComboBox1.getModel();
        // Object b1 = cb.setSelectedItem(b);
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirItem;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem guardarItem;
    private javax.swing.JMenu imageData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JComboBox<Color> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem newItem;
    // End of variables declaration//GEN-END:variables
}