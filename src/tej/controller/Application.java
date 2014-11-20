package tej.controller;
/** Just start a periodic thread with a one-second period.
 */

import javax.realtime.*; 


public class Application
{
  public static void main(String [] args)
  {
    SchedulingParameters scheduling =
        new PriorityParameters(PriorityScheduler.getMinPriority(null)+10); 
    
    ReleaseParameters releaseParam = new PeriodicParameters(
        new RelativeTime(), // Start at .start()
        new RelativeTime(1000, 0), // 1 second period
        null, // cost
        new RelativeTime(5,0),// deadline=period/2
        null, // no overrun handler
        null); // no miss handler
        
    RealtimeThread rtPeriodic= new Tache_Compteur(scheduling, releaseParam); 
             
    PriorityScheduler boss = PriorityScheduler.instance();
	Scheduler.setDefaultScheduler(boss);

	rtPeriodic.setScheduler(boss);

	if (boss.isFeasible()) {
		
		System.out.println("<Application>/ Jeu de taches ordonnancables ...");
		
		// Démarrage du thread
		rtPeriodic.start();

	}
    
    try{
    	rtPeriodic.join();
    	
 
    }catch(Exception e){}; 
  }
}
