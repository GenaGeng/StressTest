package critical;


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
public class GenTestCases4Critical {

    /*测试用例路径*/
    private String home;
    private int[] THREADNUM = new int[21];

    /*test case name list*/
    private List<String> testcasenames;

    public GenTestCases4Critical(){

        home = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java\\critical" ;
        testcasenames = new ArrayList<>();
    }

    public void generateTeatCase(){

            String content = contentOfTestCase(2);

            String TCName = "CriticalTest" + String.valueOf(2) + "Threads";

            testcasenames.add(TCName + ".class");
            writeTestCase(TCName,content);
    }

    public String contentOfTestCase(int threadNum){
        StringBuffer stringBuffer = new StringBuffer(10);

        stringBuffer.append("package critical.numberOf1;" + "\n\n");
        stringBuffer.append("import junit.framework.TestCase;\n" +
                "import org.junit.After;\n" +
                "import org.junit.Before;\n" +
                "import org.junit.Test;" + "\n" +
                "import critical.Critical;" + "\n" +
                "import critical.Section;" + "\n\n");

        String TCNmae = "CriticalTest" + String.valueOf(threadNum) + "Threads";
        stringBuffer.append("public class " + TCNmae + " extends TestCase {" + "\n\n");
        stringBuffer.append("\t" + TCNmae + " criticalTest;" + "\n\n");

        stringBuffer.append("\t" + "@Before" + "\n");
        stringBuffer.append("\t" + "public void setUp() {" + "\n\n");
        stringBuffer.append("\t\t" + "criticalTest = new " + TCNmae +"();" + "\n\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "@After" + "\n");
        stringBuffer.append("\t" + "public void tearDown() {" + "\n");
        stringBuffer.append("\t\t" + "criticalTest = null;" + "\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "@Test" + "\n");
        stringBuffer.append("\t" + "public void runTest() throws Exception {" + "\n\n");
        stringBuffer.append("\t\t" + "criticalTest.test2Threads();" + "\n");
        stringBuffer.append("\t" + "}" + "\n\n");

        stringBuffer.append("\t" + "public void test2Threads() throws Exception{" + "\n");
        String testmethod = "testCriticalSection";
        stringBuffer.append("\t\t" + testmethod + "(" + threadNum + ");" + "\n\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("\t" + "public void "+testmethod+"(int threadnum) throws Exception {"+"\n");
        stringBuffer.append("\t\t" + "Critical critical = new Critical();" +"\n\n");
        stringBuffer.append("\t\t"+"Section[] sections = new Section[threadnum];"+"\n\n");
        stringBuffer.append("\t\t"+"Thread[] threads = new Thread[threadnum];"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<threadnum;i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"sections[i] = new Section(critical,i);"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<threadnum;i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i] = new Thread(sections[i]);"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<threadnum;i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i].start();"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<threadnum;i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i].join();"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");

        stringBuffer.append("\t" + "}" + "\n");
        stringBuffer.append("}");

        return stringBuffer.toString();
    }

    private void writeTestCase(String name,String content){
        String filePath = this.home + separator + "numberOf1" + separator + name + ".java";
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

    public static int generate(){
        long startTime = System.currentTimeMillis();
        GenTestCases4Critical gtc = new GenTestCases4Critical();
        gtc.generateTeatCase();
        return (int) (System.currentTimeMillis()-startTime);
    }
}
