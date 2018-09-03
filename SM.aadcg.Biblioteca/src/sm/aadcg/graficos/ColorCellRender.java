/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Clase para poder usar un ComboBox de colores.
 * @author alberto
 */
public class ColorCellRender extends JLabel implements ListCellRenderer<Color> {
    /**
     * Variable para indicar el tamaño de los objetos seleccionados
     */
    private final static Dimension dim = new Dimension(25, 25);
    
    /**
     * Constructor por defecto
     */
    public ColorCellRender() {
         setOpaque(true);
         this.setPreferredSize(dim);
     }


    /**
     * Método para obtener el color seleccionado y aplicar a la figura
     * @param list lista de colores que hay
     * @param value color seleccionado
     * @param index índice del color seleccionado
     * @param isSelected si está seleccionado o no 
     * @param cellHasFocus No sé qué hace
     * @return devuelve el color seleccionado
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {

        
         // check if this cell represents the current DnD drop location
         JList.DropLocation dropLocation = list.getDropLocation();
         if (dropLocation != null
                 && !dropLocation.isInsert()
                 && dropLocation.getIndex() == index) {
             
         // check if this cell is selected
         } else if (isSelected) {
            this.setBackground(value);
            JButton b = new JButton();
            b.setBackground(value);
            return b;
         // unselected, and not the DnD drop location
         } else {
            this.setBackground(value);
            JButton b = new JButton();
            b.setBackground(value);
            return b;
         };

         //setBackground(background);
         //setForeground(foreground);
        
        return this;
    }
    
}
