package airline.numberOf5;

import java.lang.reflect.Method;

public class TestAll {
	public static String execute() throws Exception {

		long startTime = System.currentTimeMillis();

		boolean flag = true;

		String log=null;

		int count = 0;

		while (flag){
			for (int i=1;i<=5;i++){
				Class<?> clazz = Class.forName("airline.numberOf5.AirlineTickets2ThreadsTest"+i);

				Method method = clazz.getMethod("test2ThreadsFullInvarient");

				Object result = method.invoke(clazz.newInstance(),null);

				count++;

				flag = (Boolean)result;
				System.out.println(String.valueOf(count) + String.valueOf(flag) + String.valueOf(System.currentTimeMillis() - startTime));

				if (!flag){
					log = (String.valueOf(count) + String.valueOf(flag) + String.valueOf(System.currentTimeMillis() - startTime));
				}
			}
		}
		return log;
	}
}