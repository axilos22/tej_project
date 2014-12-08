package tej.controller;

/** Just start a periodic thread with a one-second period.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.realtime.*;

public class Application {
	static private String str = "";
	
	public static void main(String[] args) {
		SchedulingParameters scheduling = new PriorityParameters(
				PriorityScheduler.getMinPriority(null) + 10);

		ReleaseParameters releaseParam = new PeriodicParameters(
				new RelativeTime(), // Start at .start()
				new RelativeTime(1000, 0), // 1 second period
				null, // cost
				new RelativeTime(5, 0),// deadline=period/2
				null, // no overrun handler
				null); // no miss handler

		RealtimeThread rtPeriodic = new Tache_Compteur(scheduling, releaseParam);
		AsyncEvent inputKeyboard = new AsyncEvent();
		inputKeyboard.addHandler(new Asynchrone_Handler());

		PriorityScheduler boss = PriorityScheduler.instance();
		Scheduler.setDefaultScheduler(boss);

		rtPeriodic.setScheduler(boss);

		if (boss.isFeasible()) {
			System.out
					.println("<Application>/ Jeu de taches ordonnancables ...");

			// Démarrage du thread
			rtPeriodic.start();

		}

		try {
			for (int i = 0; i < 10; i++) {
				InputStreamReader isr;
				BufferedReader br;
				try {
					isr = new InputStreamReader(System.in);
					br = new BufferedReader(isr);
					String line = br.readLine();
					str = line;
					inputKeyboard.fire();
				} catch (IOException ioe) {}
			}
			rtPeriodic.join();
		} catch (Exception e) {
		}
		;
	}

	public void keyboardPushed(AsyncEvent ae) {
		ae.fire();
	}
}
