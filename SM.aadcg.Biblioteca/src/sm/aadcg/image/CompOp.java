/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase CompOp que hereda de BufferedImageOpAdapter, y que se ha creado para
 * crear una operación que se aplique componente a componente. Esta operación
 * lo que hará será dar una tonalidad morada a la imagen, destacando las bandas
 * del azul y el rojo y bajando un poco la intensidad del azul
 * @author alberto
 */
public class CompOp extends BufferedImageOpAdapter {
    /**
     * Constructor por defecto
     */
    public CompOp() {
    }

    /**
     * Método para aplicar el filtro a la imagen origen (src) y sobre la imagen
     * destino (dest) para así conservar la imagen origen intacta, de forma que
     * la imagen dest será la que tendrá una tonalidad morada
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
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int sample = 0;
        Random r = new Random();
        
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                for (int band = 0; band < srcRaster.getNumBands(); ++band) {
                    // Obtenemos el componente
                    sample = srcRaster.getSample(x, y, band);
                    if (band == 0) {
                        sample = (int) sample - 20;
                    } else if (band == 1) {
                        sample = (int) sample + 10;
                    } else if (band == 2) {
                        sample = (int) sample - 20;
                    }
                    // sample = (int) Math.min(r.nextInt(Math.abs(sample)), 175);
                    // Aplicamos la operación a la banda
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        
        return dest;
    }
    
}
