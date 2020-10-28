package critical.numberOf1;

import critical.Critical;
import critical.Section;

public class CriticalTest2Threads{

	public StringBuffer test2Threads()  throws InterruptedException {
		Thread t1, t2;

		Critical critical = new Critical();

		Section[] sections = new Section[2];

		Thread[] threads = new Thread[2];

		for (int i=0;i<2;i++){

			sections[i] = new Section(critical,i);

		}

		for (int i=0;i<2;i++){

			threads[i] = new Thread(sections[i]);

		}

		for (int i=0;i<2;i++){

			threads[i].start();

		}

		for (int i=0;i<2;i++){

			threads[i].join();

		}

		return critical.stringBuffer;
	}
}