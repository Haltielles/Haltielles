/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes.control;

import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

/**
 *
 * @author khayzer
 */
public class GetJson {

    private URL url;
    private HttpURLConnection con;
    private int status;
    private BufferedReader in;
    private StringBuffer content;
    private JSONObject dados;
    private String dado;
    private JSONArray lista;
    boolean canConnect = false;
    private static final String path = "https://servicodados.ibge.gov.br/api/v2/censos/nomes/";
    String filtro = path;

    public String dadoRank(String decada, String localidade, String sexo) throws ProtocolException, IOException {
        //com tres campos
        if (!decada.isEmpty() && !localidade.isEmpty() && !sexo.isEmpty()) {
            filtro += "ranking?decada=" + decada + "&localidade=" + localidade + "&sexo=" + sexo;
        } else {
            //com dois campos
            if (!decada.isEmpty() && !localidade.isEmpty()) {
                filtro += "rnaking?decada=" + decada + "&localidade=" + localidade;
            } else if (!decada.isEmpty() && !sexo.isEmpty()) {
                //possui um bug na API que faz a consulta corretamente mas o campo sexo fica como null
                filtro += "ranking?decada=" + decada + "&sexo=" + sexo;
            } else if (!localidade.isEmpty() && !sexo.isEmpty()) {
                filtro += "ranking?localidade=" + localidade + "&sexo=" + sexo;
            } else {
                //com apenas um campo
                if (!decada.isEmpty()) {
                    filtro += "ranking/?decada=" + decada;
                } else if (!localidade.isEmpty()) {
                    filtro += "ranking/?localidade=" + localidade;
                } else if (!sexo.isEmpty()) {
                    filtro += "ranking/?sexo=" + sexo;
                } else {
                    filtro += "ranking";
                }
            }
        }
        return this.getJson();
    }

    public String dadoNome(String nome, String localidade, String sexo) throws ProtocolException, IOException {
        //com tres campos
        if (!nome.isEmpty() && !localidade.isEmpty() && !sexo.isEmpty()) {
            filtro += nome + "?localidade=" + localidade + "&sexo=" + sexo;
        } else {
            //com dois campos
            if (!nome.isEmpty() && !localidade.isEmpty()) {
                filtro += nome + "?localidade=" + localidade;
            } else if (!nome.isEmpty() && !sexo.isEmpty()) {
                filtro += nome + "?sexo=" + sexo;
            } else {
                //com apenas um campo
                if (!nome.isEmpty()) {
                    filtro += nome;
                } else {
                    return "erro";
                }
            }
        }
        return this.getJson();
    }

    public String getJson() throws MalformedURLException, ProtocolException, IOException {
        this.url = new URL(filtro);
        this.con = (HttpURLConnection) url.openConnection();
        this.con.setRequestMethod("GET");
        this.status = con.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            canConnect = true;
        }
        this.in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        this.dado = new String();
        this.dados = new JSONObject();
        this.content = new StringBuffer();
        this.lista = new JSONArray();
        String inputline;
        while ((inputline = in.readLine()) != null) {
            content.append(inputline);
        }
        in.close();
        filtro = path;
        return content.toString();
    }
}
