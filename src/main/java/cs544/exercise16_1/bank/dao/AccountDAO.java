package cs544.exercise16_1.bank.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cs544.exercise16_1.bank.domain.Account;

public class AccountDAO implements IAccountDAO {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	public void saveAccount(Account account) {
		Session session = sf.getCurrentSession();
        session.persist(account);
	}

	public void updateAccount(Account account) {
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(account);
	}

	public Account loadAccount(long accountnumber) {
		Session session = sf.getCurrentSession();
		return (Account) session.get(Account.class, accountnumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Account> getAccounts() {
		Session session = sf.getCurrentSession();
		return session.createQuery("from Account").list();
	}

}
