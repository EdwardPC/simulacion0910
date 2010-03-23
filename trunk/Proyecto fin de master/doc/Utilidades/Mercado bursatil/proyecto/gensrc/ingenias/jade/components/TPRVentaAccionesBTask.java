

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



public class TPRVentaAccionesBTask extends Task{

 public TPRVentaAccionesBTask(String id){
  super(id,"TPRVentaAccionesB");
 }



 public void execute() throws TaskException{


        RVentaAccionesA  eiRVentaAccionesA=(RVentaAccionesA)this.getFirstInputOfType("RVentaAccionesA");             

        EstadoBolsa  eiEstadoBolsa=(EstadoBolsa)this.getFirstInputOfType("EstadoBolsa");             





			
        BolsaAppApp eaBolsaApp=(BolsaAppApp)this.getApplication("BolsaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		AVentaAccionesB outputsdefaultAVentaAccionesB=
			(AVentaAccionesB)
				outputsdefault.getEntityByType("AVentaAccionesB");
		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoTPRVentaAccionesB <--- DO NOT REMOVE THIS	

Empresa empresa = eiRVentaAccionesA.getEmpresa();
Long numAcciones = eiRVentaAccionesA.getNumAcciones();
String idAccionista = eiRVentaAccionesA.getIdAccionista();

int indice;

System.out.println("Recibida peticion de venta de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " por el accionista " + idAccionista);
eaBolsaApp.showLog("Recibida peticion de venta de "+numAcciones+" acciones de la empresa "+ empresa.getIdEmpresa() + " por el accionista " + idAccionista);

Vector<Empresa> empresas = eiEstadoBolsa.getEmpresas();

indice = empresas.indexOf(empresa);

Empresa empresaBolsa = empresas.get(indice);

double dineroSinImpuesto = (double)numAcciones * (double)empresaBolsa.getValorAccion();
double impuestos = (double)dineroSinImpuesto * (double)0.07;
double dinero = dineroSinImpuesto - impuestos;

double nuevoValor = (double)empresaBolsa.getValorAccion() - (double)((double)numAcciones/(double)10000);

empresaBolsa.setDinero(empresaBolsa.getDinero()-dinero);
empresaBolsa.setNumAcciones(empresaBolsa.getNumAcciones()+numAcciones);
empresaBolsa.setValorAccion(nuevoValor);

empresas.set(indice, empresaBolsa);

eiEstadoBolsa.setEmpresas(empresas);

eaBolsaApp.actualizaEmpresa(empresaBolsa,indice);

outputsdefaultAVentaAccionesB.setDinero(dinero);
outputsdefaultAVentaAccionesB.setIdAccionista(idAccionista);
outputsdefaultAVentaAccionesB.setEmpresa(empresaBolsa);
outputsdefaultAVentaAccionesB.setNumAcciones(numAcciones);
outputsdefaultAVentaAccionesB.setEstado("Venta realizada");



//#end_node:CodigoTPRVentaAccionesB <--- DO NOT REMOVE THIS

 }
 
}

 