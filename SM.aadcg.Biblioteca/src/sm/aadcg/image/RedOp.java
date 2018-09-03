/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.image;

import java.awt.image.BufferedImage;
import static java.lang.Math.min;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase RedOp que hereda de BufferedImageOpAdapter, y que se ha creado para 
 * crear una operación que se aplique pixel a pixel. Esta operación obtiene el 
 * el valor RGB de cada pixel y tras esto si supera un umbral, deja sólo el valor
 * del rojo a 255 y el resto a 0, de forma que el resto quedarán con tonalidad
 * de grises
 * @author alberto
 */
public class RedOp extends BufferedImageOpAdapter {
    /** 
     * Constructor por defecto
     */
    public RedOp() {
        
    }
    /**
     * Método para aplicar el filtro a la imagen origen (src) y sobre la imagen
     * destino (dest) para así conservar la imagen origen intacta, de forma que
     * la imagen dest será la que tendrá una tonalidad rojiza
     * @param src Imagen origen
     * @param dest Imagen destino
     * @return dest, la imagen sobre la que se aplica la operación
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src==null) {
            throw new NullPointerException("src is null");
        }
        if (dest==null) {
            dest = createCompatibleDestImage(src, null);
        }
        if (src==dest) {
            dest = createCompatibleDestImage(src, null);
        }
        
        int[] pixelComp = null;
        int clr, red, green, blue = 0;
        
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                pixelComp = src.getRaster().getPixel(x, y, pixelComp);
                // Obtenemos el RGB del pixel x y de la imagen
                clr   = src.getRGB(x, y); 
                // Obtenemos el rojo
                red   = (clr & 0x00ff0000) >> 16;
                // Obtenemos el verde
                green = (clr & 0x0000ff00) >> 8;
                // Obtenemos el azul
                blue  =  clr & 0x000000ff;
                // Aplicamos la operación para quedarnos con los píxeles más rojizos
                // Y dejar el resto de la imagen en tonalidad de grises
                if (red > 100 && green < 75 && blue < 75) {
                    red = 255;
                    green = 0;
                    blue = 0;
                    pixelComp[0] = red;
                    pixelComp[1] = 0;
                    pixelComp[2] = 0;
                } else {
                    pixelComp[0] = red + green + blue;
                    pixelComp[1] = red + green + blue;
                    pixelComp[2] = red + green + blue;
                }

                dest.getRaster().setPixel(x, y, pixelComp);
            }
        }
        
        
        return dest;
    }
    
}
