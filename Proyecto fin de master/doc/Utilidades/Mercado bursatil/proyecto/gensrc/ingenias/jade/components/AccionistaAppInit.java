

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.JADEAgent;

public  class AccionistaAppInit {


private static java.lang.String semaphore="AccionistaApp";
 


 private static Vector<AccionistaAppAppImp> appsinitialised=new Vector<AccionistaAppAppImp>();
 


 public static void initialize(AccionistaAppAppImp app){
   		final AccionistaAppAppImp appF=app; 
		new Thread(){
			public void run(){
				appF.showGUI();			 
			}
		}.start();
 }

 public static void shutdown(AccionistaAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<AccionistaAppAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static AccionistaAppApp createInstance(){
  	synchronized (semaphore) {
	AccionistaAppAppImp app=new AccionistaAppAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static AccionistaAppApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	AccionistaAppAppImp app=new AccionistaAppAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 