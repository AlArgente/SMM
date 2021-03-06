/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author alberto
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
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

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jTextPane1 = new javax.swing.JTextPane();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        fill = new javax.swing.JCheckBox();
        transparencia = new javax.swing.JCheckBox();
        alisar = new javax.swing.JCheckBox();
        editar = new javax.swing.JCheckBox();
        escritorio = new javax.swing.JDesktopPane();
        ventanaInterna2 = new practica7.VentanaInterna();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newItem = new javax.swing.JMenuItem();
        abrirItem = new javax.swing.JMenuItem();
        guardarItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

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

        jPanel1.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTextPane1.setText("Barra de estado");
        jPanel2.add(jTextPane1, java.awt.BorderLayout.SOUTH);

        jToolBar2.setRollover(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Color", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setMaximumSize(new java.awt.Dimension(480, 214));
        jPanel3.setLayout(new java.awt.GridLayout(2, 3));

        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rblackcircle.png"))); // NOI18N
        jToggleButton5.setFocusable(false);
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton5);

        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rredcircle.png"))); // NOI18N
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton7);

        jToggleButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rbluecircle.png"))); // NOI18N
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton9);

        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rwhitecircle.png"))); // NOI18N
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton6);

        jToggleButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ryellowcircle.png"))); // NOI18N
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton8);

        jToggleButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rgreencircle.png"))); // NOI18N
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton10);

        jToolBar2.add(jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grosor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel5.setToolTipText("");
        jPanel5.setMaximumSize(new java.awt.Dimension(480, 214));
        jPanel5.setName(""); // NOI18N

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner1.setMaximumSize(new java.awt.Dimension(38, 26));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jPanel5.add(jSpinner1);

        jToolBar2.add(jPanel5);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel7.setMaximumSize(new java.awt.Dimension(480, 214));
        jPanel7.setPreferredSize(new java.awt.Dimension(270, 71));
        jPanel7.setLayout(new java.awt.GridLayout(2, 2));

        fill.setText("Relleno");
        fill.setToolTipText("");
        fill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillActionPerformed(evt);
            }
        });
        jPanel7.add(fill);

        transparencia.setText("Transparencia");
        transparencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transparenciaActionPerformed(evt);
            }
        });
        jPanel7.add(transparencia);

        alisar.setText("Alisar");
        alisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alisarActionPerformed(evt);
            }
        });
        jPanel7.add(alisar);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel7.add(editar);

        jToolBar2.add(jPanel7);

        jPanel2.add(jToolBar2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        escritorio.setLayout(new java.awt.BorderLayout());

        ventanaInterna2.setVisible(true);
        escritorio.add(ventanaInterna2, java.awt.BorderLayout.CENTER);

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Archivo");

        newItem.setText("Nuevo");
        newItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemActionPerformed(evt);
            }
        });
        jMenu1.add(newItem);
        newItem.getAccessibleContext().setAccessibleDescription("");

        abrirItem.setText("Abrir");
        abrirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirItem);
        abrirItem.getAccessibleContext().setAccessibleName("abrirItem");

        guardarItem.setText("Guardar");
        guardarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarItemActionPerformed(evt);
            }
        });
        jMenu1.add(guardarItem);
        guardarItem.getAccessibleContext().setAccessibleName("guardarItem");

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edición");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi = new VentanaInterna();
        this.escritorio.add(vi);
        vi.setVisible(true);
        BufferedImage img;
        img = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
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
            vi.getLienzo().set_modo_dibujo("punto");
            this.jTextPane1.setText("Punto");
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
            vi.getLienzo().set_modo_dibujo("linea");
            this.jTextPane1.setText("Línea");
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
            vi.getLienzo().set_modo_dibujo("rectangulo");
            this.jTextPane1.setText("Rectángulo");
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
            vi.getLienzo().set_modo_dibujo("ellipse");
            this.jTextPane1.setText("Elipse");
        }
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton5.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton6.setSelected(false);
            this.jToggleButton7.setSelected(false);
            this.jToggleButton8.setSelected(false);
            this.jToggleButton9.setSelected(false);
            this.jToggleButton10.setSelected(false);
            vi.getLienzo().set_color(Color.BLACK);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton6.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton5.setSelected(false);
            this.jToggleButton7.setSelected(false);
            this.jToggleButton8.setSelected(false);
            this.jToggleButton9.setSelected(false);
            this.jToggleButton10.setSelected(false);
            vi.getLienzo().set_color(Color.WHITE);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton7.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton6.setSelected(false);
            this.jToggleButton5.setSelected(false);
            this.jToggleButton8.setSelected(false);
            this.jToggleButton9.setSelected(false);
            this.jToggleButton10.setSelected(false);
            vi.getLienzo().set_color(Color.RED);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // TODO add your handling code here:
        if (this.jToggleButton8.isSelected()) {
            VentanaInterna vi;
            vi = (VentanaInterna)escritorio.getSelectedFrame();
            this.jToggleButton6.setSelected(false);
            this.jToggleButton7.setSelected(false);
            this.jToggleButton5.setSelected(false);
            this.jToggleButton9.setSelected(false);
            this.jToggleButton10.setSelected(false);
            vi.getLienzo().set_color(Color.YELLOW);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (this.jToggleButton9.isSelected()) {
            this.jToggleButton6.setSelected(false);
            this.jToggleButton7.setSelected(false);
            this.jToggleButton8.setSelected(false);
            this.jToggleButton5.setSelected(false);
            this.jToggleButton10.setSelected(false);
            vi.getLienzo().set_color(Color.BLUE);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (this.jToggleButton10.isSelected()) {
            this.jToggleButton6.setSelected(false);
            this.jToggleButton7.setSelected(false);
            this.jToggleButton8.setSelected(false);
            this.jToggleButton9.setSelected(false);
            this.jToggleButton5.setSelected(false);
            vi.getLienzo().set_color(Color.GREEN);
            vi.getLienzo().repaint();
        }
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void fillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_fill()) {
            vi.getLienzo().set_fill(true);
        } else {
            vi.getLienzo().set_fill(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_fillActionPerformed

    private void transparenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transparenciaActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_transparencia()) {
            vi.getLienzo().set_transparencia(true);
        } else {
            vi.getLienzo().set_transparencia(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_transparenciaActionPerformed

    private void alisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alisarActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_alisar()) {
            vi.getLienzo().set_alisar(true);
        } else {
            vi.getLienzo().set_alisar(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_alisarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        if (!vi.getLienzo().get_editar()) {
            vi.getLienzo().set_editar(true);
        } else {
            vi.getLienzo().set_editar(false);
        }
        vi.getLienzo().repaint();
    }//GEN-LAST:event_editarActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        VentanaInterna vi;
        vi = (VentanaInterna)escritorio.getSelectedFrame();
        vi.getLienzo().set_grosor((int) this.jSpinner1.getValue());
        System.out.println("Grosor = " + vi.getLienzo().get_grosor());
        vi.getLienzo().repaint();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void abrirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirItemActionPerformed
        // TODO add your handling code here:
        JFileChooser dlg = new JFileChooser();
        int resp = dlg.showOpenDialog(this);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                VentanaInterna vi = new VentanaInterna();
                vi.getLienzo().setImage(img);
                this.escritorio.add(vi);
                vi.setTitle(f.getName());
                vi.setVisible(true);
            }catch(Exception ex){
                System.err.println("Error al leer la imagen");
            }
        }
    }//GEN-LAST:event_abrirItemActionPerformed

    private void guardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarItemActionPerformed
        // TODO add your handling code here:
        VentanaInterna vi=(VentanaInterna) escritorio.getSelectedFrame();
        if (vi != null) {
            JFileChooser dlg = new JFileChooser();
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    if (img != null) {
                        File f = dlg.getSelectedFile();
                        ImageIO.write(img, "jpg", f);
                        vi.setTitle(f.getName());
                    }
                }catch (Exception ex) {
                System.err.println("Error al guardar la imagen");
            }
        
        }
        }
    }//GEN-LAST:event_guardarItemActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirItem;
    private javax.swing.JCheckBox alisar;
    private javax.swing.JCheckBox editar;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JCheckBox fill;
    private javax.swing.JMenuItem guardarItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JMenuItem newItem;
    private javax.swing.JCheckBox transparencia;
    private practica7.VentanaInterna ventanaInterna2;
    // End of variables declaration//GEN-END:variables
}
