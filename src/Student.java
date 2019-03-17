
// Proyecto Monitor Dormilon - Clase Student
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

//	private Semaphore monitorSleeping;
//	private Semaphore firstChair;
//	private Semaphore secondChair;
//	private Semaphore thirdChair;
//	private Semaphore monitorAvailable;

	private Semaphore monitor; // Semaforo del monitor(lo despierta)
	private Semaphore chairs; // Usado para si hay sillas disponibles
	private String name; // Nombre del estudiante
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
//	public Student(Semaphore monitorSleeping, Semaphore firstChair, Semaphore secondChair, Semaphore thirdChair,
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
	public Student(Semaphore monitor, Semaphore chairs, String name, long randGen) {
		super();
		this.monitor = monitor;
		this.chairs = chairs;
		this.name = name;
		this.randGen = new Random(randGen);
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			try {

				if (monitor.availablePermits() == 0) { // Si el monitor esta ocupado entra
					if (chairs.availablePermits() > 0) { // Si hay sillas disponibles entra
						// El estudiante se sienta
						chairs.acquire();
						System.out.println("-[" + name + "] Estoy sentado esperando mi turno para la monitoria.");
						// Espera hasta que el monitor este disponible
						monitor.acquire();
						// Desocupa la silla
						chairs.release();
						System.out.println("--[" + name + "] El monitor me esta atendiendo.");
						// Espera mientras lo atienden y mientras le surge otra duda
						sleep(Math.abs(randGen.nextInt()) % 100000);
					} else {
						System.out
								.println("-[" + name + "] El monitor esta ocupado, ire a la sala de computo un rato.");
						// Espera del estudiante en la sala de computo
						sleep(Math.abs(randGen.nextInt()) % 10000);
					}
				} else {
					// Ocupa al monitor
					monitor.acquire();
					System.out.println(
							"--[" + name + "] Desperte al monitor y entre al salon para iniciar mi monitoria.");
					// Espera mientras lo atienden y mientras le surge otra duda
					sleep(Math.abs(randGen.nextInt()) % 100000);
				}

//				if (thirdChair.tryAcquire()) {
//					System.out.println(
//							"El estudiante " + name + " ha consegido sentarse en la tercera silla del corredor.");
//					secondChair.acquire();
//					System.out.println(
//							"El estudiante " + name + " ha consegido sentarse en la segunda silla del corredor.");
//					thirdChair.release();
//					firstChair.acquire();
//					System.out.println(
//							"El estudiante " + name + " ha consegido sentarse en la primera silla del corredor.");
//					secondChair.release();
//					if (monitorSleeping.availablePermits() == 0) {
//						System.out.println(
//								"El estudiante " + name + " ha despertado al monitor que se encontraba dormido");
//					}
//					monitorAvailable.acquire();
//					System.out.println("El estudiante " + name + " esta siendo atendido por el monitor");
//					firstChair.release();
//					while (monitorAvailable.availablePermits() == 0) {
//
//					}
//					System.out.println("El estudiante " + name + " ha sido atendido y ahora tiene menos dudas.");
//
//				} else {
//					System.out.println("El corredor esta lleno, el estudiante " + name
//							+ " ira a la sala de computo y regresara en un rato");
//					sleep(10000);
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
