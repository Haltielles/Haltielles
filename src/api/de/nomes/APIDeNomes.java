/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author khayzer
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.de.nomes;

import api.de.nomes.control.GetJson;
import java.io.IOException;
import java.net.ProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIDeNomes {

    public static void main(String[] args) throws ProtocolException, IOException, ParseException {
        GetJson aplication = new GetJson();
        String retorno = aplication.getdatafromapi().toString();
        System.out.println("retorno:" + retorno);
        JSONObject objeto = new JSONObject();
        JSONArray jsonarray= new JSONArray();
        JSONParser parser= new JSONParser();
        jsonarray= (JSONArray)parser.parse(retorno);
        objeto=(JSONObject)jsonarray.get(0);
        System.out.println(objeto.toString());
        
        
    }
}
