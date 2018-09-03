/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.image;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase UmbralizationOp que hereda de BufferedImageOpAdapter, la cual calcula
 * la media del valor RGB para cada pixel y a partir de un umbral le asigna el 
 * valor 255 (blanco) y si no pasa el umbral le aplica el valor 0 (negro), de 
 * forma que la iamgen resultante quede en blanco y negro
 * @author alberto
 */
public class UmbralizationOp extends BufferedImageOpAdapter {

    /**
     * Variable que indicará el umbral en la operación
     */
    private int _umbral;
    /**
     * Constructor con parámetros, para inicializar el umbral al valor deseado
     * @param umbral valor deseado para el umbral
     */
    public UmbralizationOp(int umbral) {
        this._umbral = umbral;
    }
    /**
     * Constructor por defecto, inicializa el umbral a 155
     */
    public UmbralizationOp() {
        this._umbral = 155;
    }
    /**
     * Método para aplicar el filtro a la imagen origen (src) y sobre la imagen
     * destino (dest) para así conservar la imagen origen intacta, de forma que
     * la imagen destino tendrá 2 colores, blanco o negro por cada pixel
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
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                pixelComp = src.getRaster().getPixel(x, y, pixelComp);
                if ((pixelComp[0] + pixelComp[1] + pixelComp[2]) / 3 >= _umbral) {
                    // dest.setRGB(x, y, 255);
                    pixelComp[0] = pixelComp[1] = pixelComp[2] = 255;
                } else {
                    // dest.setRGB(x, y, 0);
                    pixelComp[0] = pixelComp[1] = pixelComp[2] = 0;
                }
                dest.getRaster().setPixel(x, y, pixelComp);
            }
        }
        
        return dest;
    }
    
}
