

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

public  class BolsaAppInit {


private static java.lang.String semaphore="BolsaApp";
 


 private static Vector<BolsaAppAppImp> appsinitialised=new Vector<BolsaAppAppImp>();
 


 public static void initialize(BolsaAppAppImp app){
   		final BolsaAppAppImp appF=app; 
		new Thread(){
			public void run(){
				appF.showGUI();			 
			}
		}.start();
 }

 public static void shutdown(BolsaAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<BolsaAppAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static BolsaAppApp createInstance(){
  	synchronized (semaphore) {
	BolsaAppAppImp app=new BolsaAppAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static BolsaAppApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	BolsaAppAppImp app=new BolsaAppAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 