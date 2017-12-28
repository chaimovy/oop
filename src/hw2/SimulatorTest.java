package hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    }

	@Test
	public void test1() throws Exception {
		
		SimulatorTestDriver driver = new SimulatorTestDriver();

		assertTrue(driver != null);

		driver.createSimulator("sim1");
		driver.createSimulator("sim2");

		// delta
		double  delta =  0.001;

		// sim1
		
		// add channels
		driver.addChannel("sim1", "ch1", 100);
		driver.addChannel("sim1", "ch2", 100);


		// add participants
		driver.addParticipant("sim1", "amit", 3);
		driver.addParticipant("sim1", "matan", 4);
		driver.addParticipant("sim1", "shir", 0.9);

		
		// sim2
		// add channels
		driver.addChannel("sim2", "ch1", 100);
		driver.addChannel("sim2", "ch2", 100);

		// add participants
		driver.addParticipant("sim2", "amit", 3);
		driver.addParticipant("sim2", "matan", 4);
				
		// connect channels and participants
		
		driver.addEdge("sim1", "amit", "ch1", "ato1");
		driver.addEdge("sim1", "ch1", "matan", "1tom");
		driver.addEdge("sim1", "matan", "ch2", "mto2");
		driver.addEdge("sim1", "ch2", "shir", "2tos");

		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") ,0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan"), 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir"), 0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");

		
		// create transactions
		Transaction t1 = new Transaction("shir", 10);
		Transaction t2 = new Transaction("shir", 10);

		// send transaction
		driver.sendTransaction("sim1", "ch1", t1);
		driver.sendTransaction("sim1", "ch2", t2);
		
		assertEquals(driver.listContents("sim1", "ch1"),"10.0");
		assertEquals(driver.listContents("sim1", "ch2"),"10.0");

		//simulate
		driver.simulate("sim1");

		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 0.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 4.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 10.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"6.0");
		
		//simulate
		driver.simulate("sim1");
		
		//check values
		
		//check balance
		
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 4,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 16,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");

	}
	
	@Test
	public void test2() throws Exception {
		
		SimulatorTestDriver driver = new SimulatorTestDriver();

		assertTrue(driver != null);

		driver.createSimulator("sim1");

		// delta
		double  delta =  0.001;

		// sim1
		
		// add channels
		driver.addChannel("sim1", "ch1", 1);
		driver.addChannel("sim1", "ch2", 1);


		// add participants
		driver.addParticipant("sim1", "amit", 1);
		driver.addParticipant("sim1", "matan", 4);
		driver.addParticipant("sim1", "shir", 1);

				
		// connect channels and participants
		
		driver.addEdge("sim1", "amit", "ch1", "ato1");
		driver.addEdge("sim1", "ch1", "matan", "1tom");
		driver.addEdge("sim1", "matan", "ch2", "mto2");
		driver.addEdge("sim1", "ch2", "shir", "2tos");

		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") ,0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan"), 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir"), 0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");

		
		// create transactions
		Transaction t1 = new Transaction("shir", 1);
		Transaction t2 = new Transaction("shir", 10);

		// send transaction
		driver.sendTransaction("sim1", "ch1", t1);
		driver.sendTransaction("sim1", "ch2", t2);
		
		assertEquals(driver.listContents("sim1", "ch1"),"1.0");
		assertEquals(driver.listContents("sim1", "ch2"),"");

		//simulate
		driver.simulate("sim1");
		
		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 0.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 4.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 0.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		
		//simulate
		driver.simulate("sim1");
		
		//check values
		
		//check balance
		
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 4,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 0.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");

	}
	
	@Test
	public void test3() throws Exception {
		
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		// delta
		double  delta =  0.001;

		// sim1
		
		// add channels
		driver.addChannel("sim1", "ch1", 100);
		driver.addChannel("sim1", "ch2", 100);
		driver.addChannel("sim1", "ch3", 100);


		// add participants
		driver.addParticipant("sim1", "amit", 5);
		driver.addParticipant("sim1", "matan", 3);
		driver.addParticipant("sim1", "shir", 5);

				
		// connect channels and participants
		
		driver.addEdge("sim1", "amit", "ch1", "ac");
		driver.addEdge("sim1", "matan", "ch1", "mc");
		driver.addEdge("sim1", "ch1", "shir", "1s");
		driver.addEdge("sim1", "ch3", "amit", "2a");
		driver.addEdge("sim1", "ch2", "matan", "3s");


		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") ,0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan"), 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir"), 0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");

		
		// create transactions
		Transaction t1 = new Transaction("shir", 10);
		Transaction t2 = new Transaction("shir", 10);

		// send transaction
		driver.sendTransaction("sim1", "ch2", t1);
		driver.sendTransaction("sim1", "ch3", t2);
		
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"10.0");
		assertEquals(driver.listContents("sim1", "ch3"),"10.0");


		//simulate
		driver.simulate("sim1");
		
		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 5.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 3.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 0.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"7.0 5.0");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");

		
		//simulate
		driver.simulate("sim1");
		//check values
		
		//check balance
		
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 5,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 3,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 12,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");


	}
	@Test
	public void test4() throws Exception {
		
		SimulatorTestDriver driver = new SimulatorTestDriver();

		driver.createSimulator("sim1");

		// delta
		double  delta =  0.001;

		// sim1
		
		// add channels
		driver.addChannel("sim1", "ch1", 100);
		driver.addChannel("sim1", "ch2", 100);
		driver.addChannel("sim1", "ch3", 100);
		driver.addChannel("sim1", "ch4", 100);



		// add participants
		driver.addParticipant("sim1", "amit", 5);
		driver.addParticipant("sim1", "matan", 5);
		driver.addParticipant("sim1", "shir", 5);
		driver.addParticipant("sim1", "natali", 5);


				
		// connect channels and participants
		
		driver.addEdge("sim1", "amit", "ch1", "ac");
		driver.addEdge("sim1", "matan", "ch1", "mc");
		driver.addEdge("sim1", "ch1", "shir", "1s");
		driver.addEdge("sim1", "ch3", "amit", "2a");
		driver.addEdge("sim1", "ch2", "matan", "3s");
		driver.addEdge("sim1", "shir", "ch4", "s4");
		driver.addEdge("sim1", "ch4", "natali", "4n");



		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") ,0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan"), 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir"), 0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "natali"), 0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");
		assertEquals(driver.listContents("sim1", "ch4"),"");

		
		// create transactions
		Transaction t1 = new Transaction("shir", 20);
		Transaction t2 = new Transaction("natali", 20);

		// send transaction
		driver.sendTransaction("sim1", "ch2", t1);
		driver.sendTransaction("sim1", "ch3", t2);
		
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"20.0");
		assertEquals(driver.listContents("sim1", "ch3"),"20.0");


		//simulate
		driver.simulate("sim1");
		
		
		//check balance
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 5.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 5.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 0.0,delta);
		assertEquals(driver.getParticipantBalace("sim1", "natali") , 0.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"15.0 15.0");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");
		assertEquals(driver.listContents("sim1", "ch4"),"");


		
		//simulate
		driver.simulate("sim1");
		
		//check values
		
		//check balance
		
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 5,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 5,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 20,delta);
		assertEquals(driver.getParticipantBalace("sim1", "natali") , 0.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");
		assertEquals(driver.listContents("sim1", "ch4"),"10.0");

		//simulate
		driver.simulate("sim1");
		
		//check values
		
		//check balance
		
		assertEquals(driver.getParticipantBalace("sim1", "amit") , 5,delta);
		assertEquals(driver.getParticipantBalace("sim1", "matan") , 5,delta);
		assertEquals(driver.getParticipantBalace("sim1", "shir") , 20,delta);
		assertEquals(driver.getParticipantBalace("sim1", "natali") , 10.0,delta);

		//check contents
		assertEquals(driver.listContents("sim1", "ch1"),"");
		assertEquals(driver.listContents("sim1", "ch2"),"");
		assertEquals(driver.listContents("sim1", "ch3"),"");
		assertEquals(driver.listContents("sim1", "ch4"),"");
		
	}
	
}