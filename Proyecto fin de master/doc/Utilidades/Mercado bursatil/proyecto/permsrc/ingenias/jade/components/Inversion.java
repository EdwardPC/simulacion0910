/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.jade.components;

import java.util.Vector;

/**
 *
 * @author Victor
 */
public class Inversion {

    Empresa Empresa;
    long NumAccionesCompradas;

    Inversion(Empresa empresa, long numAcciones) {
        this.Empresa = empresa;
        NumAccionesCompradas = numAcciones;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresas) {
        this.Empresa = Empresas;
    }

    public long getNumAccionesCompradas() {
        return NumAccionesCompradas;
    }

    public void setNumAccionesCompradas(long NumAccionesCompradas) {
        this.NumAccionesCompradas = NumAccionesCompradas;
    }

    public String toString() {
        return "" +Empresa.IdEmpresa+ " - "+
               "Num. Acciones compradas: "+NumAccionesCompradas;
    }

    public boolean equals(Object obj)
    {
        if( obj instanceof Inversion)
        {
            Inversion oObjetoLocal = (Inversion)obj;

            if(this.Empresa.getIdEmpresa().equals(oObjetoLocal.getEmpresa().getIdEmpresa()))
            {
                   return true;
            }
        }
        return false;
    }

   
}

