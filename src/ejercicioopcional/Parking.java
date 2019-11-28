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
    EntradaCoche[] en;
    SalidaCoche[] sa;
    static boolean entrada = true;
    
            static int cont=0;
            
    public Parking (){
        
    }
    public Parking(int nPlazas, int nCoches) {
        this.plazas = new int[nPlazas];
        en = new EntradaCoche[nCoches];
        sa = new SalidaCoche[nCoches];
        inicializarArray();
        for (int i = 0; i < nCoches; i++) {
            en[i] = new EntradaCoche(i+1,this);
            sa[i] = new SalidaCoche(i+1,this);
        }
    }
    
    public void inicializarHilos(){
           for (int i = 0; i < en.length; i++) {
            en[i].start();
            sa[i].start();
        }
                      for (int i = 0; i < en.length; i++) {
               try {
                   en[i].join();
                   sa[i].join();
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
        
        public synchronized void entrada(EntradaCoche enc) {

         int aparcado=-1;   
            
        while (!entrada) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < Parking.plazas.length; i++) {

            if (Parking.plazas[i] == 0 && aparcado != enc.getNumCoche()) {
                aparcado = enc.getNumCoche();
                Parking.plazas[i] = enc.getNumCoche();
                System.out.println("ENTRADA: Coche " + enc.getNumCoche() + " aparca en " + (i + 1));
                Parking.mostrarArray();
                
            }else if (Parking.plazas[i] == 0){
                entrada = true;
            }else{
                entrada = false;
            }
        }

        this.notifyAll();
    }

    public synchronized void salida(SalidaCoche sac) {

        while (entrada) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = 0; i < Parking.plazas.length; i++) {

            if (Parking.plazas[i] == sac.getNumCoche()) {
                System.out.println("SALIDA: Coche " + sac.getNumCoche() + " saliendo");
                Parking.plazas[i] = 0;
                Parking.mostrarArray();
                entrada = true;
                this.notifyAll();
            }

        }
    }

}
