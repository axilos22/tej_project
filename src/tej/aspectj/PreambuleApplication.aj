package tej.aspectj;

import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;

public aspect PreambuleApplication {
	static final RelativeTime DELAY_INC = new RelativeTime(50,0);
	 RelativeTime delay= new RelativeTime(DELAY_INC);
	     // Coupe :
	     pointcut mainCall() :
	          execution(* tej..Application.main(..));
	    
	      before() : mainCall() {
	    	  System.out.println("<Aspect Preambule/> Hello RT world!");	    	
	      }
	      
	      pointcut overrunner():
	    	  execution(* *.Tache_Compteur.run());
	      before() : get(* compteur) {
	    delay.add(DELAY_INC,delay);
	    try{
	    	RealtimeThread.sleep(delay);
	    }catch(InterruptedException ie) {}
	     	     
}
}
