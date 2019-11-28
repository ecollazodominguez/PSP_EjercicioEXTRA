//Escribe una clase llamada Parking que reciba el número de plazas del parking
//y el número de coches existentes en el sistema. Se deben de crear tantos hilos
//como coches haya. El parking dispondrá de una única entrada y una única salida.
//En la entrada de vehículos habrá un dispositivo de control que permita o impida
//el acceso al parking, dependiendo del estado actual del mismo (plazas de
//aparcamiento disponibles). Los tiempos de espera de los vehículos dentro del
//parking son aleatorios. En el momento que un vehículo sale del parking,
//notifica al dispositivo de control el número de plaza que tenía asignada 
//y se libera la plaza que estuviera ocupando, quedando así nuevamente 
//disponible. Un vehículo que ha salido del parking esperará un tiempo 
//aleatorio para volver a entrar nuevamente al mismo. Por tanto los vehículos
//estarán entrando y saliendo indefinidamente del parking.
package ejercicioopcional;

/**
 *
 * @author Mirroriced
 */
public class EjercicioOpcional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parking park = new Parking(4,8);
        
        park.inicializarHilos();
        System.out.println("Termina hilo main");
    }
    
}
