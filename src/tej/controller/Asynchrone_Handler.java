package tej.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.realtime.*;

public class Asynchrone_Handler extends AsyncEventHandler {

	public Asynchrone_Handler() {
		super(new PriorityParameters(PriorityScheduler.instance()
				.getMinPriority() + 10), null, null, null, null, false);
	}

	RealtimeThread th;

	public void setThread(RealtimeThread th) {
		this.th = th;
	}

	public void handleAsyncEvent() {
		InputStreamReader isr;
		BufferedReader br;
		try {
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			String line = br.readLine();
			System.out.println("input =" + line);
		} catch (IOException ioe) {
		}
		// Permet la reprise de la tache preemptee (th) par ce AEH

		// Cas missHandler ou OverrunHandler par exemple

		th.schedulePeriodic(); // Let the thread continue
	}
}
