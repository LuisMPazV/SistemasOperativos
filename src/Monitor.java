
// Proyecto Monitor Dormilon - Clase Monitor
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

//	private Semaphore monitorSleeping;
//	private Semaphore firstChair;
//	private Semaphore secondChair;
//	private Semaphore thirdChair;
//	private Semaphore monitorAvailable;

	private Semaphore monitor; // Usado por los estudiantes para marcarlo como ocupado
	private Semaphore chairs; // Usado para saber cuantas sillas hay disponibles
	private String name; // Nombre del Monitor
	private Random randGen; // Generador de numeros aleatorios

	/**
	 * @param monitorSleeping
	 * @param firstChair
	 * @param secondChair
	 * @param thirdChair
	 * @param monitorAvailable
	 * @param name
	 * @param randGen
	 */
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

	@Override
	public void run() {
		while (true) {

			try {
				if (monitor.availablePermits() == 0) { // Si el monitor esta disponible atiende a un estudiante
					System.out.println("--[" + name + "]Estoy atendiendo a un estudiante.");
					// Espera mientras atiende al estudiante
					sleep(Math.abs(randGen.nextInt()) % 1000);
					monitor.release(); // El monitor queda disponible
				} else if (chairs.availablePermits() == 3) {
					System.out.println("[" + name + "] Dormire en vista de que no hay estudiantes");
				}
//				if(firstChair.availablePermits()==1&&secondChair.availablePermits()==1&&thirdChair.availablePermits()==1) {
//					if(monitorSleeping.availablePermits()==1) {
//						monitorSleeping.acquire();
//						System.out.println("El monitor "+name+" se ha quedado dormido en vista de que no hay estudiantes");
//					}
//				}else {
//					if(monitorAvailable.availablePermits()==0) {
//						System.out.println("Debo seguir atendiendo estudiantes");
//						sleep(1000);
//						monitorAvailable.release();
//					}
//				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}