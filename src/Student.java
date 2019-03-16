import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	private Semaphore monitorSleeping;
	private Semaphore firstChair;
	private Semaphore secondChair;
	private Semaphore thirdChair;
	private Semaphore monitorAvailable;
	private String name;
	private Random randGen;

	
	
	
	/**
	 * @param monitorSleeping
	 * @param firstChair
	 * @param secondChair
	 * @param thirdChair
	 * @param monitorAvailable
	 * @param name
	 * @param randGen
	 */
	public Student(Semaphore monitorSleeping, Semaphore firstChair, Semaphore secondChair, Semaphore thirdChair,
			Semaphore monitorAvailable, String name, long randGen) {
		super();
		this.monitorSleeping = monitorSleeping;
		this.firstChair = firstChair;
		this.secondChair = secondChair;
		this.thirdChair = thirdChair;
		this.monitorAvailable = monitorAvailable;
		this.name = name;
		this.randGen = new Random(randGen);
	}




	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				if(thirdChair.tryAcquire()) {
					System.out.println("El estudiante "+name+" ha consegido sentarse en la tercera silla del corredor.");
					secondChair.acquire();
					System.out.println("El estudiante "+name+" ha consegido sentarse en la segunda silla del corredor.");
					thirdChair.release();
					firstChair.acquire();
					System.out.println("El estudiante "+name+" ha consegido sentarse en la primera silla del corredor.");
					secondChair.release();
					if(monitorSleeping.availablePermits()==0) {
						System.out.println("El estudiante "+name+" ha despertado al monitor que se encontraba dormido");
					}
					monitorAvailable.acquire();
					System.out.println("El estudiante "+name+" esta siendo atendido por el monitor");
					firstChair.release();
					sleep(1000);
					monitorAvailable.release();
				}else {
					System.out.println("El corredor esta lleno, el estudiante "+name+" ira a la sala de computo y regresara en un rato");
					sleep(10000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
