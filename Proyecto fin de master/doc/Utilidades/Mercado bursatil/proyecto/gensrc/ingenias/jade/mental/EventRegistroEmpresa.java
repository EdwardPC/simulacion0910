

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


package ingenias.jade.mental;

import java.util.*;
import ingenias.jade.components.*;
import ingenias.editor.entities.*;
import ingenias.editor.entities.ViewPreferences.ViewType;

public class EventRegistroEmpresa extends ingenias.editor.entities.RuntimeEvent{
   
    java.lang.String IdEmpresa;   
   
    long NumAcciones;   
   
    double ValorAccion;   
    
   
   public EventRegistroEmpresa (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="EventRegistroEmpresa";
  }
  

  public EventRegistroEmpresa (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
   this.type="EventRegistroEmpresa";
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "EventRegistroEmpresa";
  }
  
   public String getParentType(){
   return "RuntimeEvent";
  }
  
   

   public void setIdEmpresa(java.lang.String value){
     IdEmpresa=value;   
   };
   
   public java.lang.String getIdEmpresa(){
     return IdEmpresa;      
   }
   

   public void setNumAcciones(long value){
     NumAcciones=value;   
   };
   
   public long getNumAcciones(){
     return NumAcciones;      
   }
   

   public void setValorAccion(double value){
     ValorAccion=value;   
   };
   
   public double getValorAccion(){
     return ValorAccion;      
   }
    
  
  
}

 