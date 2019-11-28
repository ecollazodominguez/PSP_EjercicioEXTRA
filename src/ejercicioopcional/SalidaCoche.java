/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioopcional;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class SalidaCoche extends Thread {

        private int numCoche;
        private Parking park;
    
    public SalidaCoche(int numCoche, Parking park) {
        this.numCoche = numCoche;
        this.park = park;
    }

    public int getNumCoche() {
        return numCoche;
    }
    
    
    
        public void run() {

        for (int i = 0; i < 3; i++) {

                try {

                    try {
                        sleep((long) (Math.random() * (3000 - 7000) + 7000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SalidaCoche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    park.salida(this);
                    
                    sleep((long) (Math.random() * (3000 - 10000) + 10000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(SalidaCoche.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
    
}
