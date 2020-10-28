package account.numberOf1;

import java.lang.reflect.Method;

public class TestAll {

//	private static int count;
	public static String execute() throws Exception {
		long startTime = System.currentTimeMillis();
		boolean flag = true;
		String log=null;
		int count = 0;

		while (flag){
			for (int i=1;i<=1;i++){
				Class<?> clazz = Class.forName("account.numberOf1.Account2ThreadDepositandWithdrawTest"+i);

				Method method = clazz.getMethod("test2ThreadDepositAndWithdrawAndCheckInvariant");

				Object result = method.invoke(clazz.newInstance(),null);

				count++;
				flag = (Boolean)result;
//				if (!flag){
				log = (String.valueOf(count) + String.valueOf(flag) + (System.currentTimeMillis() - startTime));
				System.out.println(log);
//				}
			}
		}
		return log;
	}
}