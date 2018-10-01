/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author khayzer
 */
public class Nomes {

    private String nome;
    private String localidade;
    private String sexo;
    private ArrayList<ResNomes> res;

    public Nomes(String nome, String localidade, String sexo, Object res) {
        this.nome = nome;
        this.localidade = localidade;
        this.sexo = sexo;
        this.res = this.amontaRes((JSONArray) res);
    }

    public ArrayList amontaRes(JSONArray res) {
        ArrayList<ResNomes> lista = new <ResNomes>ArrayList();
        for (int i = 0; i < res.size(); i++) {
            JSONObject objeto = new JSONObject();
            objeto = (JSONObject) res.get(i);
            lista.add(new ResNomes(objeto.get("periodo").toString(),
                    Integer.parseInt(objeto.get("frequencia").toString())));
        }
        return lista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
