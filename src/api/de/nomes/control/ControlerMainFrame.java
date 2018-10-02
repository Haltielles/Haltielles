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
import api.de.nomes.view.FormRank;
import api.de.nomes.view.FormNome;
import api.de.nomes.view.MainFrame;
import java.io.IOException;
import java.net.ProtocolException;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author khayzer
 */
public class ControlerMainFrame {

    public MainFrame frame;
    JFrame janela1, janela2, resultadoNome, resultadoRank;
    FormNome formnome;
    FormRank formrank;
    GetJson requisitor;
    public Nomes nome;
    public Ranking rank;

    public ControlerMainFrame() throws ProtocolException, IOException {
        this.janela1 = new JFrame();
        this.janela2 = new JFrame();
        this.resultadoNome = new JFrame();
        this.resultadoRank = new JFrame();
        this.requisitor = new GetJson();
        this.formnome = new FormNome(this);
        this.formrank = new FormRank(this);
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public void criaFrameNome() {
        janela1.add(formnome);
        janela1.setVisible(true);
        janela1.setSize(200, 200);
        janela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void criaFrameRank() {
        janela2.add(formrank);
        janela2.setVisible(true);
        janela2.setSize(200, 200);
        janela2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }

    public void voltarNome() {
        janela1.setVisible(false);
    }

    public void voltarRank() {
        janela2.setVisible(false);
    }

    public void buscaRank() throws IOException, ParseException {
        String decada = this.formrank.getTextdecada().getText();
        String localidade = this.formrank.getTextlocalidade().getText();
        String sexo = this.formrank.getTextsexo().getText();
        String retorno = this.requisitor.dadoRank(decada, localidade, sexo);
        this.carregaJsonByRank(retorno);
        ExibeGetRank dado = new ExibeGetRank(this);
        this.fillFormRank(dado);
        this.resultadoRank.add(dado);
        this.resultadoRank.setSize(400, 350);
        this.resultadoRank.setVisible(true);
        this.resultadoRank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void voltaBRank() {
        this.resultadoRank.removeAll();
        this.resultadoRank.setVisible(false);
    }

    public void buscaNome() throws IOException, ProtocolException, ParseException {
        String nome = this.formnome.getTextNome().getText();
        String localidade = this.formnome.getTextLocalidade().getText();
        String sexo = this.formnome.getTextSexo().getText();
        String retorno = this.requisitor.dadoNome(nome, localidade, sexo);
        this.carregaJsonByName(retorno);
        ExibeGetNome dado = new ExibeGetNome(this);
        this.fillFormNome(dado);
        this.resultadoNome.add(dado);
        this.resultadoNome.setSize(400, 350);
        this.resultadoNome.setVisible(true);
        this.resultadoNome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void voltaBNome() {
        this.resultadoNome.removeAll();
        this.resultadoNome.setVisible(false);
    }

    public void fillFormNome(ExibeGetNome form) {
        form.getTextnome().setText(this.nome.getNome());
        form.getTextlocalidade().setText(this.nome.getLocalidade());
        form.getTextsexo().setText(this.nome.getSexo());
        DefaultTableModel modelo = (DefaultTableModel) form.getTabelares().getModel();
        for (int i = 0; i < nome.getRes().size(); i++) {
            modelo.addRow(new Object[]{((ResNomes) this.nome.getRes().get(i)).getPeriodo(),
                ((ResNomes) this.nome.getRes().get(i)).getFrequencia()});
        }
        form.getTabelares().setModel(modelo);
    }

    public void fillFormRank(ExibeGetRank form) {
        form.getTextlocalidade().setText(this.rank.getLocalidade());
        form.getTextsexo().setText(this.rank.getSexo());
        DefaultTableModel modelo = (DefaultTableModel) form.getTabelaRes().getModel();
        for (int i = 0; i < rank.getRes().size(); i++) {
            modelo.addRow(new Object[]{((ResRanking) this.rank.getRes().get(i)).getNome(),
                ((ResRanking) this.rank.getRes().get(i)).getFrequencia(),
                ((ResRanking) this.rank.getRes().get(i)).getRanking()});
        }
        form.getTabelaRes().setModel(modelo);
    }

    public void carregaJsonByName(String json) throws ProtocolException, IOException, ParseException {
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONParser parser = new JSONParser();
        jsonarray = (JSONArray) parser.parse(json);
        objeto = (JSONObject) jsonarray.get(0);
        //parte onde pega o json e joga os dados dentro dos objetos
        String sexo = new String();
        if (objeto.get("sexo") == null) {
            sexo = "ambos";
        } else {
            sexo = objeto.get("sexo").toString();
        }
        this.nome = new Nomes(objeto.get("nome").toString(),
                objeto.get("localidade").toString(),
                sexo, objeto.get("res"));
    }

    public void carregaJsonByRank(String json) throws ProtocolException, IOException, ParseException {
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONParser parser = new JSONParser();
        jsonarray = (JSONArray) parser.parse(json);
        objeto = (JSONObject) jsonarray.get(0);
        //parte onde pega o json e joga os dados dentro dos objetos
        String sexo = new String();
        if (objeto.get("sexo") == null) {
            sexo = "ambos";
        } else {
            sexo = objeto.get("sexo").toString();
        }
        this.rank = new Ranking(objeto.get("localidade").toString(),
                sexo,
                objeto.get("res"));
    }
    //-------------sua parte ju fica daqui pra baixo-----------------------//
    //voce pode manipular direto o nome e o rank pra pegar os dados que neste
    //ponto eles já vão estar carregados
    //conexão com o banco de dados e insert tabelas
    public void saveNome(){
        
    }
    
    public void saveRank(){
        
    }
}
