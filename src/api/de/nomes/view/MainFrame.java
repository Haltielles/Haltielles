/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.view;

import api.de.nomes.control.ControlerMainFrame;
import javax.swing.JFrame;

/**
 *
 * @author khayzer
 */
public class MainFrame {
    public JFrame mainframe;
    public ControlerMainFrame controler;

    public MainFrame() {
        this.mainframe = new JFrame();
        this.mainframe.setVisible(true);
        this.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainframe.setSize(200, 130);
    }

    public JFrame getMainframe() {
        return mainframe;
    }

    public void setMainframe(JFrame mainframe) {
        this.mainframe = mainframe;
    }

    public ControlerMainFrame getControler() {
        return controler;
    }

    public void setControler(ControlerMainFrame controler) {
        this.controler = controler;
    }

    
    
}
