package tej.aspectj;

import tej.controller.*;
import javax.realtime.*;

public aspect TimedLogging {
	Clock rtClk = Clock.getRealtimeClock();
	AbsoluteTime rt0, rt1;

	after(int compteur) : set(* Tache_Compteur.compteur)
	&&args(compteur)
	&& !withincode(* run()){
		rt0 = rtClk.getTime();
	}

	after(int compteur) : set(* Tache_Compteur.compteur) 
	&& args(compteur)
	&& withincode(* run()){
		rt1 = rtClk.getTime();
		rt1.subtract(rt0);
		System.out.println(rt1.toString()+"Cpt= " + compteur);
	}
}
