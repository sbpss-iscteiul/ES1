package txtreader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeitorTest {
	private Leitor a = new Leitor();
	private String b= "promocao";
	private String c= "nomeProprio";
	private ArrayList<String> d;
	private double e = 0.4;
	private double f = -4.2;
	private ArrayList<Double>  g;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		d = new ArrayList<String>();
		d.add(b);
		d.add(c);
		g = new ArrayList<Double>();
		g.add(e);
		g.add(f);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetRules() {
		a.setRules(d);
		assertEquals(d, a.getRules());
	}

	@Test
	void testSetWeightsArrayListOfDouble() {
		a.setWeights(g);
		assertEquals(g, a.getWeights());
	}

	@Test
	void testLeitor() {
		Leitor teste = new Leitor();
		teste.setRules(d);
		teste.setWeights(g);
		assertEquals(d, teste.getRules());
		assertEquals(g, teste.getWeights());
	}

	@Test
	void testRead_Rules() {
		ArrayList<String>regras=new ArrayList<>();
		regras.add("BAYES_00");
		regras.add("FREEMAIL_FROM");
		regras.add("RDNS_NONE");
		regras.add("FREEMAIL_REPLYTO_END_DIGIT");
		regras.add("MSOE_MID_WRONG_CASE");
		regras.add("DATE_IN_PAST_24_48");
		regras.add("T_LOTS_OF_MONEY");
		regras.add("SPF_HELO_FAIL");
		regras.add("ALL_TRUSTED");
		regras.add("DNS_FROM_RFC_DSN");
		Leitor leitor=new Leitor();
		leitor.read_Rules("C:\\Users\\Ruben\\Desktop\\rules2s");
		leitor.read_Rules("C:\\Users\\Ruben\\Desktop\\rules2.txt");
		for(int i=0; i<11;i++) {
			assertEquals(regras.get(i), leitor.getRules().get(i));
		}	
	}

	@Test
	void testWrite_Rules() {
		Leitor leitor=new Leitor();
		leitor.read_Rules("C:\\Users\\Ruben\\Desktop\\rules2.txt");
		leitor.write_Rules("C:\\Users\\Ruben\\Desktop\\rules4.txt");
		
	}

	@Test
	void testReset_Rules() {
		Leitor leitor=new Leitor();
		leitor.read_Rules("C:\\Users\\Ruben\\Desktop\\rules5.txt");
		leitor.reset_Rules("C:\\Users\\Ruben\\Desktop\\rules5.txt");
	}

	@Test
	void testRead_Email() {
		Leitor leitor=new Leitor();
		leitor.read_Email("C:\\Users\\Ruben\\Desktop\\rules4.txt");
		leitor.read_Email("C:\\Users\\Ruben\\Desktop\\rules4");
		
	}

	@Test
	void testGetRules() {
		a.setRules(d);
		assertEquals(d, a.getRules());
	}

	@Test
	void testGetSpam() {
		Leitor leitor = new Leitor();
		Email teste = new Email("ola");
		leitor.getSpam().add(teste);
		assertEquals(leitor.getSpam().get(leitor.getSpam().size()-1), teste);
	}

	@Test
	void testGetHam() {
		Leitor leitor = new Leitor();
		Email teste = new Email("ola");
		leitor.getHam().add(teste);
		assertEquals(leitor.getHam().get(leitor.getHam().size()-1), teste);
	}

	@Test
	void testGetWeights() {
		a.setWeights(g);
		assertEquals(g, a.getWeights());
	}

	@Test
	void testSetWeightsIntDouble() {
		Leitor teste = new Leitor();
		ArrayList<Double> weights = new ArrayList<Double>();
		weights.add(0.5);
		teste.setWeights(weights);
		teste.setWeights(0, 0.7);
		if(teste.getWeights().size()!=0)
			assertEquals(weights.get(0),teste.getWeights().get(0) );
	}

	@Test
	void testGetnRegras() {
		Leitor leitor = new Leitor();
		leitor.read_Rules("C:\\Users\\Ruben\\Desktop\\rules.cf");
		assertEquals(335, leitor.getnRegras());	
	}

	@Test
	void testconfig() {
		Leitor leitor = new Leitor();
		leitor.config(3);
	}
}


