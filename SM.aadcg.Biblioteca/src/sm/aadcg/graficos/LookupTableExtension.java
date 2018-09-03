/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.aadcg.graficos;

import java.awt.image.ByteLookupTable;
import java.awt.image.LookupTable;

/**
 *
 * @author alberto
 */
public class LookupTableExtension extends LookupTable {

    LookupTableExtension(int i, int j) {
       super(i,j);
    }

    @Override
    public int[] lookupPixel(int[] src, int[] dest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static LookupTable seno(double w) {
        // f(x) = |sin(w * x)| -> w = velocidad angular
        double K = 255.0;
        w = w/K;
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int l = 1; l < 256; ++l) {
            lt[l] = (byte)(K * (Math.abs(Math.sin(Math.toRadians(w*l)))));
        }
        ByteLookupTable slt = new ByteLookupTable(0,lt);
        return slt;
    }
    public static LookupTable own(double k) {
        // sen(x) = sqrt(1 - cos^2(x)) 
        // f(k) = sqrt(1 - cos^2(k-l)) 
        double Max = 1;
        // double CTE = 255.0 / Max;
        double CTE = 255.0;
        byte lt[] = new byte[256];
        lt[0] = 0;
        for (int l = 1; l < 256; ++l) {
            lt[l] = (byte)(CTE * (Math.abs(Math.sqrt(1.0 - Math.pow(Math.cos(Math.toRadians(k-l)), 2)))));
        }
        ByteLookupTable slt = new ByteLookupTable(0,lt);
        return slt;
    }
    
    public static LookupTable negativo() {
        byte lt[] = new byte[256];
        for(int i = 0; i < 256; ++i) {
            lt[i] = (byte)(255-i);
        }
        ByteLookupTable slt = new ByteLookupTable(0,lt);
        return slt;
    }
   
    
}
