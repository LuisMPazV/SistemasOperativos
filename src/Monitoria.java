import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitoria {

	public static void main(String[] args) {
		
		long Semilla=2000000;
		
		Semaphore monitorDormido;
		Semaphore corredor;
		Semaphore monitorDisponible;
		
		corredor=new Semaphore(3, true);
		monitorDisponible=new Semaphore(1, true);
		monitorDormido=new Semaphore(1, true);
		
		ArrayList<Student> estudiantes=new ArrayList<>();
		
		Monitor monitor;
		

	}

}
