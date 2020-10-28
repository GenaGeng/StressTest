package critical;

import org.junit.Test;

/**
 * @author GN
 * @description
 * @date 2020/10/4
 */
public class CriticalTest {

    public static void main(String[] args) throws Exception {

        CriticalTest criticalTest = new CriticalTest();

        criticalTest.test2Threads();
    }

    public void test2Threads() throws Exception{
        testCriticalSection(2);
    }

    public void testCriticalSection(int threadnum) throws Exception{
        Critical critical = new Critical();
        Section[] sections = new Section[threadnum];
        Thread[] threads = new Thread[threadnum];
        for (int i=0;i<threadnum;i++){
            sections[i] = new Section(critical,i);
            threads[i] = new Thread(sections[i]);
        }

        for (int i=0;i<threadnum;i++){
            threads[i].start();
        }
        for (int i=0;i<threadnum;i++){
            threads[i].join();
        }
    }

    @Test
    public void test() throws Exception {
        main(new String[]{});
    }

}
