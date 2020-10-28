package critical;

import java.lang.reflect.Method;

public class TestAll {

	public static String execute() throws Exception {
		long startTime = System.currentTimeMillis();

		boolean flag = true;

		String log=null;

		int count = 0;

		while (flag){
			for (int i=1;i<=5;i++){
				Class<?> clazz = Class.forName("critical.numberOf1.CriticalTest2Threads");

				Method method = clazz.getMethod("test2Threads");

				Object result = method.invoke(clazz.newInstance(),null);

				count++;
				String res = result.toString();
				if (res.contains("0's turn do not allow 1")){
				    flag=false;
                }else if (res.contains("1's turn do not allow 0")){
				    flag=false;
                }
				System.out.println(String.valueOf(count) + String.valueOf(flag) + (System.currentTimeMillis() - startTime));

				if (!flag){
					log = (String.valueOf(count) + String.valueOf(flag) + (System.currentTimeMillis() - startTime));
				}
			}
		}
		return log;
	}

	public static void main(String[] args) throws Exception {
		String log = execute();
	}
}