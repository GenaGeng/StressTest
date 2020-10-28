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
 * @date 2020/10/26
 */
public class GenTestCase4Critical {
    /*测试用例路径*/
    private String home;
    private int[] THREADNUM = new int[21];

    /*test case name list*/
    private List<String> testcasenames;

    public GenTestCase4Critical(){

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
        stringBuffer.append("import critical.Critical;" + "\n" +
                "import critical.Section;" + "\n\n");

        String TCNmae = "CriticalTest" + String.valueOf(threadNum) + "Threads";
        stringBuffer.append("public class " + TCNmae + "{" + "\n\n");

        stringBuffer.append("\t" + "public StringBuffer test2Threads()  throws InterruptedException {" + "\n");
        stringBuffer.append("\t\t" + "Thread t1, t2;" + "\n\n");
        stringBuffer.append("\t\t" + "Critical critical = new Critical();" +"\n\n");
        stringBuffer.append("\t\t"+"Section[] sections = new Section["+threadNum+"];"+"\n\n");
        stringBuffer.append("\t\t"+"Thread[] threads = new Thread["+threadNum+"];"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<"+threadNum+";i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"sections[i] = new Section(critical,i);"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<"+threadNum+";i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i] = new Thread(sections[i]);"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<"+threadNum+";i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i].start();"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t"+"for (int i=0;i<"+threadNum+";i++){"+"\n\n");
        stringBuffer.append("\t\t\t"+"threads[i].join();"+"\n\n");
        stringBuffer.append("\t\t"+"}"+"\n\n");
        stringBuffer.append("\t\t" + "return critical.stringBuffer;" + "\n");
        stringBuffer.append("\t" + "}" + "\n");

        stringBuffer.append("}");

        return stringBuffer.toString();
    }

    private void writeTestCase(String name,String content){
        String filePath = this.home + separator + "numberOf1" + separator + name + ".java";
        File file = new File(filePath);

        PrintWriter printWriter1 = null;
        try {
            printWriter1 = new PrintWriter(file);
            printWriter1.write(content);
            printWriter1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int generate(){
        long startTime = System.currentTimeMillis();
        GenTestCase4Critical gtc = new GenTestCase4Critical();
        gtc.generateTeatCase();
        return (int) (System.currentTimeMillis()-startTime);
    }
}
