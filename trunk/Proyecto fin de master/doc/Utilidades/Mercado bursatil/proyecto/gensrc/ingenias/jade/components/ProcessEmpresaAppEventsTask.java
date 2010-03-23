

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



public class ProcessEmpresaAppEventsTask extends Task{

 public ProcessEmpresaAppEventsTask(String id){
  super(id,"ProcessEmpresaAppEvents");
 }



 public void execute() throws TaskException{


        EventRegistroEmpresa  eiEventRegistroEmpresa=(EventRegistroEmpresa)this.getFirstInputOfType("EventRegistroEmpresa");             





			
        EmpresaAppApp eaEmpresaApp=(EmpresaAppApp)this.getApplication("EmpresaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		TriggerRegistroEmpresaE outputsdefaultTriggerRegistroEmpresaE=
			(TriggerRegistroEmpresaE)
				outputsdefault.getEntityByType("TriggerRegistroEmpresaE");
		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoProcessEmpresaAppEvents <--- DO NOT REMOVE THIS	
String empresa =  eiEventRegistroEmpresa.getIdEmpresa();
double valorAccion = eiEventRegistroEmpresa.getValorAccion();
long numAcciones = eiEventRegistroEmpresa.getNumAcciones();

System.out.println("Evento de la aplicacion de empresa de " +empresa+ " recibido");

eaEmpresaApp.showLog("Evento de la aplicacion de empresa de " +empresa+ " recibido");

outputsdefaultTriggerRegistroEmpresaE.setId(empresa);
outputsdefaultTriggerRegistroEmpresaE.setNumAcciones(numAcciones);
outputsdefaultTriggerRegistroEmpresaE.setValorAccion(valorAccion);
//#end_node:CodigoProcessEmpresaAppEvents <--- DO NOT REMOVE THIS

 }
 
}

 