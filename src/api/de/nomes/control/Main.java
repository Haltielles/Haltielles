/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.control;

import api.de.nomes.model.Nomes;
import api.de.nomes.model.Ranking;
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
        aplicacao.carregaJsonByRank();
    }
    
    public Main() {
        this.frameprincipal = new MainFrame();
        this.controlerprincipal = new ControlerMainFrame();
        this.frameprincipal.setControler(controlerprincipal);
        this.controlerprincipal.setFrame(frameprincipal);
    }
    
    public void carregaJsonByName() throws ProtocolException, IOException, ParseException {
        GetJson aplication = new GetJson();
        String retorno = aplication.getdatafromapi().toString();
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONParser parser = new JSONParser();
        jsonarray = (JSONArray) parser.parse(retorno);
        objeto = (JSONObject) jsonarray.get(0);
        //parte onde pega o json e joga os dados dentro dos objetos
        String sexo = new String();
        if (objeto.get("sexo") == null) {
            sexo = "ambos";
        } else {
            sexo = objeto.get("sexo").toString();
        }
        Nomes nomes = new Nomes(objeto.get("nome").toString(), objeto.get("localidade").toString(), sexo, objeto.get("res"));
        System.out.println("nome" + nomes.getNome());
    }
    
    public void carregaJsonByRank() throws ProtocolException, IOException, ParseException {
        GetJson aplication = new GetJson();
        String retorno = aplication.getdatafromapi().toString();
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONParser parser = new JSONParser();
        jsonarray = (JSONArray) parser.parse(retorno);
        objeto = (JSONObject) jsonarray.get(0);
        //parte onde pega o json e joga os dados dentro dos objetos
        String sexo = new String();
        if (objeto.get("sexo") == null) {
            sexo = "ambos";
        } else {
            sexo = objeto.get("sexo").toString();
        }
        Ranking rank = new Ranking(objeto.get("localidade").toString(),
                sexo,
                objeto.get("res"));
        System.out.println("sexo:" + rank.getSexo());
    }
}
