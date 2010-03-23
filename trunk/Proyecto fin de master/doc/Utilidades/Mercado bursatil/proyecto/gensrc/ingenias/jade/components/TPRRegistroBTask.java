

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



public class TPRRegistroBTask extends Task{

 public TPRRegistroBTask(String id){
  super(id,"TPRRegistroB");
 }



 public void execute() throws TaskException{


        RRegistroEmpresaE  eiRRegistroEmpresaE=(RRegistroEmpresaE)this.getFirstInputOfType("RRegistroEmpresaE");             

        EstadoBolsa  eiEstadoBolsa=(EstadoBolsa)this.getFirstInputOfType("EstadoBolsa");             





			
        BolsaAppApp eaBolsaApp=(BolsaAppApp)this.getApplication("BolsaApp");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		ARegistroEmpresaB outputsdefaultARegistroEmpresaB=
			(ARegistroEmpresaB)
				outputsdefault.getEntityByType("ARegistroEmpresaB");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:CodigoTPRRegistroB <--- DO NOT REMOVE THIS	

String empresa =  eiRRegistroEmpresaE.getIdEmpresa();
double valorAccion = eiRRegistroEmpresaE.getValorAccion();
long numAcciones = eiRRegistroEmpresaE.getNumAcciones();

Empresa nuevaEmpresa = new Empresa(empresa,valorAccion,numAcciones);

System.out.println("Registrando empresa en bolsa.\nInformacion de la empresa:\n"+nuevaEmpresa);

eaBolsaApp.showLog("Registrando empresa en bolsa.\nInformacion de la empresa:\n"+nuevaEmpresa);

java.util.Vector<Empresa> empresas = eiEstadoBolsa.getEmpresas();

if(empresas==null){
    empresas = new Vector();
}

empresas.add(nuevaEmpresa);

eiEstadoBolsa.setEmpresas(empresas);

eaBolsaApp.addEmpresa(nuevaEmpresa);

//#end_node:CodigoTPRRegistroB <--- DO NOT REMOVE THIS

 }
 
}

 