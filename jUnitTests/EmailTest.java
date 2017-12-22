package txtreader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailTest {
	private String a = "nomeEmail";
	private Rule b= new Rule("promocao", 8);
	private Rule c= new Rule("nomeProprio", 12);
	private ArrayList<Rule> d;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		d = new ArrayList<Rule>();
		d.add(b);
		d.add(c);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmail() {
		Email teste = new Email(a);
		teste.setRules(d);
		assertEquals(a, teste.getEmailName());
		assertEquals(d, teste.getRules());
	}

	@Test
	void testAdd_Rules() {
		Email teste = new Email(a);
		teste.add_Rules(b);
		assertEquals(b, teste.getRules().get(0));
	}

	@Test
	void testGetEmailName() {
		Email teste = new Email(a);
		assertEquals(a, teste.getEmailName());
	}
	

	@Test
	void testGetRules() {
		Email teste = new Email(a);
		teste.setRules(d);
		assertEquals(d, teste.getRules());

	}

	@Test
	void testToString() {
		Email teste = new Email(a);
		assertEquals("Email name:"+a, teste.toString());
	}

}
