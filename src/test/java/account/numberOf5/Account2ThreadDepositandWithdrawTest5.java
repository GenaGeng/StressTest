package account.numberOf5;

import account.Account;
import junit.framework.TestCase;

import org.junit.Before;

import org.junit.After;

import org.junit.Test;

public class Account2ThreadDepositandWithdrawTest5 extends TestCase{

	Account2ThreadDepositandWithdrawTest5 accountTest;

	@Before
	public void setUp(){

		accountTest = new Account2ThreadDepositandWithdrawTest5();
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
		Account account = new Account("The Account",5004);
		Thread[] withdrawalThreads = new Thread[numThreads];

		Thread[] depositThreads = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i] = new Thread(new DepositThread(account,1316));
			withdrawalThreads[i] = new Thread(new WithdrawThread(account,3431));
		}

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i].start();

			withdrawalThreads[i].start();

		}

		for (int i = 0; i < numThreads; i++) {
			depositThreads[i].join();

			withdrawalThreads[i].join();
		}

		if (account.amount!=774){
			return false;
		}else {
			return true;
		}
	}
}