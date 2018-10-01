/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.model;

import java.util.ArrayList;

/**
 *
 * @author khayzer
 */
public class Ranking {

    private String localidade;
    private String sexo;
    private ArrayList res;

    public Ranking(String localidade, String sexo, ArrayList res) {
        this.localidade = localidade;
        this.sexo = sexo;
        this.res = res;
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
