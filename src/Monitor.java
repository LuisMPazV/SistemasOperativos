
// Proyecto Monitor Dormilon - Clase Monitor
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

	/**
	 * Usado por los estudiantes para ver si el monitor esta dormido
	 */
	private Semaphore monitor;

	/**
	 * Usado para saber cuantas sillas hay disponibles
	 */
	private Semaphore chairs;

	/**
	 * Nombre del Monitor
	 */
	private String name;

	/**
	 * Generador de numeros aleatorios
	 */
	private Random randGen;

	/**
	 * Constructor de la clase - Inicializa todos los datos requeridos
	 * 
	 * @param monitor
	 * @param chairs
	 * @param name
	 * @param randGen
	 */
	public Monitor(Semaphore monitor, Semaphore chairs, String name, long randGen) {
		super();
		this.monitor = monitor;
		this.chairs = chairs;
		this.name = name;
		this.randGen = new Random(randGen);
	}

	/**
	 * Metodo run del hilo monitor, que es utilizado para que un monitor pueda
	 * atender a un estudiante si el monitor esta despierto y hay estudiantes
	 * esperando para ser atendidos, los atiende. De lo contrario si no esta
	 * atiendiendo a nadie y no hay nadie sentado el monitor se queda dormido.
	 * 
	 * Cuando el monitor esta atendiendo a un estudiante el hilo se queda dormido
	 * hasta 1 segundo representando la duracion de la monitoria.
	 * 
	 */
	@Override
	public void run() {
		while (true) {
			try {

				// Si el monitor esta disponible atiende a un estudiante
				if (monitor.availablePermits() == 0) {

					// La espera del monitor mientras atiende al estudiante
					System.out.println("-[" + name + "]Estoy atendiendo a un estudiante.");
					sleep(Math.abs(randGen.nextInt()) % 1000);

					// El monitor queda disponible
					monitor.release();

				} else if (chairs.availablePermits() == 3) {

					// Si el monitor esta disponible y no hay nadie sentado esperando el monitor se
					// duerme
					System.out.print("El monitor esta dormido en vista de que no hay estudiantes \r");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	private Semaphore monitorSleeping;
//	private Semaphore firstChair;
//	private Semaphore secondChair;
//	private Semaphore thirdChair;
//	private Semaphore monitorAvailable;

//	/**
//	 * @param monitorSleeping
//	 * @param firstChair
//	 * @param secondChair
//	 * @param thirdChair
//	 * @param monitorAvailable
//	 * @param name
//	 * @param randGen
//	 */
//	public Monitor(Semaphore monitorSleeping, Semaphore firstChair, Semaphore secondChair, Semaphore thirdChair,
//			Semaphore monitorAvailable, String name, long randGen) {
//		super();
//		this.monitorSleeping = monitorSleeping;
//		this.firstChair = firstChair;
//		this.secondChair = secondChair;
//		this.thirdChair = thirdChair;
//		this.monitorAvailable = monitorAvailable;
//		this.name = name;
//		this.randGen = new Random(randGen);
//	}

//	@Override
//	public void run() {
//		while (true) {
//
//			try {
//				if (firstChair.availablePermits() == 1 && secondChair.availablePermits() == 1
//						&& thirdChair.availablePermits() == 1) {
//					if (monitorSleeping.availablePermits() == 1) {
//						monitorSleeping.acquire();
//						System.out.println(
//								"El monitor " + name + " se ha quedado dormido en vista de que no hay estudiantes");
//					}
//				} else {
//					if (monitorAvailable.availablePermits() == 0) {
//						System.out.println("Debo seguir atendiendo estudiantes");
//						sleep(1000);
//						monitorAvailable.release();
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
