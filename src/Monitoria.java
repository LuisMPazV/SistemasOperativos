
// Proyecto Monitor Dormilon - Clase Monitoria
// Sistemas Operativos - Luis Miguel Paz, Mateo Marin

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitoria {

	public static void main(String[] args) {

		long semilla = 2000000;

		// Instanciar los semaforos requeridos
		Semaphore s_monitor;
		Semaphore chairs;

		// Crea el arreglo vacia de estudiantes
		ArrayList<Student> students = new ArrayList<>();

		// Instanciar el monitor
		Monitor monitor;

		// Semaforo binario (Representa el monitor)
		s_monitor = new Semaphore(1, true);

		// Semaforo contador (Representa las sillas)
		chairs = new Semaphore(3, true);

		// Anade 10 estudiantes al arreglo de estudiantes
		for (int i = 0; i < 10; i++) {

			// Inicializa un estudiante
			students.add(new Student(s_monitor, chairs, "ESTUDIANTE " + i, semilla));
		}

		// Inicializa al monitor
		monitor = new Monitor(s_monitor, chairs, "MONITOR", semilla);

		// Inicia los once hilos
		monitor.start();
		for (int i = 0; i < 10; i++) {
			students.get(i).start();
		}

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

//		for (int i = 0; i < 10; i++) {
//		estudiantes.add(new Student(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, String.valueOf(i), semilla));
//	}
//	monitor=new Monitor(monitorSleeping, firstChair, secondChair, thirdChair, monitorAvailable, "Mateo", semilla);
	}

}
