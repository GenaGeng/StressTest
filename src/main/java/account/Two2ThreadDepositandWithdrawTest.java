package account;

import org.junit.Test;

/**
 * @author GN
 * @description
 * @date 2020/10/9
 */
public class Two2ThreadDepositandWithdrawTest extends Failable{


        public static void main(String[] args) throws Exception {
            Two2ThreadDepositandWithdrawTest accountTest = new Two2ThreadDepositandWithdrawTest();
//        accountTest.test2RingTransferAndInvariantCheck();
//        accountTest.test3RingTransferAndInvariantCheck();
//        accountTest.test5RingTransferAndInvariantCheck();
//        accountTest.test2ThreadDepositAndCheckInvariant();
//        accountTest.test3ThreadDepositAndCheckInvariant();
//        accountTest.test5ThreadDepositAndCheckInvariant();
//        accountTest.test2ThreadWithdrawAndCheckInvariant();
//        accountTest.test3ThreadWithdrawAndCheckInvariant();
//        accountTest.test5ThreadWithdrawAndCheckInvariant();
            accountTest.test2ThreadDepositAndWithdrawAndCheckInvariant();
//        accountTest.test3ThreadDepositAndWithdrawAndCheckInvariant();
//        accountTest.test5ThreadDepositAndWithdrawAndCheckInvariant();
        }



        @Test
        public void test2ThreadDepositAndWithdrawAndCheckInvariant() throws Exception {
            performDepositsAndWithdrawalsAndCheckInvariant(2);
        }


        private class DepositThread implements Runnable {

            private final Account account;
            private final double money;

            public DepositThread(Account account, double money) {
                this.account = account;
                this.money = money;
            }

            @Override
            public void run() {
                account.depsite(money);
            }

        }

        private class WithdrawThread implements Runnable {

            private final Account account;
            private final double amount;

            public WithdrawThread(Account account, double amount) {
                this.account = account;
                this.amount = amount;
            }

            @Override
            public void run() {
                account.withdraw(amount);
            }

        }

        private void performDepositsAndWithdrawalsAndCheckInvariant(int numThreads) throws Exception {

            Account account = new Account("The Account", 9000);
//        numThreads /= 2;
            Thread[] withdrawalThreads = new Thread[numThreads];
            Thread[] depositThreads = new Thread[numThreads];

            for (int i = 0; i < numThreads; i++) {
                depositThreads[i] = new Thread(new DepositThread(account, 1000));
                withdrawalThreads[i] = new Thread(new WithdrawThread(account, 2000));

            }
            for (int i = 0; i < numThreads; i++) {
                depositThreads[i].start();
                withdrawalThreads[i].start();
            }
            for (int i = 0; i < numThreads; i++) {
                depositThreads[i].join();
                withdrawalThreads[i].join();
            }

            if (account.amount!=7000){
                System.err.println(account.amount);
            }else {
                System.out.println(account.amount);
            }


        }


}
