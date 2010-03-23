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
import ingenias.jade.mental.EventRegistroEmpresa;

public class EmpresaAppAppImp extends EmpresaAppApp {

    EmpresaAppGUI gui;

    public EmpresaAppAppImp() {
        super();
    }

    public void showGUI() {
        gui = new EmpresaAppGUI(this);
    }

    public void showLog(String cadena) {
        gui.showLog(cadena);
    }

    public void showNumAcciones(long numero) {
        gui.showNumAcciones(numero);
    }

    public void showValor(double valor) {
        gui.showValor(valor);
    }

     public void showEstado(String estado) {
        gui.showEstado(estado);
    }

    public void registrar(String nombre, long numero, double valor) {

        EventRegistroEmpresa eRegistrar= new EventRegistroEmpresa();
        eRegistrar.setIdEmpresa(nombre);
        eRegistrar.setNumAcciones(numero);
        eRegistrar.setValorAccion(valor);

        try {
                System.out.println("getOwner().getMSM().addMentalEntity(eRegistrar)");
                getOwner().getMSM().addMentalEntity(eRegistrar);
        } catch (ingenias.exception.InvalidEntity ex){
                ex.printStackTrace();
        }
    }
}

 