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
public class ResRanking {
    private String nome;
    private int frequencia;
    private int ranking;

    public ResRanking(String nome, int frequencia, int ranquing) {
        this.nome = nome;
        this.frequencia = frequencia;
        this.ranking = ranquing;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranquing) {
        this.ranking = ranquing;
    }
    
    
}
