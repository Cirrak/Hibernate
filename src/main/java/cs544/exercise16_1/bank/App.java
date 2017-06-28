package cs544.exercise16_1.bank;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cs544.exercise16_1.bank.dao.HibernateUtil;
import cs544.exercise16_1.bank.domain.Account;
import cs544.exercise16_1.bank.domain.AccountEntry;
import cs544.exercise16_1.bank.domain.Customer;
import cs544.exercise16_1.bank.service.AccountService;
import cs544.exercise16_1.bank.service.IAccountService;


public class App {
	 
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	public static void main(String[] args) throws Exception {
		
		IAccountService accountService = new AccountService();
		// create 2 accounts;
		accountService.createAccount("Frank Brown");
		accountService.createAccount("John Doe");
		//use account 1;
		accountService.deposit(1L, 240);
		accountService.deposit(1L, 529);
		accountService.withdrawEuros(1L, 230);
		//use account 2;
		accountService.deposit(2L, 12450);
		accountService.depositEuros(2L, 200);
		accountService.transferFunds(2L, 1L, 100, "payment of invoice 10232");
		// show balances
		
		Collection<Account> accountlist = accountService.getAllAccounts();
		Customer customer = null;
		for (Account account : accountlist) {
			customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountnumber());
			System.out.println("Account Holder: " + customer.getName());
			System.out.println("-Date-------------------------"
							+ "-Description------------------"
							+ "-Amount-------------");
			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
						.toString(), entry.getDescription(), entry.getAmount());
			}
			System.out.println("----------------------------------------"
					+ "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
					account.getBalance());
		}
	}

}


