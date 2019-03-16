import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread{
	
	private Semaphore monitorSleeping;
	private Semaphore hall;
	private Semaphore monitorAvailable;
	private String name;
	private Random randGen;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
