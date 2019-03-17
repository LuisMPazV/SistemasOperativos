
// Proyecto Monitor Dormilon - Clase Monitoria
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitoria {

	public static void main(String[] args) {

		long semilla = 2000000;

//		Semaphore monitorSleeping;
//		Semaphore firstChair;
//		Semaphore secondChair;
//		Semaphore thirdChair;
//		Semaphore monitorAvailable;
//		
//		monitorSleeping=new Semaphore(1, true);
//		firstChair=new Semaphore(1, true);
//		secondChair=new Semaphore(1, true);
//		thirdChair=new Semaphore(1, true);
//		monitorAvailable=new Semaphore(1, true);

		// Instanciar los sem�foros requeridos
		Semaphore s_monitor;
		Semaphore chairs;

		// Instanciar los estudiantes
		ArrayList<Student> estudiantes = new ArrayList<>();

		// Instanciar el monitor
		Monitor monitor;

		s_monitor = new Semaphore(1, true); // Semaforo binario
		chairs = new Semaphore(3, true); // Semaforo contador

		for (int i = 0; i < 10; i++) {
			estudiantes.add(new Student(s_monitor, chairs, "ESTUDIANTE " + i, semilla));
		}
		monitor = new Monitor(s_monitor, chairs, "MONITOR", semilla);

//		for (int i = 0; i < 10; i++) {
//			estudiantes.add(new Student(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, String.valueOf(i), semilla));
//		}
//		monitor=new Monitor(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, "Mateo", semilla);

		// Iniciar los once hilos
		monitor.start();
		for (int i = 0; i < 10; i++) {
			estudiantes.get(i).start();
		}

	}

}
