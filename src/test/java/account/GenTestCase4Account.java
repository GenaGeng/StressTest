package account;

import log.WriteResult2Excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.io.File.separator;

/**
 * @author GN
 * @description
 * @date 2020/10/9
 */
public class GenTestCase4Account {
    /*测试用例路径*/
    private String home;

    /*test case name list*/
    private List<String> testcasenames;

    private static int[] THREADNUM = new int[20];

    private static int[] TESTCASENUM = {5,25,125,625,3125};

    private static int AMOUNT ;

    private  static int DMONEY;

    private static int WMONEY;

    private static String ACCOUNTNAME = "\"The Account\"";

    public  GenTestCase4Account(){
        home = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java\\account";
        testcasenames= new ArrayList<>();
    }
    public void generateTestCase(int seed){
        Random random =new Random(seed);

            for (int i = 1; i <= TESTCASENUM[2]; i++) {

                AMOUNT = random.nextInt(10000)+1;

                DMONEY = random.nextInt(10000);

                WMONEY = random.nextInt(AMOUNT);

                int result = AMOUNT + DMONEY * 2 - WMONEY * 2;
                String content = contentOfTC(2, result,i);

                String TCName = "Account" + "2ThreadDepositandWithdrawTest" + String.valueOf(i);


                testcasenames.add(TCName + ".class");

                writeTC(TCName, content,String.valueOf(TESTCASENUM[2]));
            }

    }

