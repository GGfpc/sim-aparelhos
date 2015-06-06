package TestesFinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Projecto.Chart;
import Projecto.Aparelho;
import Projecto.Instalacao;
import Projecto.Ligavel;
import Projecto.MaquinaDeLavar;
import Projecto.Relogio;
import Projecto.Computador;
import Projecto.Frigorifico;
import Projecto.Lampada;
import Projecto.MicroOndas;
import Projecto.Torradeira;

public class Main {

	public static void main(String[] args) {
		JSONParser json = new JSONParser();
		try {
			Instalacao instalacao = Instalacao.getInstanciaUnica(); 
			
			
			Chart grafico = new Chart("Potencia por linha");
			
			instalacao.addObserver(grafico);

			JSONArray objectos = (JSONArray) json.parse(new BufferedReader(new FileReader("instalacao.json")));

			instalacao.init(objectos);

			JSONArray listaAparelhos = (JSONArray) json.parse(new BufferedReader(new FileReader("aparelhos.json")));
			
			List<Ligavel> aparelhos = instalacao.lerAparelhos(listaAparelhos);
			
			JSONArray listaLigacoes = (JSONArray) json.parse(new BufferedReader(new FileReader("ligacoes.json")));
			instalacao.lerLigacoes(listaLigacoes, aparelhos);		
			
			
			JSONArray listaEventos = (JSONArray) json.parse(new BufferedReader(new FileReader("eventos.json")));
			instalacao.lerEventos(listaEventos);
		
			
			long fim = Long.valueOf(JOptionPane.showInputDialog("Please enter the end time"));
			
			grafico.setVisible(true);
			instalacao.simula(fim);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();		
		}
		
	}

	


}
