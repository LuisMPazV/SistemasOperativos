import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread{
	
	
	private Semaphore monitorSleeping;
	private Semaphore hall;
	private Semaphore monitorAvailable;
	private String name;
	private Random randGen;
	

	public Monitor(Semaphore monitorSleeping, Semaphore hall, Semaphore monitorAvailable, String name, Random randGen) {
		super();
		this.monitorSleeping = monitorSleeping;
		this.hall = hall;
		this.monitorAvailable = monitorAvailable;
		this.name = name;
		this.randGen = randGen;
	}











	@Override
	public void run() {
		
		while(true) {
			
		}
		
	}

}
