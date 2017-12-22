package antiSpamFilter;

import static org.junit.Assert.*;
import org.junit.*;

class InterfaceTest {

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
	void testInterface() {
		Interface a = new Interface();
		a.open();
		a.refresh();
		assertNotNull(a);

	}

	@Test
	void testTableUpdater() {
		fail("Not yet implemented");
	}

	@Test
	void testRefresh() {
		fail("Not yet implemented");
	}

	@Test
	void testAddFrameContent() {
		fail("Not yet implemented");
	}

}
