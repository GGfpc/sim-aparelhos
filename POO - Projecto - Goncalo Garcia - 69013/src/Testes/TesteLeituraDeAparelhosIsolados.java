package Testes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Projecto.Aparelho;
import Projecto.Instalacao;
import Projecto.Computador;
import Projecto.Frigorifico;
import Projecto.Lampada;
import Projecto.MicroOndas;
import Projecto.Torradeira;

public class TesteLeituraDeAparelhosIsolados {
	
	private Instalacao instalacao;
	
	@BeforeClass
	public static void preparacaoInicial() {
	}

	@Before
	public void preparacaoDeTeste() {
	}
	
	@Test
	public void testeInstalacaoAparelhos() {
		JSONParser json = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject)json.parse(new BufferedReader(new FileReader("frigorifico.json")));
			Frigorifico frigo = (Frigorifico) Aparelho.novoAparelho(obj);

			assertNotNull(frigo);
			
			obj = (JSONObject)json.parse(new BufferedReader(new FileReader("computador.json")));
			Computador computador = (Computador) Aparelho.novoAparelho(obj);
		
			assertNotNull(computador);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
}
