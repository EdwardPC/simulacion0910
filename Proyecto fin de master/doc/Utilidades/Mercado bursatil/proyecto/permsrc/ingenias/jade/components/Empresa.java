/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.jade.components;

import java.text.DecimalFormat;

/**
 *
 * @author Victor
 */
public class Empresa {

    String IdEmpresa;
    double ValorAccion;
    long NumAcciones;
    double Dinero;

    DecimalFormat df = new DecimalFormat("0.00");

    Empresa(String empresa, double valorAccion, long numAcciones) {
        IdEmpresa = empresa;
        ValorAccion = valorAccion;
        NumAcciones = numAcciones;
        Dinero = 0;
    }

    public String getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(String IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    public long getNumAcciones() {
        return NumAcciones;
    }

    public void setNumAcciones(long NumAcciones) {
        this.NumAcciones = NumAcciones;
    }

    public double getValorAccion() {
        return ValorAccion;
    }

    public void setValorAccion(double ValorAccion) {
        this.ValorAccion = ValorAccion;
    }

    public double getDinero() {
        return Dinero;
    }

    public void setDinero(double Dinero) {
        this.Dinero = Dinero;
    }

    public String toString() {
        return "" +IdEmpresa+ " - "+
               "Num. Acciones: "+NumAcciones+ " - "+
               "Valor accion: "+ValorAccion+" euros"+ " - "+
               "Dinero ganado: "+df.format(Dinero)+" euros";
    }

    public boolean equals(Object obj)
    {
        if( obj instanceof Empresa)
        {
            Empresa oObjetoLocal = (Empresa)obj;

            if(this.IdEmpresa.equals(oObjetoLocal.getIdEmpresa()))
            {
                   return true;
            }
        }
        return false;
    }

}
