/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JFrame;

/**
 *
 * @author Josep
 */
public class MenuPrincipalVista {
    
    
    PanellPrincipal panellPrincipal;
    
    public static void main(String[] args) {
        MenuPrincipalVista app = new MenuPrincipalVista();
    }
    
    public MenuPrincipalVista(){
        JFrame finestraPrincipal = new JFrame("Grossa Calculator");
        panellPrincipal = new PanellPrincipal();
        
        finestraPrincipal.add(panellPrincipal);
        
        finestraPrincipal.setSize(panellPrincipal.getPreferredSize());
        finestraPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraPrincipal.setVisible(true);
        
    }

    public PanellPrincipal getPanellPrincipal() {
        return panellPrincipal;
    }
    
}
