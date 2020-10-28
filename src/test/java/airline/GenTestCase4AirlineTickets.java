package airline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

/**
 * @author GN
 * @description
 * @date 2020/10/9
 */
public class GenTestCase4AirlineTickets {
    /*test case path*/
    private String home;

    /*test case name list*/
    private List<String> testcasenames;

//    private static int TICKETNUM = new Random().nextInt(2995)+5;

    private int[] THREADNUM = new int[20];
    private int[] TESTCASENUM = {5,25,125,625,3125};

    public GenTestCase4AirlineTickets(){
        home =  System.getProperty("user.dir") + separator + "src\\test\\java\\airline";
        testcasenames = new ArrayList<>();
    }

    public void generateTestCase(int seed){
        for (int i=1;i<=TESTCASENUM[0];i++){

            String content = contentOfTestCase(2,i);

            String TCName = "AirlineTickets2ThreadsTest" + String.valueOf(i);

            testcasenames.add(TCName + ".class");
            writeTC(TCName,content,String.valueOf(TESTCASENUM[0]));
        }
    }

    /**
     *
     * @return
     */
    private String contentOfTestCase(int threadNum,int nth){

        StringBuffer stringBuffer = new StringBuffer(10);

        stringBuffer.append("package airline.numberOf" + TESTCASENUM[0] +";" + "\n\n");
        stringBuffer.append("import airline.AirlineTickets;" + "\n");
        stringBuffer.append("import junit.framework.TestCase;" + "\n\n");
        stringBuffer.append("import org.junit.Before;" + "\n\n");
        stringBuffer.append("import org.junit.After;" + "\n\n");
        stringBuffer.append("import org.junit.Test;" + "\n\n");

        String TCNmae = "AirlineTickets" + String.valueOf(threadNum) + "ThreadsTest" + nth;
        stringBuffer.append("public class " + TCNmae + " extends TestCase {" + "\n\n");
        stringBuffer.append("\t" + TCNmae + " airlineticketsTest;" + "\n\n");
        stringBuffer.append("\t" + "private AirlineTickets airline;" + "\n\n");

        stringBuffer.append("\t" + "@Before" + "\n");
        stringBuffer.append("\t" + "public void setUp() {" + "\n\n");
        stringBuffer.append("\t\t" + "airlineticketsTest = new " + TCNmae +"();" + "\n\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "@After" + "\n");
        stringBuffer.append("\t" + "public void tearDown() {" + "\n");
        stringBuffer.append("\t\t" + "airlineticketsTest = null;" + "\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "@Test" + "\n");
        stringBuffer.append("\t" + "public void runTest() throws Exception {" + "\n\n");
        String testmethod = "test" + String.valueOf(threadNum) + "ThreadsFullInvarient";
        stringBuffer.append("\t\t" + "airlineticketsTest." + testmethod + "();" + "\n\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "public boolean " + testmethod + "() throws Exception {" + "\n\n");
        stringBuffer.append("\t\t" + "makeBookings(" + String.valueOf(threadNum) + ");"+"\n\n");
        stringBuffer.append("\t\t" + "return testSoldInvarient();" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "private void makeBookings(int numTickets) throws Exception {" + "\n\n");
        stringBuffer.append("\t\t" + "airline = new AirlineTickets(numTickets);" + "\n\n");
        stringBuffer.append("\t\t" + "airline.makeBookings();" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "public boolean testSoldInvarient() {" + "\n\n");
        stringBuffer.append("\t\t" + "if (airline.numberOfSeatsSold > airline.maximumCapacity) {" + "\n\n");
        stringBuffer.append("\t\t\t" + "return false;" + "\n\n");
        stringBuffer.append("\t\t" + "}else if (airline.numberOfSeatsSold < airline.maximumCapacity) {" + "\n\n");
        stringBuffer.append("\t\t\t" + "return false;" + "\n\n");
        stringBuffer.append("\t\t" + "} else {" + "\n\n");
        stringBuffer.append("\t\t\t" + "return true;" + "\n");
        stringBuffer.append("\t\t" + "}" + "\n");

        stringBuffer.append("\t" + "}" + "\n");
        stringBuffer.append("}");

        return stringBuffer.toString();
    }

    private void writeTC(String name,String content,String num){

        String filePath = this.home + separator + "numberOf" + num + separator + name + ".java";
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
        stringBuffer.append("package airline.numberOf" + TESTCASENUM[0] +";" + "\n\n");
        stringBuffer.append("import java.lang.reflect.Method;" + "\n\n");
        stringBuffer.append("public class TestAll {" + "\n");

        stringBuffer.append("\t" + "public static String execute() throws Exception {" + "\n\n");
        stringBuffer.append("\t\t" + "long startTime = System.currentTimeMillis();" + "\n\n");
        stringBuffer.append("\t\t" + "boolean flag = true;"+"\n\n");
        stringBuffer.append("\t\t" + "String log=null;" + "\n\n");
        stringBuffer.append("\t\t" + "int count = 0;" + "\n\n");
        stringBuffer.append("\t\t" + "while (flag){"+"\n");
        stringBuffer.append("\t\t\t" + "for (int i=1;i<=" + TESTCASENUM[0] + ";i++){" + "\n");

        stringBuffer.append("\t\t\t\t" + "Class<?> clazz = Class.forName(\"airline.numberOf" + TESTCASENUM[0] + ".AirlineTickets2ThreadsTest\"+i);" + "\n\n");
        stringBuffer.append("\t\t\t\t" + "Method method = clazz.getMethod(\"test2ThreadsFullInvarient\");"+"\n\n");
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

        writeTC("TestAll", stringBuffer.toString(),String.valueOf(TESTCASENUM[0]));
    }

    public static int  generate(int seed){
        long startTime = System.currentTimeMillis();
        GenTestCase4AirlineTickets gtc = new GenTestCase4AirlineTickets();
        gtc.generateTestCase(seed);
        gtc.generateTestAll();
        long endTime = System.currentTimeMillis();
        return (int)(endTime-startTime);
    }
}
