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
		fail("Not yet implemented");
	}

	@Test
	void testWrite_Rules() {
		fail("Not yet implemented");
	}

	@Test
	void testReset_Rules() {
		fail("Not yet implemented");
	}

	@Test
	void testRead_Email() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRules() {
		a.setRules(d);
		assertEquals(d, a.getRules());
	}

	@Test
	void testGetSpam() {
		Email teste = new Email(b);
		a.getSpam().add(teste);
		assertEquals(a.getSpam(), teste);
	
	}

	@Test
	void testGetHam() {
		Email teste = new Email(b);
		a.getHam().add(teste);
		assertEquals(a.getHam(), teste);
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
		weights.set(0, e);
		weights.set(1, f);
		teste.setWeights(weights);
		assertEquals(g, teste.getWeights());
	}

	@Test
	void testGetnRegras() {
		fail("Not yet implemented");
	}

}
