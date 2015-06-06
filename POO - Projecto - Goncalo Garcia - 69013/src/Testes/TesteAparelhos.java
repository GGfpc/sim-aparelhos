package Testes;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Projecto.Aparelho;
import Projecto.Instalacao;
import Projecto.Ligavel;
import Projecto.AparelhoPotenciaFixa;
import Projecto.AparelhoPotenciaVariavel;
import Projecto.Lampada;
import Projecto.MicroOndas;
import Projecto.Torradeira;

public class TesteAparelhos {
	
	private Instalacao instalacao;
	
	@BeforeClass
	public static void preparacaoInicial() {
	}

	@Before
	public void preparacaoDeTeste() {
		instalacao = Instalacao.getInstanciaUnica();
		instalacao.removeTodasAsLinhas();
		instalacao.novaLinha("cozinha", 10); // cozinha, 10 tomadas
		 
	}
	
	@Test
	public void testeInstalacaoAparelhos() {
		Lampada lamp1 = new Lampada("lampada1", 50);
		instalacao.ligaAparelhoATomadaLivre("cozinha", lamp1);
		lamp1.liga();
		assertEquals(50.0, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		Torradeira torradeira = new Torradeira("torradeira1", 100);
		instalacao.ligaAparelhoATomadaLivre("cozinha", torradeira);
		torradeira.liga();
		assertEquals(150, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		MicroOndas m = new MicroOndas("micro1", 900);
		instalacao.ligaAparelhoATomadaLivre("cozinha", m);
		assertEquals(150, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		m.aumenta(500);
		assertEquals(150, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		m.liga();
		assertEquals(650, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		m.aumenta(500); // só 400 efetivo
		assertEquals(1050, instalacao.potenciaNaLinha("cozinha"), 0.0000001);
		
	}
	
	@Test
	public void testeHierarquiaAparelhos() {
		Lampada lamp1 = new Lampada("lampada1", 50);		
		Torradeira torradeira = new Torradeira("torradeira1", 100);
		AparelhoPotenciaFixa a = lamp1;
		a = torradeira;
		MicroOndas m = new MicroOndas("micro1", 900);
		AparelhoPotenciaVariavel b = m;
		Set<Aparelho> aparelhos = new HashSet<Aparelho>();
		aparelhos.add(lamp1);
		aparelhos.add(torradeira);
		aparelhos.add(b);
		Set<Ligavel> ligaveisATomadas = new HashSet<Ligavel>();
		ligaveisATomadas.add(lamp1);
		ligaveisATomadas.add(torradeira);
		ligaveisATomadas.add(b);
		
	}
	
}
