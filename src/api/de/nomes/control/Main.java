/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.control;

import api.de.nomes.model.Nomes;
import api.de.nomes.view.MainFrame;
import java.io.IOException;
import java.net.ProtocolException;
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
        aplicacao.carregaJson();
    }

    public Main() {
        this.frameprincipal = new MainFrame();
        this.controlerprincipal = new ControlerMainFrame();
        this.frameprincipal.setControler(controlerprincipal);
        this.controlerprincipal.setFrame(frameprincipal);
    }

    public void carregaJson() throws ProtocolException, IOException, ParseException {
        GetJson aplication = new GetJson();
        String retorno = aplication.getdatafromapi().toString();
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONParser parser = new JSONParser();
        jsonarray = (JSONArray) parser.parse(retorno);
        objeto = (JSONObject) jsonarray.get(0);
        //parte onde pega o json e joga os dados dentro dos objetos
       // Nomes nomes = new Nomes(objeto.get("nome").toString(),objeto.get("localidade").toString(),objeto.get("sexo").toString(),objeto.get("res"));
       // System.out.println("nome"+nomes.getNome());
    }
}
