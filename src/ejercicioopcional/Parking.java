/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioopcional;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mirroriced
 */
public class Parking {
    
    static int plazas[];
    Coche[] co;
            static int cont=0;
    public Parking(int nPlazas, int nCoches) {
        this.plazas = new int[nPlazas];
        co = new Coche[nCoches];
        inicializarArray();
        for (int i = 0; i < nCoches; i++) {
            co[i] = new Coche(i+1);
        }
    }
    
    public void inicializarHilos(){
           for (int i = 0; i < co.length; i++) {
            co[i].start();
        }
                      for (int i = 0; i < co.length; i++) {
               try {
                   co[i].join();
               } catch (InterruptedException ex) {
                   Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }
    
        public void inicializarArray() {

        for (int i = 0; i < plazas.length; i++) {

            plazas[i] = 0;

        } 
        }
        
        public  static synchronized void mostrarArray() {
        cont= 0;
        for (int i = 0; i < plazas.length; i++) {
            if(plazas[i]==0){
                cont++;
            }
            System.out.print("[" + plazas[i] + "]");
        }
            System.out.print("\n");
                    System.out.println("Plazas disponibles: "+cont+"\n");

    }

}
