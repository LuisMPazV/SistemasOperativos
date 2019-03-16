import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {

	private Semaphore monitorSleeping;
	private Semaphore hall;
	private Semaphore monitorAvailable;
	private String name;
	private Random randGen;

	public Monitor(Semaphore monitorSleeping, Semaphore hall, Semaphore monitorAvailable, String name, long randGen) {
		super();
		this.monitorSleeping = monitorSleeping;
		this.hall = hall;
		this.monitorAvailable = monitorAvailable;
		this.name = name;
		this.randGen = new Random(randGen);
	}

	@Override
	public void run() {

		while (true) {

			try {
				
				if(hall.availablePermits()==3) {
					if(monitorSleeping.availablePermits()==1) {
						monitorSleeping.acquire();
					}
				}else {
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}

	}

}
