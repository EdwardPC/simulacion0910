

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



public class TPAObtenerInformacionATask extends Task{

 public TPAObtenerInformacionATask(String id){
  super(id,"TPAObtenerInformacionA");
 }



 public void execute() throws TaskException{


        AObtenerInformacionB  eiAObtenerInformacionB=(AObtenerInformacionB)this.getFirstInputOfType("AObtenerInformacionB");             

        EstadoAccionista  eiEstadoAccionista=(EstadoAccionista)this.getFirstInputOfType("EstadoAccionista");             





			
        AccionistaAppApp eaAccionistaApp=(AccionistaAppApp)this.getApplication("AccionistaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoTPAObtenerInformacionA <--- DO NOT REMOVE THIS	
System.out.println("Accionista: Informacion obtenida");
eiEstadoAccionista.setEstado("Informacion obtenida");

int i,indice;

Vector<Empresa> empresas = eiAObtenerInformacionB.getEmpresas();

Vector<Inversion> inversiones = eiEstadoAccionista.getInversiones();

Inversion inversion;

System.out.println("empresas: " + empresas);
System.out.println("inversiones: " + inversiones);

if(inversiones == null || inversiones.size()==0){
    inversiones = new Vector();
    
    for(i=0;i<empresas.size();i++){
        inversion = new Inversion(empresas.get(i),0L);
        inversiones.add(inversion);
    }
}else{
    for(i=0;i<empresas.size();i++){
        inversion = new Inversion(empresas.get(i),0L);
        indice = inversiones.indexOf(inversion);
        if(indice==-1){
            inversiones.add(inversion);
        }else{
            Inversion inversionAccionista = inversiones.get(indice);
            inversionAccionista.setEmpresa(empresas.get(i));
            inversiones.set(indice, inversionAccionista);
        }
    }
}

eiEstadoAccionista.setInversiones(inversiones);

eaAccionistaApp.showInversiones(inversiones);
eaAccionistaApp.showLog("Informacion obtenida");
eaAccionistaApp.showEstado("Informacion obtenida");

//#end_node:CodigoTPAObtenerInformacionA <--- DO NOT REMOVE THIS

 }
 
}

 