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

    public GetJson() throws MalformedURLException, ProtocolException, IOException {
        this.url = new URL("https://servicodados.ibge.gov.br/api/v2/censos/nomes/joao?localidade=33?sexo=m");
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
    }

    public StringBuffer getdatafromapi() throws IOException, ParseException {
        String inputline;
        while ((inputline = in.readLine()) != null) {
            content.append(inputline);
        }
        return content;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public JSONObject getDados() {
        return dados;
    }

    public void setDados(JSONObject dados) {
        this.dados = dados;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public JSONArray getLista() {
        return lista;
    }

    public void setLista(JSONArray lista) {
        this.lista = lista;
    }

}
