package airline.numberOf5;

import airline.AirlineTickets;
import junit.framework.TestCase;

import org.junit.Before;

import org.junit.After;

import org.junit.Test;

public class AirlineTickets2ThreadsTest4 extends TestCase {

	AirlineTickets2ThreadsTest4 airlineticketsTest;

	private AirlineTickets airline;

	@Before
	public void setUp() {

		airlineticketsTest = new AirlineTickets2ThreadsTest4();

	}
	@After
	public void tearDown() {
		airlineticketsTest = null;
	}
	@Test
	public void runTest() throws Exception {

		airlineticketsTest.test2ThreadsFullInvarient();

	}
	public boolean test2ThreadsFullInvarient() throws Exception {

		makeBookings(2);

		return testSoldInvarient();
	}

	private void makeBookings(int numTickets) throws Exception {

		airline = new AirlineTickets(numTickets);

		airline.makeBookings();
	}

	public boolean testSoldInvarient() {

		if (airline.numberOfSeatsSold > airline.maximumCapacity) {

			return false;

		}else if (airline.numberOfSeatsSold < airline.maximumCapacity) {

			return false;

		} else {

			return true;
		}
	}
}