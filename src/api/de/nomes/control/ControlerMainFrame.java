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
import api.de.nomes.view.Botoes;
import api.de.nomes.view.ExibeGetNome;
import api.de.nomes.view.ExibeGetRank;
import api.de.nomes.view.FormRank;
import api.de.nomes.view.FormNome;
import api.de.nomes.view.MainFrame;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    public JFrame janela1, janela2, resultadoNome, resultadoRank;
    public FormNome formnome;
    public FormRank formrank;
    public GetJson requisitor;
    public ExibeGetRank dadorank;
    public FlowLayout layout;
    public Botoes botoes;
    public ArrayList<ExibeGetNome> nomesExibe;
    public ArrayList<Nomes> nomes;
    public Ranking rank;

    public ControlerMainFrame() throws ProtocolException, IOException {
        this.botoes = new Botoes(this);
        this.layout = new FlowLayout();
        this.nomesExibe = new ArrayList();
        this.nomes = new ArrayList();
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
        this.dadorank = new ExibeGetRank(this);
        this.fillFormRank(this.dadorank);
        this.resultadoRank.add(this.dadorank);
        this.resultadoRank.setSize(400, 400);
        this.resultadoRank.setVisible(true);
        this.resultadoRank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void voltaBRank() {
        this.resultadoRank.remove(this.dadorank);
        this.resultadoRank.setVisible(false);
    }

    public void buscaNome() throws IOException, ProtocolException, ParseException {
        String nome = this.formnome.getTextNome().getText();
        String localidade = this.formnome.getTextLocalidade().getText();
        String sexo = this.formnome.getTextSexo().getText();
        String retorno = this.requisitor.dadoNome(nome, localidade, sexo);
        if (retorno.equals("erro")) {
            JOptionPane.showMessageDialog(null, "preencha o campos nome para fazer a consulta");
        } else {
            this.resultadoNome.setLayout(layout);
            this.carregaJsonByName(retorno);
            for (int i = 0; i < nomes.size(); i++) {
                this.nomesExibe.add(new ExibeGetNome(this));
                this.fillFormNome((ExibeGetNome) this.nomesExibe.get(i), i);
                this.resultadoNome.add(this.nomesExibe.get(i));
            }
            this.resultadoNome.add(this.botoes);
            this.resultadoNome.setSize(420, 350 * this.nomes.size());
            this.resultadoNome.setVisible(true);
            this.resultadoNome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void voltaBNome() {
        this.resultadoNome.remove(botoes);
        for (int i = 0; i < nomes.size(); i++) {
            this.resultadoNome.remove(this.nomesExibe.get(i));
        }
        this.resultadoNome.setVisible(false);
    }

    public void fillFormNome(ExibeGetNome form, int cont) {
        form.getTextnome().setText(this.nomes.get(cont).getNome());
        form.getTextlocalidade().setText(this.nomes.get(cont).getLocalidade());
        form.getTextsexo().setText(this.nomes.get(cont).getSexo());
        DefaultTableModel modelo = (DefaultTableModel) form.getTabelares().getModel();
        for (int i = 0; i < nomes.get(cont).getRes().size(); i++) {
            modelo.addRow(new Object[]{((ResNomes) this.nomes.get(cont).getRes().get(i)).getPeriodo(),
                ((ResNomes) this.nomes.get(cont).getRes().get(i)).getFrequencia()});
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
        for (int i = 0; i < jsonarray.size(); i++) {
            objeto = (JSONObject) jsonarray.get(i);
            //parte onde pega o json e joga os dados dentro dos objetos
            String sexo = new String();
            if (objeto.get("sexo") == null) {
                sexo = "ambos";
            } else {
                sexo = objeto.get("sexo").toString();
            }
            this.nomes.add(new Nomes(objeto.get("nome").toString(),
                    objeto.get("localidade").toString(),
                    sexo,
                    objeto.get("res")));
        }
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
    //voce pode manipular direto o nomes e o rank pra pegar os dados que neste
    //ponto eles já vão estar carregados
    //conexão com o banco de dados e insert tabelas
    public void saveNome() {

    }

    public void saveRank() {

    }
}
