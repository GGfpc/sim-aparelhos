package Projecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Projecto.Linha;
import Projecto.Tomada;
import Projecto.Ligavel;

public class Instalacao extends Observable {

	private ArrayList<Linha> linhas = new ArrayList<Linha>();
	private static Instalacao instanciaUnica = null;
	private Map<String, Double> potencias = new HashMap<String, Double>();
	private ArrayList<Evento> eventos = new ArrayList<Evento>();

	private Instalacao() {
		super();
		instanciaUnica = this;
	}

	public static Instalacao getInstanciaUnica() { /*
													 * Devolve unica instancia
													 * da instalacao
													 */
		if (instanciaUnica == null) {
			instanciaUnica = new Instalacao();
		}
		return instanciaUnica;

	}

	public void novaLinha(String divisao, int numTomadas){
		Linha novalinha = new Linha(divisao, numTomadas);
		linhas.add(novalinha);
	}

	public void removeTodasAsLinhas() {
		linhas.clear();
	}

	public Linha getLinha(String linhaNome) {
		if(linhaNome == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}
		for (Linha linha : linhas) {
			if (linha != null && linhaNome != null && linha.getDivisao().equals(linhaNome)) {
				return linha;
			}
		}
		return null;
	}

	public Tomada getTomadaLivre(String divisao) {
		if(divisao == null){
			throw new IllegalArgumentException("Nao pode ser nulo");
		}
		for (Linha linha : linhas) {
			if (linha != null && divisao != null && linha.getDivisao().equals(divisao) && linha.getTomadaLivre() != null) {
				return linha.getTomadaLivre();
			}
		}	
		return null;
	}

	public void ligaAparelhoATomadaLivre(String linhaRequirida, Ligavel aparelho) {
		if(linhaRequirida == null || aparelho == null){
			throw new IllegalArgumentException("Os parametros nao podem ser nulos");
		}
		if (linhaRequirida != null && getTomadaLivre(linhaRequirida) != null && aparelho != null) {
			getTomadaLivre(linhaRequirida).setLigado(aparelho);
			aparelho.ligaATomada();
			if(aparelho instanceof Tripla){
				((Tripla) aparelho).adicionaTomadas(getLinha(linhaRequirida));
			}
				
		}

	}

	public double potenciaNaLinha(String linhaRequirida) {
		if(linhaRequirida == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}
		for (Linha linha : linhas) {
			if (linha != null && linhaRequirida != null && linha.getDivisao().equals(linhaRequirida)) {
				double potencia = linha.potenciaNaLinha();
				return potencia;
			}
		}
		return 0;
	}

	public Aparelho getAparelho(String aparelho) {
		if(aparelho == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}
		for (Linha linha : linhas) {
			if (linha != null && aparelho != null && linha.getAparelho(aparelho) != null) {
				return linha.getAparelho(aparelho);
			}
		}
		return null;
	}

	public void init(JSONArray objectos) {
		if(objectos == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}
		String nome = null;
		int nTomadas = 0;
		Instalacao instal = Instalacao.getInstanciaUnica();

		for (int i = 0; i < objectos.size(); i++) {
			JSONObject divisao = (JSONObject) objectos.get(i);

			if (divisao.get("nome") != null) {
				nome = (String) divisao.get("nome");
			}

			if (divisao.get("tomadas") != null) {
				nTomadas = (int) (long) divisao.get("tomadas");
			}

			if (nome != null) {
				instal.novaLinha(nome, nTomadas);
			}
		}
		for (Linha linha : linhas) {
			potencias.put(linha.getDivisao(), potenciaNaLinha(linha.getDivisao()));
		}
		

	}

	public List<Ligavel> lerAparelhos(JSONArray listaAparelhos) {
		
		if(listaAparelhos == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}

		List<Ligavel> listaDeAps = new ArrayList<Ligavel>();
		FabricaDeAparelhos fab = new FabricaDeAparelhos();

		for (int i = 0; i < listaAparelhos.size(); i++) {
			JSONObject obj = (JSONObject) listaAparelhos.get(i);
			Ligavel aparelho = fab.novoAparelho(obj);
			listaDeAps.add(aparelho);

		}

		return listaDeAps;
	}

	public void lerLigacoes(JSONArray listaLigacoes, List<Ligavel> listaAparelhos) {
		
		if(listaAparelhos == null && listaLigacoes == null){
			throw new IllegalArgumentException("Parametros nao podem ser nulos");
		}

		String ligadoA = null;
		String aparelho = null;
		Ligavel aLigar = null;

		if (listaLigacoes != null && listaAparelhos != null) {

			for (int i = 0; i < listaLigacoes.size(); i++) {

				JSONObject obj = (JSONObject) listaLigacoes.get(i);

				if (obj != null) {

					if (obj.get("ligadoA") != null) {
						ligadoA = (String) obj.get("ligadoA");
					}
					if (obj.get("aparelho") != null) {
						aparelho = (String) obj.get("aparelho");
					}

					for (Ligavel ligavel : listaAparelhos) {
						if (ligavel != null && ligavel.getId().equals(aparelho)) {
							aLigar = ligavel;

						}
					}
					if (ligadoA != null && aLigar != null) {
						ligaAparelhoATomadaLivre(ligadoA, aLigar);

					}
				}
			}
		}

	}

	public void lerEventos(JSONArray listaEventos) {
		if(listaEventos == null){
			throw new IllegalArgumentException("Não pode ser nulo");
		}
		String accao = null;
		String id = null;
		String programa = null;
		int tempo = 0;
		int valor = 0;

		for (int i = 0; i < listaEventos.size(); i++) {
			JSONObject obj = (JSONObject) listaEventos.get(i);

			if (obj.get("accao") != null) {
				accao = (String) obj.get("accao");

			}

			if (obj.get("idAparelho") != null) {
				id = (String) obj.get("idAparelho");
			}
			if (obj.get("programa") != null) {
				programa = (String) obj.get("programa");
			}
			if (obj.get("tempo") != null) {
				tempo = (int) (long) obj.get("tempo");
			}
			if (obj.get("valor") != null) {
				valor = (int) (double) obj.get("valor");
			}

			Evento novoEvento = new Evento(tempo, valor, programa, accao, id);
			
			eventos.add(novoEvento);

		}
		

	}

	@Override
	public String toString() {

		String str = "t = " + Relogio.getInstanciaUnica().getTempoAtual() + "\n";
		for (Linha linha : linhas) {
			str += linha.getDivisao() + " " + potenciaNaLinha(linha.getDivisao()) + "W" + "\n";
		}
		return str;
	}

	public void simula(long fim) {
		
		if(fim < 1){
			throw new IllegalArgumentException("Tem que ser maior que 0");
		}

		for (int i = 0; i != fim; i++) {
			Relogio.getInstanciaUnica().tique();
			System.out.println("t = " + i + "\n " + potencias);

			for (Evento evento : eventos) {
				evento.selecEvento();
			}

			for (Map.Entry<String, Double> valores : potencias.entrySet()) {
				if (valores.getValue() != potenciaNaLinha(valores.getKey())) {
					potencias.put(valores.getKey(), potenciaNaLinha(valores.getKey()));
					setChanged();
					notifyObservers(potencias);
				}
			}

		}
		
	}

}
