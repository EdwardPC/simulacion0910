

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



public class TPRCompraAccionesBTask extends Task{

 public TPRCompraAccionesBTask(String id){
  super(id,"TPRCompraAccionesB");
 }



 public void execute() throws TaskException{


        RCompraAccionesA  eiRCompraAccionesA=(RCompraAccionesA)this.getFirstInputOfType("RCompraAccionesA");             

        EstadoBolsa  eiEstadoBolsa=(EstadoBolsa)this.getFirstInputOfType("EstadoBolsa");             





			
        BolsaAppApp eaBolsaApp=(BolsaAppApp)this.getApplication("BolsaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		ACompraAccionesB outputsdefaultACompraAccionesB=
			(ACompraAccionesB)
				outputsdefault.getEntityByType("ACompraAccionesB");
		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoTPRCompraAccionesB <--- DO NOT REMOVE THIS	

Empresa empresa = eiRCompraAccionesA.getEmpresa();
Double coste = eiRCompraAccionesA.getCoste();
Long numAcciones = eiRCompraAccionesA.getNumAcciones();
String idAccionista = eiRCompraAccionesA.getIdAccionista();

int indice;

System.out.println("Recibida peticion de compra de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste + " por el accionista " + idAccionista);
eaBolsaApp.showLog("Recibida peticion de compra de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " con un coste de " + coste + " por el accionista " + idAccionista);

Vector<Empresa> empresas = eiEstadoBolsa.getEmpresas();

indice = empresas.indexOf(empresa);

Empresa empresaBolsa = empresas.get(indice);

if(empresaBolsa.getNumAcciones()-numAcciones>=0){

    double nuevoValor = (double)empresaBolsa.getValorAccion() + (double)((double)numAcciones/(double)10000);

    empresaBolsa.setDinero(coste+empresaBolsa.getDinero());
    empresaBolsa.setNumAcciones(empresaBolsa.getNumAcciones()-numAcciones);
    empresaBolsa.setValorAccion(nuevoValor);

    empresas.set(indice, empresaBolsa);

    eiEstadoBolsa.setEmpresas(empresas);

    eaBolsaApp.actualizaEmpresa(empresaBolsa,indice);

    outputsdefaultACompraAccionesB.setCoste(coste);
    outputsdefaultACompraAccionesB.setIdAccionista(idAccionista);
    outputsdefaultACompraAccionesB.setEmpresa(empresaBolsa);
    outputsdefaultACompraAccionesB.setNumAcciones(numAcciones);
    outputsdefaultACompraAccionesB.setEstado("Compra realizada");
}else{
    outputsdefaultACompraAccionesB.setEstado("El numero de acciones para comprar supera el limite permitido por la empresa.\n\nLimite acciones: " + empresaBolsa.getNumAcciones());
}


//#end_node:CodigoTPRCompraAccionesB <--- DO NOT REMOVE THIS

 }
 
}

 