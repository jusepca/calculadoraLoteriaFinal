/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import model.PremiGrossa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuPrincipalVista;

/**
 *
 * @author Josep
 */
public class ControladorPrincipal implements ActionListener {
    static private MenuPrincipalVista menuPrincipalVista;
    
        
    public ControladorPrincipal(){
        menuPrincipalVista = new MenuPrincipalVista();
        
        menuPrincipalVista.getPanellPrincipal().getjButton1().addActionListener(this);
        menuPrincipalVista.getPanellPrincipal().getjButton2().addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuPrincipalVista.getPanellPrincipal().getjButton1()){
            //arranca tema
            String sRondes = menuPrincipalVista.getPanellPrincipal().getjTextField1().getText();
            String sParticipacions = menuPrincipalVista.getPanellPrincipal().getjTextField2().getText();
            
            String[] ssParticipacions = sParticipacions.split(",");
            int[] iParticipacions = new int[ssParticipacions.length];
            for (int i = 0; i< ssParticipacions.length;i++){
                iParticipacions[i] = Integer.parseInt(ssParticipacions[i].trim());
            }
            
            PremiGrossa premiGrossa = new PremiGrossa(Integer.parseInt(sRondes), iParticipacions);
            menuPrincipalVista.getPanellPrincipal().getjTextArea1().setText(premiGrossa.getResumText());
        }else if(e.getSource() == menuPrincipalVista.getPanellPrincipal().getjButton2()){
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    }
    
}
