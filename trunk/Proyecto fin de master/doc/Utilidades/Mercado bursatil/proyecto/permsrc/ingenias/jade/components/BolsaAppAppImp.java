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

public class BolsaAppAppImp extends BolsaAppApp {

    private BolsaAppGUI gui;

    public BolsaAppAppImp() {
        super();
    }

    public void showLog(String cadena) {
       gui.showLog(cadena);
    }

    public void addEmpresa(Empresa empresa) {
        gui.addEmpresa(empresa);
    }

    public void actualizaEmpresa(Empresa empresa, int indice) {
        gui.actualizaEmpresa(empresa,indice);
    }

    public void showGUI() {
        gui = new BolsaAppGUI(this);
    }
}

 