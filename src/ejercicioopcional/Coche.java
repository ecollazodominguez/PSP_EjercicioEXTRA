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
public class Coche extends Thread {

    public int numCoche;
    static boolean entrada = true;

    public Coche(int numCoche) {
        this.numCoche = numCoche;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                try {
                    System.out.println("Hilo " + numCoche + "Repeticion: " + i);
                    sleep((long) (Math.random() * (3000 - 10000) + 10000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }

                entrada();

                sleep((long) (Math.random() * (3000 - 10000) + 10000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
            }

            salida();
        }
    }

    public synchronized void entrada() {

        while (!entrada) {
            try {
                System.out.println("hola");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        entrada = false;
        System.out.println("pase por aqui");
        for (int i = 0; i < Parking.plazas.length; i++) {

            if (Parking.plazas[i] == 0) {
                Parking.plazas[i] = numCoche;
                System.out.println("ENTRADA: Coche " + numCoche + " aparca en " + (i + 1));
                Parking.mostrarArray();
                i = Parking.plazas.length;
            }
        }

        this.notifyAll();
    }

    public synchronized void salida() {

        for (int i = 0; i < Parking.plazas.length; i++) {

            if (Parking.plazas[i] == numCoche) {
                System.out.println("Saliendo: Coche " + numCoche + " saliendo");
                Parking.plazas[i] = 0;
                Parking.mostrarArray();
                entrada = true;
                this.notifyAll();
            }

        }
    }
}
