/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */
package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.mental.EventCompraAcciones;
import ingenias.jade.mental.EventInformacionBolsa;
import ingenias.jade.mental.EventVentaAcciones;

public class AccionistaAppAppImp extends AccionistaAppApp {

    private AccionistaAppGUI gui;

    public AccionistaAppAppImp() {
        super();
    }

    public void showGUI() {
        gui = new AccionistaAppGUI(this);
    }

    public void showInversiones(Vector<Inversion> Inversiones) {
        gui.showInversiones(Inversiones);
    }

    public void showEstado(String estado) {
        gui.showEstado(estado);
    }

    public void showLog(String cadena) {
        gui.showLog(cadena);
    }

    void obtenerInformacion() {
        EventInformacionBolsa eInformacion= new EventInformacionBolsa();
       
        try {
                System.out.println("getOwner().getMSM().addMentalEntity(eInformacion)");
                getOwner().getMSM().addMentalEntity(eInformacion);
        } catch (ingenias.exception.InvalidEntity ex){
                ex.printStackTrace();
        }
    }

    public void showDinero(Double dinero) {
        gui.showDinero(dinero);
    }

    void comprarAcciones(Empresa empresa, long numeroAcciones, double dinero, double coste) {
        EventCompraAcciones eCompra= new EventCompraAcciones();

        eCompra.setCoste(coste);
        eCompra.setDinero(dinero);
        eCompra.setEmpresa(empresa);
        eCompra.setNumAcciones(numeroAcciones);

        try {
                System.out.println("getOwner().getMSM().addMentalEntity(eInformacion)");
                getOwner().getMSM().addMentalEntity(eCompra);
        } catch (ingenias.exception.InvalidEntity ex){
                ex.printStackTrace();
        }
    }

    void venderAcciones(Empresa empresa, double dinero, long numeroAcciones) {
        EventVentaAcciones eVenta= new EventVentaAcciones();

        eVenta.setDinero(dinero);
        eVenta.setEmpresa(empresa);
        eVenta.setNumAcciones(numeroAcciones);

        try {
                System.out.println("getOwner().getMSM().addMentalEntity(eVenta)");
                getOwner().getMSM().addMentalEntity(eVenta);
        } catch (ingenias.exception.InvalidEntity ex){
                ex.printStackTrace();
        }
    }


}

 