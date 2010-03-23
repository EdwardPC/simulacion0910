

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



public class TPACompraAccionesATask extends Task{

 public TPACompraAccionesATask(String id){
  super(id,"TPACompraAccionesA");
 }



 public void execute() throws TaskException{


        ACompraAccionesB  eiACompraAccionesB=(ACompraAccionesB)this.getFirstInputOfType("ACompraAccionesB");             

        EstadoAccionista  eiEstadoAccionista=(EstadoAccionista)this.getFirstInputOfType("EstadoAccionista");             





			
        AccionistaAppApp eaAccionistaApp=(AccionistaAppApp)this.getApplication("AccionistaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoTPACompraAccionesA <--- DO NOT REMOVE THIS	

Empresa empresa = eiACompraAccionesB.getEmpresa();
Double coste = eiACompraAccionesB.getCoste();
Long numAcciones = eiACompraAccionesB.getNumAcciones();
String idAccionista = eiACompraAccionesB.getIdAccionista();
String estado = eiACompraAccionesB.getEstado();
Inversion inversion = new Inversion(empresa,numAcciones);

if(estado.equals("Compra realizada")){

    int indice;

    Vector<Inversion> inversiones = eiEstadoAccionista.getInversiones();

    indice = inversiones.indexOf(inversion);

    Inversion inversionAccionista = inversiones.get(indice);

    System.out.println("Indice: " + indice);
    System.out.println("inversionAccionista: " + inversionAccionista);

    long nuevoValor = inversionAccionista.getNumAccionesCompradas() + inversion.getNumAccionesCompradas();

    inversion.setNumAccionesCompradas(nuevoValor);

    inversiones.set(indice, inversion);

    eiEstadoAccionista.setInversiones(inversiones);
    eiEstadoAccionista.setDinero(eiEstadoAccionista.getDinero()-coste);
    eiEstadoAccionista.setEstado(estado);

    System.out.println("Compradas "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste);
    eaAccionistaApp.showLog("Compradas "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste);
    eaAccionistaApp.showInversiones(inversiones);
    eaAccionistaApp.showEstado(estado);
    eaAccionistaApp.showDinero(eiEstadoAccionista.getDinero());
    

}else{
    eaAccionistaApp.showEstado(estado);
    eaAccionistaApp.showLog("Compra no efectuada. Causa: " + estado);
}


//#end_node:CodigoTPACompraAccionesA <--- DO NOT REMOVE THIS

 }
 
}

 