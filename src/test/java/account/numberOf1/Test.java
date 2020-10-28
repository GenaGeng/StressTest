package account.numberOf1;

/**
 * @author GN
 * @description
 * @date 2020/10/24
 */
public class Test {
    public static void main(String[] args) throws Exception {
        for (int i=1;i<=30;i++) {
            Class<?> clazz = Class.forName("account.numberOf1.TestAll");
            Object result = clazz.getMethod("execute").invoke(clazz.newInstance(), null);
            System.out.println(clazz.getMethod("execute").invoke(clazz.newInstance(), null));
            String res = (String)result;
//            if (res.contains("false")){
//                int index = res.indexOf("false");
//                ET.add(Integer.valueOf(res.substring(index + 5)));
//                NUM.add(Integer.valueOf(res.substring(0,index)));
//            }
            System.out.println(res);
        }
    }
}
