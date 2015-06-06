package Projecto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * @author ggarc_000
 *
 */
public class Aparelho implements Ligavel {

	/** @author Goncalo Garcia */

	
	

	private String id; 
	private boolean ligado = false;
	private boolean ligadoATomada = false;
	private double potenciaMaxima;
	private double potenciaAtual;
	
	
	public static Aparelho novoAparelho(JSONObject obj) {
		FabricaDeAparelhos fab = new FabricaDeAparelhos();
		return fab.novoAparelho(obj);
	}

	
	/**
	 * Liga o aparelho
	 */
	public void liga() {
		ligado = true;

	}

	/**
	 * Desliga o aparelho
	 */
	public void desliga() {
		ligado = false;

	}
	
	

	/**
	 * 
	 * @return 
	 *			 Devolve true se o aparelho estiver ligado
	 * 			e false se estiver desligado
	 */
	public boolean estaLigado() {
		if (ligado) {
			return true;
		} else {
			return false;
		}
	}

	
	/** 
	 * Indica ao aparelho que esta ligado a uma tomada
	 * 
	 */
	public void ligaATomada() {
		ligadoATomada = true;
	}

	/**
	 * @return
	 * 			Devolve a potencia maxima
	 */
	public double potenciaMaxima() {
		return potenciaMaxima;
	}

	/** 
	 * @return
	 * 			Devolve a potencia atual	
	 */
	public double potenciaAtual() {
		return potenciaAtual;
	}

	/**
	 * @param potenciaMaxima
	 * 					Define a potencia maxima do aparelho
	 */
	public void setPotenciaMaxima(double potenciaMaxima) {
		this.potenciaMaxima = potenciaMaxima;
	}

	/**
	 * @param potenciaAtual
	 * 					Define a potencia atual do aparelho
	 */
	public void setPotenciaAtual(double potenciaAtual) {
		this.potenciaAtual = potenciaAtual;
	}

	/**
	 * Devolve o id do aparelho
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * 			Defineo id do aparelho
	 * 	
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devolve representacao textual do aparelho e potencia actual
	 */
	@Override
	public String toString() {
		return id + " " + potenciaAtual + "\n";
	}

}
