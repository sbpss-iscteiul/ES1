package txtreader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuleTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRule() {
		Rule a = new Rule("capsLock", 1);
		assertEquals(1, a.getId());
		assertEquals("capsLock", a.getName());
		
	}

	@Test
	void testGetId() {
		Rule a = new Rule("capsLock", 1);
		assertEquals(1, a.getId());
	}

	@Test
	void testGetName() {
		Rule a = new Rule("capsLock", 1);
		assertEquals("capsLock", a.getName());
	}

	@Test
	void testToString() {
		Rule a = new Rule("capsLock", 1);
		assertEquals("Nome:"+"capsLock"+" Id:"+1,a.toString());
	}

}
