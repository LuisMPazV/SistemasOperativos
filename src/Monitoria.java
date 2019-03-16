import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitoria {

	public static void main(String[] args) {
		
		long semilla=2000000;
		
		Semaphore monitorSleeping;
		Semaphore firstChair;
		Semaphore secondChair;
		Semaphore thirdChair;
		Semaphore monitorAvailable;
		
		monitorSleeping=new Semaphore(1, true);
		firstChair=new Semaphore(1, true);
		secondChair=new Semaphore(1, true);
		thirdChair=new Semaphore(1, true);
		monitorAvailable=new Semaphore(1, true);
		
		ArrayList<Student> estudiantes=new ArrayList<>();
		
		Monitor monitor;
		
		for (int i = 0; i < 10; i++) {
			estudiantes.add(new Student(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, String.valueOf(i), semilla));
		}
		monitor=new Monitor(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, "Mateo", semilla);
		monitor.start();
		
		for (int i = 0; i < 10; i++) {
			estudiantes.get(i).start();
		}

	}

}
