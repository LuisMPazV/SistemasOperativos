import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

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
	public Monitor(Semaphore monitorSleeping, Semaphore firstChair, Semaphore secondChair, Semaphore thirdChair,
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

		while (true) {

			try {
				
				if(firstChair.availablePermits()==1&&secondChair.availablePermits()==1&&thirdChair.availablePermits()==1) {
					if(monitorSleeping.availablePermits()==1) {
						monitorSleeping.acquire();
						System.out.println("El monitor "+name+" se ha quedado dormido en vista de que no hay estudiantes");
					}
				}else {
					if(monitorAvailable.availablePermits()==0) {
						System.out.println("Debo seguir atendiendo estudiantes");
						sleep(1000);
						monitorAvailable.release();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}

	}

}
