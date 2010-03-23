

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.comm.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;



public class ProcessCompraAccionesAppEventsTask extends Task{

 public ProcessCompraAccionesAppEventsTask(String id){
  super(id,"ProcessCompraAccionesAppEvents");
 }



 public void execute() throws TaskException{


        EventCompraAcciones  eiEventCompraAcciones=(EventCompraAcciones)this.getFirstInputOfType("EventCompraAcciones");             





			
        AccionistaAppApp eaAccionistaApp=(AccionistaAppApp)this.getApplication("AccionistaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		TriggerComprarAccionA outputsdefaultTriggerComprarAccionA=
			(TriggerComprarAccionA)
				outputsdefault.getEntityByType("TriggerComprarAccionA");
		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoProcessCompraAccionesAppEvents <--- DO NOT REMOVE THIS	

Empresa empresa = eiEventCompraAcciones.getEmpresa();
Double coste = eiEventCompraAcciones.getCoste();
Double dinero = eiEventCompraAcciones.getDinero();
Long numAcciones = eiEventCompraAcciones.getNumAcciones();

eaAccionistaApp.showLog("Recibida peticion de compra de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste);
System.out.println("Recibida peticion de compra de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste);

eaAccionistaApp.showEstado("Recibida peticion de compra");

outputsdefaultTriggerComprarAccionA.setCoste(coste);
outputsdefaultTriggerComprarAccionA.setDinero(dinero);
outputsdefaultTriggerComprarAccionA.setEmpresa(empresa);
outputsdefaultTriggerComprarAccionA.setNumAcciones(numAcciones);

//#end_node:CodigoProcessCompraAccionesAppEvents <--- DO NOT REMOVE THIS

 }
 
}

 