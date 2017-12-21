package antiSpamFilter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AntiSpamFilterProblemTest {

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
	void testAntiSpamFilterProblem() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem();
		a.setupData();
		assertEquals(335, a.getNumberOfVariables());
	}

	@Test
	void testAntiSpamFilterProblemInteger() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem(50);
		assertEquals(50, a.getNumberOfVariables());
	}

	@Test
	void testEvaluate() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem(50);
		a.evaluate(null);
		assertNotNull(a);
	}

}
