/*SENAI 
*PSIN
*MI-66
*Objetivo: Classe utilizada para ler uma pagina JSON da web e transformar os dados em um objeto
*Autores: Guilherme Witkosky, Kelvin Schneider, Leonardo Pio, Rafael Adriano e Vinicius Silva Sena
*Data: 06/08/2020
*
*Alterações:
*Nome: Leonardo Pio
*Data: 06/08/2020
*Alterou: Documentação de código
*/

package br.com.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.dao.GenericDaoUf;
import br.com.model.Endereco;
import br.com.model.UnidadeFederativa;

public class JSONReader {
	
	public Endereco ReaderJSONViaCep(String cep) {
		JSONObject objJson;
		JSONParser parser = new JSONParser();
		GenericDaoUf genericDaoUf = new GenericDaoUf();

		Endereco ende = new Endereco();

		try {

			URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");

			Reader reader = new InputStreamReader(url.openStream());

			objJson = (JSONObject) parser.parse(reader);

			ende.setCep((String) objJson.get("cep"));
			ende.setLogradouro((String) objJson.get("logradouro"));
			ende.setBairro((String) objJson.get("bairro"));
			ende.setLocalidade((String) objJson.get("localidade"));
			ende.setComplemento((String) objJson.get("complemento"));
			ende.setUnidadeFederativa((UnidadeFederativa) genericDaoUf.search(new UnidadeFederativa(), ((String) objJson.get("uf"))));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ende;
		
	}
	
}
