/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author khayzer
 */
public class Ranking {

    private String localidade;
    private String sexo;
    private ArrayList res;

    public Ranking(String localidade, String sexo, Object res) {
        this.localidade = localidade;
        this.sexo = sexo;
        this.res = this.amontaRes((JSONArray)res);
    }
    public ArrayList amontaRes(JSONArray res) {
        ArrayList<ResRanking> lista = new <ResRanking>ArrayList();
        for (int i = 0; i < res.size(); i++) {
            JSONObject objeto = new JSONObject();
            objeto = (JSONObject) res.get(i);
            lista.add(new ResRanking(objeto.get("nome").toString(),
                    Integer.parseInt(objeto.get("frequencia").toString()),
                    Integer.parseInt(objeto.get("ranking").toString())));
        }
        return lista;
    }
    
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public ArrayList getRes() {
        return res;
    }

    public void setRes(ArrayList res) {
        this.res = res;
    }

}
