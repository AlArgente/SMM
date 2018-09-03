/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.min;
import sm.image.BufferedImageOpAdapter;


/**
 * Clase SepiaOp que hereda de BufferedImageOpAdapter, la cual aplica el famoso
 * filtro sepia sobre la imagen proporcionada, haciendo que la imagen parezca
 * una imagen antigua.
 * @author alberto
 */
public class SepiaOp extends BufferedImageOpAdapter {
    /**
     * Constructor por defecto
     */
    public SepiaOp () {
    }
    /**
     * Método para aplicar el filtro a la imagen origen (src) y sobre la imagen
     * destino (dest) para así conservar la imagen origen intacta, de forma que
     * la imagen dest parezca antigua respecto a la verdadera
     * @param src Imagen origen
     * @param dest Imagen destino
     * @return dest, la imagen sobre la que se aplica la operación
     */
    @Override
    @SuppressWarnings("empty-statement")
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
        
        int sepiaR = 0; //  = min(255 , 0.393·R + 0.769·G + 0.189·B)
        int sepiaG = 0; //  = min(255, 0.349·R + 0.686·G + 0.168·B)
        int sepiaB = 0; // = min(255, 0.272·R + 0.534·G + 0.131·B)
        // int[] rgb = null;
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
                // Aplicamo sla operación sepia
                sepiaR = (int) min(255 , 0.393*red + 0.769*green + 0.189*blue);
                sepiaG = (int) min(255, 0.349*red + 0.686*green + 0.168*blue);
                sepiaB = (int) min(255, 0.272*red + 0.534*green+ 0.131*blue);
                pixelComp[0] = sepiaR;
                pixelComp[1] = sepiaG;
                pixelComp[2] = sepiaB;
                dest.getRaster().setPixel(x, y, pixelComp);
            }
        }
        
        return dest;
    }
    
}