    /**
     * 测试用例内容
     * @param thraednum
     * @return
     */
    private String contentOfTC(int thraednum,int result,int nth){
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("package account.numberOf" + TESTCASENUM[2] + ";" + "\n\n");
        stringBuffer.append("import account.Account;" + "\n");
        stringBuffer.append("import junit.framework.TestCase;" + "\n\n");
        stringBuffer.append("import org.junit.Before;" + "\n\n");
        stringBuffer.append("import org.junit.After;" + "\n\n");
        stringBuffer.append("import org.junit.Test;" + "\n\n");
        String TCName = "Account" + thraednum + "ThreadDepositandWithdrawTest"+nth;
        stringBuffer.append("public class " + TCName + " extends TestCase{" + "\n\n");
        stringBuffer.append("\t" + TCName + " accountTest;" + "\n\n");

        stringBuffer.append("\t" + "@Before" + "\n");
        stringBuffer.append("\t"+"public void setUp(){" + "\n\n");
        stringBuffer.append("\t\t"+"accountTest = new " + TCName + "();" +"\n");
        stringBuffer.append("\t" +"}" + "\n\n");

        stringBuffer.append("\t" + "@After" + "\n");
        stringBuffer.append("\t" + "public void tearDown() {" + "\n");
        stringBuffer.append("\t\t" + "accountTest = null;" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "@Test" + "\n");
        stringBuffer.append("\t" + "public void runTest() throws Exception {" + "\n");
        String testmethod = "test" + String.valueOf(thraednum) + "ThreadDepositAndWithdrawAndCheckInvariant";
        stringBuffer.append("\t\t"+"accountTest." + testmethod + "();" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "@Test" + "\n");
        stringBuffer.append("\t" + "public boolean "+testmethod+"() throws Exception {" + "\n");
        stringBuffer.append("\t\t" + "return performDepositsAndWithdrawalsAndCheckInvariant(" + String.valueOf(thraednum) +");" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "private class DepositThread implements Runnable {" + "\n");
        stringBuffer.append("\t\t" + "private final Account account;" + "\n\n");
        stringBuffer.append("\t\t" + "private final double money;" + "\n\n");
        stringBuffer.append("\t\t" + "public DepositThread(Account account, double money) {" + "\n\n");
        stringBuffer.append("\t\t\t" + "this.account = account;" + "\n\n");
        stringBuffer.append("\t\t\t" + "this.money = money;" + "\n\n");
        stringBuffer.append("\t\t" + "}" +"\n\n");
        stringBuffer.append("\t\t" + "@Override" + "\n");
        stringBuffer.append("\t\t" + "public void run() {" + "\n");
        stringBuffer.append("\t\t\t" + "account.depsite(money);" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n");
        stringBuffer.append("\t" + "}" +"\n\n");

        stringBuffer.append("\t" + "private class WithdrawThread implements Runnable {" + "\n");
        stringBuffer.append("\t\t" + "private final Account account;" + "\n\n");
        stringBuffer.append("\t\t" + "private final double money;" + "\n\n");
        stringBuffer.append("\t\t" + "public WithdrawThread(Account account, double money) {" + "\n");
        stringBuffer.append("\t\t\t" + "this.account = account;" + "\n\n");
        stringBuffer.append("\t\t\t" + "this.money = money;" + "\n\n");
        stringBuffer.append("\t\t" + "}" +"\n\n");
        stringBuffer.append("\t\t" + "@Override" + "\n");
        stringBuffer.append("\t\t" + "public void run() {" + "\n");
        stringBuffer.append("\t\t\t" + "account.withdraw(money);" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n");
        stringBuffer.append("\t" + "}" +"\n\n");

        stringBuffer.append("\t" + "@Test" + "\n");
        stringBuffer.append("\t" + "public boolean performDepositsAndWithdrawalsAndCheckInvariant(int numThreads) throws Exception {" + "\n");
        stringBuffer.append("\t\t" + "Account account = new Account(" + ACCOUNTNAME + "," + String.valueOf(AMOUNT) + ");" + "\n");
        stringBuffer.append("\t\t" + "Thread[] withdrawalThreads = new Thread[numThreads];" + "\n\n");
        stringBuffer.append("\t\t" + "Thread[] depositThreads = new Thread[numThreads];" + "\n\n");
        stringBuffer.append("\t\t" + "for (int i = 0; i < numThreads; i++) {" + "\n");
        stringBuffer.append("\t\t\t" + "depositThreads[i] = new Thread(new DepositThread(account," + String.valueOf(DMONEY) +"));" + "\n");
        stringBuffer.append("\t\t\t" + "withdrawalThreads[i] = new Thread(new WithdrawThread(account," + String.valueOf(WMONEY) + "));" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n\n");
        stringBuffer.append("\t\t" + "for (int i = 0; i < numThreads; i++) {" + "\n");
        stringBuffer.append("\t\t\t" + "depositThreads[i].start();" + "\n\n");
        stringBuffer.append("\t\t\t" + "withdrawalThreads[i].start();" + "\n\n");
        stringBuffer.append("\t\t" + "}" + "\n\n");
        stringBuffer.append("\t\t" + "for (int i = 0; i < numThreads; i++) {" + "\n");
        stringBuffer.append("\t\t\t" + "depositThreads[i].join();" + "\n\n");
        stringBuffer.append("\t\t\t" + "withdrawalThreads[i].join();" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n\n");
        stringBuffer.append("\t\t" + "if (account.amount!=" + String.valueOf(result) + "){" + "\n");
        stringBuffer.append("\t\t\t" +"return false;"+"\n");
        stringBuffer.append("\t\t" + "}else {" + "\n");
        stringBuffer.append("\t\t\t" + "return true;" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n");
        stringBuffer.append("\t" + "}" + "\n");
        stringBuffer.append("}");

        return stringBuffer.toString();
    }

    private void writeTC(String name,String content,String nth){

            String filePath = this.home + separator + "numberOf" + nth + separator + name + ".java";
            File file = new File(filePath);

            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(file);
                printWriter.write(content);
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }

    private void generateTestAll(){
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("package account.numberOf" + TESTCASENUM[2] +";" + "\n\n");
        stringBuffer.append("import java.lang.reflect.Method;" + "\n\n");
        stringBuffer.append("public class TestAll {" + "\n\n");

        stringBuffer.append("\t" + "public static String execute() throws Exception {" + "\n");
        stringBuffer.append("\t\t" + "long startTime = System.currentTimeMillis();" + "\n\n");
        stringBuffer.append("\t\t" + "boolean flag = true;"+"\n\n");
        stringBuffer.append("\t\t" + "String log=null;" + "\n\n");
        stringBuffer.append("\t\t" + "int count = 0;" + "\n\n");
        stringBuffer.append("\t\t" + "while (flag){"+"\n");
        stringBuffer.append("\t\t\t" + "for (int i=1;i<=" + TESTCASENUM[2] + ";i++){" + "\n");
        stringBuffer.append("\t\t\t\t" + "Class<?> clazz = Class.forName(\"account.numberOf" + TESTCASENUM[2] + ".Account2ThreadDepositandWithdrawTest\"+i);" + "\n\n");
        stringBuffer.append("\t\t\t\t" + "Method method = clazz.getMethod(\"test2ThreadDepositAndWithdrawAndCheckInvariant\");"+"\n\n");
        stringBuffer.append("\t\t\t\t" + "Object result = method.invoke(clazz.newInstance(),null);" + "\n\n");
        stringBuffer.append("\t\t\t\t" + "count++;" + "\n\n");
        stringBuffer.append("\t\t\t\t" + "flag = (Boolean)result;" + "\n");
        stringBuffer.append("\t\t\t\t" + "System.out.println(String.valueOf(count) + String.valueOf(flag) + String.valueOf(System.currentTimeMillis() - startTime));" + "\n\n");
        stringBuffer.append("\t\t\t\t" + "if (!flag){" + "\n");
        stringBuffer.append("\t\t\t\t\t" + "log = (String.valueOf(count) + String.valueOf(flag) + String.valueOf(System.currentTimeMillis() - startTime));" +"\n");
        stringBuffer.append("\t\t\t\t" + "}" + "\n");
        stringBuffer.append("\t\t\t" + "}" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n");
        stringBuffer.append("\t\t" + "return log;" + "\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("}");

        writeTC("TestAll", stringBuffer.toString(),String.valueOf(TESTCASENUM[2]));
    }

    public static int  generate(int seed){
        long startTime = System.currentTimeMillis();
        GenTestCase4Account gtc = new GenTestCase4Account();
        gtc.generateTestCase(seed);
        gtc.generateTestAll();
        long endTime = System.currentTimeMillis();
        return (int)(endTime-startTime);
    }

}
