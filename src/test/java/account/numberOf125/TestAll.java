package account.numberOf125;

import java.lang.reflect.Method;

public class TestAll {

	public static String execute() throws Exception {
		long startTime = System.currentTimeMillis();

		boolean flag = true;

		String log=null;

		int count = 0;

		while (flag){
			for (int i=1;i<=125;i++){
				Class<?> clazz = Class.forName("account.numberOf125.Account2ThreadDepositandWithdrawTest"+i);

				Method method = clazz.getMethod("test2ThreadDepositAndWithdrawAndCheckInvariant");

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