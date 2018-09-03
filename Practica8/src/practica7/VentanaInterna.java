/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import sm.aadcg.iu.Lienzo2D;
import sm.aadcg.iu.Lienzo2DImagen;

/**
 *
 * @author alberto
 */
public class VentanaInterna extends javax.swing.JInternalFrame {

    /**
     * Creates new form VentanaInterna
     */
    public VentanaInterna() {
        initComponents();
        this.setResizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lienzo2DImagen1 = new sm.aadcg.iu.Lienzo2DImagen();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(400, 300));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout lienzo2DImagen1Layout = new javax.swing.GroupLayout(lienzo2DImagen1);
        lienzo2DImagen1.setLayout(lienzo2DImagen1Layout);
        lienzo2DImagen1Layout.setHorizontalGroup(
            lienzo2DImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        lienzo2DImagen1Layout.setVerticalGroup(
            lienzo2DImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(lienzo2DImagen1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
        switch(this.getLienzo().get_modo_dibujo()) {
                case "punto":
                    this.getLienzo().createShape("punto", evt.getPoint());
                    this.getLienzo().set_paux(evt.getPoint());
                    this.repaint();
                    break;
                case "linea":
                    this.getLienzo().createShape("linea", evt.getPoint());
                    break;
                case "rectangulo": 
                    this.getLienzo().createShape("rectangulo", evt.getPoint());                    
                    this.getLienzo().set_paux(evt.getPoint());
                    break;
                case "ellipse":
                    this.getLienzo().createShape("ellipse", evt.getPoint());
                    this.getLienzo().set_paux(evt.getPoint());
                    break;
                default:
                   // thisgetLienzo().createShape("punto");
                    break;
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:.
        this.formMouseDragged(evt);
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        if (this.getLienzo().get_editar()) {
            this.getLienzo().set_paux(evt.getPoint());
            this.getLienzo().updateShape("recangulo", evt.getPoint());
        } else {
            switch(this.getLienzo().get_modo_dibujo()) {
                    case "punto":
                        this.getLienzo().updateShape("punto", evt.getPoint());
                        // l.setLine(this.l.getP1(), evt.getPoint());
                        break;
                    case "linea":
                        this.getLienzo().updateShape("linea", evt.getPoint());
                        break;
                    case "rectangulo": 
                        this.getLienzo().updateShape("rectangulo", evt.getPoint());
                        break;
                    case "ellipse":
                        this.getLienzo().updateShape("ellipse", evt.getPoint());
                        break;
                    default:
                        this.getLienzo().updateShape("punto", evt.getPoint());
                        break;
            }
        }
        
        this.getLienzo().repaint();
    }//GEN-LAST:event_formMouseDragged

    public Lienzo2DImagen getLienzo() {
        return this.lienzo2DImagen1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private sm.aadcg.iu.Lienzo2DImagen lienzo2DImagen1;
    // End of variables declaration//GEN-END:variables
}