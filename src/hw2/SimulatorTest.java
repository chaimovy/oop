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
		 assertEquals("wrong contents in pipe", "5.0 ", driver.listContents("simi", "A1").toString());
        driver.simulate("simi");
        double t=driver.getParticipantBalace("simi", "B2");
        assertEquals("wrong contents in pipe", "", driver.listContents("simi", "A1").toString());
        assertEquals("wrong BALANCE", "5.0", String.valueOf(driver.getParticipantBalace("simi", "B2")));
        driver.simulate("simi");
        assertEquals("wrong BALANCE", "1.0", c(driver.getParticipantBalace("simi", "B3")));
    }
}