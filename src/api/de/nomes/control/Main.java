/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.control;

import api.de.nomes.model.Nomes;
import api.de.nomes.model.Ranking;
import api.de.nomes.model.ResNomes;
import api.de.nomes.model.ResRanking;
import api.de.nomes.view.ExibeGetNome;
import api.de.nomes.view.ExibeGetRank;
import api.de.nomes.view.MainFrame;
import api.de.nomes.view.PainelBuscas;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author khayzer
 */
public class Main {

    public MainFrame frameprincipal;
    public ControlerMainFrame controlerprincipal;
    

    public static void main(String[] args) throws IOException, ProtocolException, ParseException {
        Main aplicacao = new Main();
        PainelBuscas painel = new PainelBuscas();
        painel.setControl(aplicacao.controlerprincipal);
        aplicacao.frameprincipal.getMainframe().add(painel);
        aplicacao.frameprincipal.getMainframe().setVisible(true);
    }

    public Main() throws IOException {
        this.frameprincipal = new MainFrame();
        this.controlerprincipal = new ControlerMainFrame();
        this.frameprincipal.setControler(controlerprincipal);
        this.controlerprincipal.setFrame(frameprincipal);
    }
}
