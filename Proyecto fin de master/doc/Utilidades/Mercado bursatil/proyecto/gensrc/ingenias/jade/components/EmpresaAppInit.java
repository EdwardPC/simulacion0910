

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

public  class EmpresaAppInit {


private static java.lang.String semaphore="EmpresaApp";
 


 private static Vector<EmpresaAppAppImp> appsinitialised=new Vector<EmpresaAppAppImp>();
 


 public static void initialize(EmpresaAppAppImp app){
  		final EmpresaAppApp appF=app; 
		new Thread(){
			public void run(){
				appF.showGUI();			 
			}
		}.start();
 }

 public static void shutdown(EmpresaAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<EmpresaAppAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static EmpresaAppApp createInstance(){
  	synchronized (semaphore) {
	EmpresaAppAppImp app=new EmpresaAppAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static EmpresaAppApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	EmpresaAppAppImp app=new EmpresaAppAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 