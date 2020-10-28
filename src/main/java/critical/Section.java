package critical;

public class Section implements Runnable{
    Critical t;
    int threadNum;

    public Section(Critical t,int threadNum){
        this.t=t;
        this.threadNum=threadNum;
        t.stringBuffer = new StringBuffer();
    }


    @Override

    public void run () {

        synchronized(this){
            if (threadNum == 0) {
//                synchronized (this) {

                    t.turn = 0;
                t.stringBuffer.append("In critical section,thread number:" + threadNum);

                    while (t.turn != 0) {
                        t.stringBuffer.append("0's turn do not allow 1");
                    }
                t.stringBuffer.append("Out critical section,thread number:" + threadNum);
                    t.turn = 1;
//                }
            } else {
//                synchronized (this) {
                    if (threadNum == 1) {
                        t.turn = 1;
                        t.stringBuffer.append("In critical section,thread number:" + threadNum);

                        while (t.turn != 1) {
                            t.stringBuffer.append("1's turn do not allow 0");
                        }
                        t.stringBuffer.append("Out critical section,thread number:" + threadNum);
                        t.turn = 0;
                    } else {
                        t.stringBuffer.append("This program only support two threads!  ");
                    }
//                }
            }

        }
    }
}
