package airline;



import org.junit.Test;

/**
 * @author GN
 * @description
 * @date 2020/10/1
 */

public class AirlineTicketsTest {

    public static void main(String args[]) throws Exception {
        AirlineTicketsTest airlineTest = new AirlineTicketsTest();

        airlineTest.test5ThreadsNotTooMany();

    }

     @Test
    public boolean test5ThreadsNotTooMany() throws Exception {
        makeBookings(49);
        return testSoldInvarient();
    }


    private AirlineTickets airline;

    public void makeBookings(int numTickets) throws Exception {
        airline = new AirlineTickets(numTickets);
        airline.makeBookings();
    }

//    @SuppressWarnings("deprecation")
	public boolean testSoldInvarient() {
        if (airline.numberOfSeatsSold > airline.maximumCapacity) {
            return false;
        }else if (airline.numberOfSeatsSold < airline.maximumCapacity) {
            return false;
        }
        else {
            return true;
        }
    }

    
//    @Test
//	public void test() throws InterruptedException {
//		try {
//			AirlineTicketsTest.main(null);
//		} catch (Exception e) {
//			System.out.println("here");
//			fail();
//		}
//	}

}
