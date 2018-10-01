/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.model;

/**
 *
 * @author khayzer
 */
public class ResNomes {
    private String periodo;
    private Integer  frequencia;

    public ResNomes(String periodo, Integer frequencia) {
        this.periodo = periodo;
        this.frequencia = frequencia;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }
    
    
    
}
