package hw2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimulatorTest {
	@Test
    public void testSimulator() throws Exception {
		SimulatorTestDriver driver = new SimulatorTestDriver();
		driver.createSimulator("simi");
		driver.addChannel("simi", "A1", 7);
		driver.addChannel("simi", "A2", 7);
		driver.addParticipant("simi", "B1", 0.2);
		driver.addParticipant("simi", "B2", 0.1);
		driver.addParticipant("simi", "B3", 0.4);
		driver.addEdge("simi", "B1", "A1", "11");
		driver.addEdge("simi", "A1", "B2", "12");
		driver.addEdge("simi", "B2", "A2", "22");
		driver.addEdge("simi", "A2", "B3", "23");
		driver.sendTransaction("simi", "A1", new Transaction("B2", 5));
		driver.sendTransaction("simi", "A2", new Transaction("B3", 1));
		assertEquals("wrong contents in pipe", "5.0", driver.listContents("simi", "A1").toString());
        driver.simulate("simi");
        //double t=driver.getParticipantBalace("simi", "B2");
        assertEquals("wrong contents in pipe", "", driver.listContents("simi", "A1").toString());
        assertEquals("wrong BALANCE", "5.0", String.valueOf(driver.getParticipantBalace("simi", "B2")));
        driver.simulate("simi");
        assertEquals("wrong BALANCE", "1.0", String.valueOf(driver.getParticipantBalace("simi", "B3")));
        driver.printAllEdges("simi");
    }

	@Test
	public void testA() throws Exception {
		
		SimulatorTestDriver driver = new SimulatorTestDriver();
		
		//configure simulations
		driver.createSimulator("sim1");
		driver.addChannel("sim1", "c1", 33);
		driver.addChannel("sim1", "c2", 33);
		driver.addParticipant("sim1", "p1", 2);
		driver.addParticipant("sim1", "p2", 3);
		driver.addParticipant("sim1", "p3", 5);
		driver.addEdge("sim1", "p1", "c1", "11");
		driver.addEdge("sim1", "c1", "p2", "12");
		driver.addEdge("sim1", "p2", "c2", "22");
		driver.addEdge("sim1", "c2", "p3", "23");
		
		driver.createSimulator("sim2");
		driver.addChannel("sim2", "c", 44);
		driver.addParticipant("sim2", "p1", 2);
		driver.addParticipant("sim2", "p2", 3);
		driver.addEdge("sim2", "p1", "c", "1");
		driver.addEdge("sim2", "c", "p2", "2");
		
		// create transaction
		Transaction t = new Transaction("p3", 10);
		driver.sendTransaction("sim1", "c1", t);
		driver.sendTransaction("sim1", "c2", t);
		
		// init validations
		assertEquals(driver.getParticipantBalace("sim1", "p1"), 0, 0.001);
		assertEquals(driver.getParticipantBalace("sim1", "p2"), 0, 0.001);
		assertEquals(driver.getParticipantBalace("sim1", "p3"), 0, 0.001);
		assertEquals(driver.listContents("sim1", "c1"), "");
		assertEquals(driver.listContents("sim1", "c2"), "");
		assertEquals(driver.listContents("sim1", "c1"), "10.0");
		assertEquals(driver.listContents("sim1", "c2"), "10.0");

		//simulate
		driver.simulate("sim1");

		// validate simulation
		assertEquals(0.0, driver.getParticipantBalace("sim1", "p1"), 0.001);
		assertEquals(3.0, driver.getParticipantBalace("sim1", "p2"), 0.001);
		assertEquals(10.0, driver.getParticipantBalace("sim1", "p3"), 0.001);
		assertEquals("", driver.listContents("sim1", "c1"));
		assertEquals("7.0", driver.listContents("sim1", "c2"));
		
		//simulate
		driver.simulate("sim1");

		// validate simulation
		assertEquals(0, driver.getParticipantBalace("sim1", "p1"), 0.001);
		assertEquals(3, driver.getParticipantBalace("sim1", "p2"), 0.001);
		assertEquals(17, driver.getParticipantBalace("sim1", "p3"), 0.001);
		assertEquals(driver.listContents("sim1", "c1"),"");
		assertEquals(driver.listContents("sim1", "c2"),"");
	}
}