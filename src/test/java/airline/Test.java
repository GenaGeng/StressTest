package airline;

import account.GenTestCase4Account;
import log.WriteResult2Excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GN
 * @description
 * @date 2020/10/24
 */

/**
 * 需要修改的地方：
 * 1、对同一个程序，执行不同数目的测试用例时，
 *    修改GenTestCase4Account中的TESTCASE[i]
 *    修改path值、修改"account.numberOf5.TestAll"、修改excelname
 * 2、对不同程序，修改path值、修改"account.numberOf5.TestAll"、修改excelName，修改programName
 *
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<Long> ET = new ArrayList<>();
        List<Integer> GT = new ArrayList<>();
        List<Long> NUM = new ArrayList<>();
        for (int i=1;i<=30;i++) {
            System.out.println("repeat time=" + i);
            //第i次重复执行，先生成测试用例
            String path ="E:\\project\\CMT\\StressTest\\src\\test\\java\\airline\\numberOf5";
            File file = new File(path);
            File[] files = file.listFiles();
            if (files.length!=0){
                delFolder(path);
            }

            int gt = GenTestCase4AirlineTickets.generate(i);
            GT.add(gt);

            Thread.sleep(5000);

            //然后执行测试用例
            Class<?> clazz = Class.forName("airline.numberOf5.TestAll");

            Object result = clazz.getMethod("execute").invoke(clazz.newInstance(), null);
            String res = (String)result;
            System.out.println("repeat time=" + i + res);
            if (res.contains("false")){
                int index = res.indexOf("false");
                ET.add(Long.parseLong(res.substring(index + 5)));
                NUM.add(Long.parseLong(res.substring(0,index)));
            }

        }
        WriteResult2Excel writeResult2Excel = new WriteResult2Excel();
        writeResult2Excel.writeResult("GT","5TC4AirlineTickets","AirlineTickets",String.valueOf(5),GT,ET,NUM);
        writeResult2Excel.writeResult("ET","5TC4AirlineTickets","AirlineTickets",String.valueOf(5),GT,ET,NUM);
        writeResult2Excel.writeResult("FTime","5TC4AirlineTickets","AirlineTickets",String.valueOf(5),GT,ET,NUM);
        writeResult2Excel.writeResult("FMeasure","5TC4AirlineTickets","AirlineTickets",String.valueOf(5),GT,ET,NUM);
    }


    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            //不想删除文佳夹隐藏下面
//			String filePath = folderPath;
//			filePath = filePath.toString();
//			java.io.File myFilePath = new java.io.File(filePath);
//			myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
//				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

}
