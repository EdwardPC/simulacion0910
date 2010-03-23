

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



public class ILVenderAccionesATask extends Task{

 public ILVenderAccionesATask(String id){
  super(id,"ILVenderAccionesA");
 }



 public void execute() throws TaskException{


        TriggerVentaAccionA  eiTriggerVentaAccionA=(TriggerVentaAccionA)this.getFirstInputOfType("TriggerVentaAccionA");             

        EstadoAccionista  eiEstadoAccionista=(EstadoAccionista)this.getFirstInputOfType("EstadoAccionista");             

        Agent_data  eiAgent_data=(Agent_data)this.getFirstInputOfType("Agent_data");             





			
        AccionistaAppApp eaAccionistaApp=(AccionistaAppApp)this.getApplication("AccionistaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RVentaAccionesA outputsdefaultRVentaAccionesA=
			(RVentaAccionesA)
				outputsdefault.getEntityByType("RVentaAccionesA");
		
		RuntimeConversation outputsdefaultIVentaAcciones=
			(RuntimeConversation)
				outputsdefault.getEntityByType("IVentaAcciones");
		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type IVentaAcciones by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Bolsa"
      	//eoIVentaAcciones.addCollaborators("Local ID of the collaborator");
       	


//#start_node:CodigoILVenderAccionesA <--- DO NOT REMOVE THIS	

Empresa empresa = eiTriggerVentaAccionA.getEmpresa();
Double dinero = eiTriggerVentaAccionA.getDinero();
Long numAcciones = eiTriggerVentaAccionA.getNumAcciones();
String idAccionista = eiAgent_data.getAgentID();

System.out.println("Solicitando peticion al mercado bursatil");

eaAccionistaApp.showEstado("Vendiendo acciones");
eaAccionistaApp.showLog("Solicitando peticion al mercado bursatil");

eiEstadoAccionista.setEstado("Vendiendo acciones");
eiEstadoAccionista.setDinero(dinero);

outputsdefaultRVentaAccionesA.setIdAccionista(idAccionista);
outputsdefaultRVentaAccionesA.setEmpresa(empresa);
outputsdefaultRVentaAccionesA.setNumAcciones(numAcciones);

//#end_node:CodigoILVenderAccionesA <--- DO NOT REMOVE THIS

 }
 
}

 