package account.numberOf125;

import account.Account;
import junit.framework.TestCase;

import org.junit.Before;

import org.junit.After;

import org.junit.Test;

public class Account2ThreadDepositandWithdrawTest27 extends TestCase{

	Account2ThreadDepositandWithdrawTest27 accountTest;

	@Before
	public void setUp(){

		accountTest = new Account2ThreadDepositandWithdrawTest27();
	}

	@After
	public void tearDown() {
		accountTest = null;
	}

	@Test
	public void runTest() throws Exception {
		accountTest.test2ThreadDepositAndWithdrawAndCheckInvariant();
	}

	@Test
	public boolean test2ThreadDepositAndWithdrawAndCheckInvariant() throws Exception {
		return performDepositsAndWithdrawalsAndCheckInvariant(2);
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

		private final double money;

		public WithdrawThread(Account account, double money) {
			this.account = account;

			this.money = money;

		}

		@Override
		public void run() {
			account.withdraw(money);
		}
	}

	@Test
	public boolean performDepositsAndWithdrawalsAndCheckInvariant(int numThreads) throws Exception {
		Account account = new Account("The Account",2343);
		Thread[] withdrawalThreads = new Thread[numThreads];

		Thread[] depositThreads = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i] = new Thread(new DepositThread(account,3168));
			withdrawalThreads[i] = new Thread(new WithdrawThread(account,2301));
		}

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i].start();

			withdrawalThreads[i].start();

		}

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i].join();

			withdrawalThreads[i].join();
		}

		if (account.amount!=4077){
			return false;
		}else {
			return true;
		}
	}
}