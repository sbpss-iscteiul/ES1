package antiSpamFilter;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

class AntiSpamFilterProblemTest {

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	@Before
	void setUp() throws Exception {
	}

	@After
	void tearDown() throws Exception {
	}

	@Test
	void testAntiSpamFilterProblem() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem("");
		a.setupData();
		assertEquals(335, a.getNumberOfVariables());
	}

	@Test
	void testAntiSpamFilterProblemInteger() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem(50, );
		assertEquals(50, a.getNumberOfVariables());
	}

	@Test
	void testEvaluate() {
		AntiSpamFilterProblem a = new AntiSpamFilterProblem(50);
		a.evaluate(null);
		assertNotNull(a);
	}

}
