package tej.controller;

import javax.realtime.*; 

public class Tache_Compteur extends RealtimeThread
{ 
	static final RelativeTime DELAY_INC = new RelativeTime(50,0);
 RelativeTime delay= new RelativeTime(DELAY_INC);
  int compteur = 0;
  
  public Tache_Compteur(SchedulingParameters sched, ReleaseParameters release)
  {
    super(sched, release); 
  }
  
  public void run()
  {
    while(true){
    	do{compteur++;
    	System.out.println("cpt: "+compteur);
    	
    		
    	}while(waitForNextPeriod());
    	System.out.println("depacement");
    }
    
  }  
}
