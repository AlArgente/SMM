/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.awt.Color;
import java.awt.Point;
import sm.aadcg.iu.Lienzo2DImagen;

/**
 *
 * @author alberto
 */
public class VentanaInterna extends javax.swing.JInternalFrame {

    private VentanaPrincipal parent;
    private int _imgx = 300;
    private int _imgy = 300;
    
    public VentanaInterna(VentanaPrincipal parent) {
        this.parent = parent;
        initComponents();
        this.setResizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        
    }
    
    
    /**
     * Creates new form VentanaInterna
     */
    /*public VentanaInterna() {
        initComponents();
        this.setResizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lienzo2DImagen2 = new sm.aadcg.iu.Lienzo2DImagen();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));

        lienzo2DImagen2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        lienzo2DImagen2.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(lienzo2DImagen2);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Lienzo2DImagen getLienzo() {
        return this.lienzo2DImagen2;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private sm.aadcg.iu.Lienzo2DImagen lienzo2DImagen2;
    // End of variables declaration//GEN-END:variables
}
