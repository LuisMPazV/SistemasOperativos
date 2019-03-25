
// Proyecto Monitor Dormilon - Clase Student
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	/**
	 * Semaforo del monitor (lo despierta)
	 */
	private Semaphore monitor;

	/**
	 * Usado para si hay sillas disponibles
	 */
	private Semaphore chairs;

	/**
	 * Nombre del estudiante
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
	public Student(Semaphore monitor, Semaphore chairs, String name, long randGen) {
		super();
		this.monitor = monitor;
		this.chairs = chairs;
		this.name = name;
		this.randGen = new Random(randGen);
	}

	/**
	 * Metodo run del hilo estuadiante, que permite al estudiante entrar a la sala
	 * del monitor. Si el monitor esta dormido lo despierta y sino, revisa si hay
	 * sillas disponibles si no hay sillas disponibles se va para la sala de computo
	 * a esperar, y si hay sillas disponibles se sienta y espera hasta que sea su
	 * turno de entrar.
	 * 
	 * Cuando un estudiante es atendido el hilo se duerme durante hasta 1 minuto
	 * representando el tiempo que dura la monitoria y el tiempo que no este
	 * intentand ir a una monitoria.
	 * 
	 * Cuando un estudiante va a la sala de espera, el hilo se duerme hasta 5
	 * segundos representando el tiempo que el estudiante utilizaria para ir esperar
	 * y volver a la monitoria.
	 * 
	 */
	@Override
	public void run() {
		super.run();
		while (true) {
			try {

				// Si el monitor esta atendiendo a otro estudiante, se entra al cuarto donde
				// estan las sillas(el if)
				if (monitor.availablePermits() == 0) {

					// Si hay sillas disponibles se entra al cuarto donde estan las sillas(el if)
					if (chairs.availablePermits() > 0) {

						// El estudiante se sienta
						chairs.acquire();
						System.out.println("--[" + name + "] Estoy sentado esperando mi turno para la monitoria.");

						// El estudiante espera hasta que el monitor este disponible
						monitor.acquire();

						// El estudiante desocupa la silla en la que estaba sentado
						chairs.release();
						System.out.println("-[" + name + "] El monitor me esta atendiendo.");

						// El estudiante espera mientras lo atienden y se va
						sleep(Math.abs(randGen.nextInt()) % 40000);

					} else {

						// El estudiante espera en la sala de computo
						System.out
								.println("---[" + name + "] El monitor esta ocupado, ire a la sala de computo un rato.");
						sleep(Math.abs(randGen.nextInt()) % 1000);
					}
				} else {

					// El estudiante entra a la monitoria si no hay otro estudiante
					if (monitor.tryAcquire()) {

						// El estudiante espera mientras lo atienden y se va
						System.out.println(
								"-[" + name + "] Desperte al monitor y entre al salon para iniciar mi monitoria.");
						sleep(Math.abs(randGen.nextInt()) % 40000);
					}

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

//	@Override
//	public void run() {
//		super.run();
//		while (true) {
//			try {
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
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
