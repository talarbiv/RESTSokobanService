package db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
/**
 * this class Responsible for communication with the DB use design singelton DB(with sub class)
 * <be>hibernate technology
 * @see getLevelSolution(String board) if have a solution return in our format and if d'ont have a solution return null
 * @see add(Object obj) add solution to DB in our format
 *  */
public class DbManager {

private static class DbManagerHolder{
	public static final DbManager instance = new DbManager();
}
	public static DbManager getInstance() {
		return DbManagerHolder.instance;
	}

	private SessionFactory factory;

	private DbManager() {
		// to show the severe msg
		Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	/**if have a solution return in our format and if d'ont have a solution return null*/
	public String getLevelSolution(String board)
	{
		Session session = null;
		String result;
		List<Solution> listResult=new ArrayList<>();
		try 
		{
			session = factory.openSession();
			@SuppressWarnings("rawtypes")
			Query query=session.createQuery("FROM Solution where board like '"+board+"'");
			listResult=(List<Solution>) query.list();
			//not in DB
			if(listResult.size()==0)
				return null;
			else{
				System.out.println("LevelSol From DB: "+ listResult.get(0).toString());
				return listResult.get(0).getSolutionBoard();
			}
			
		} 
		catch (HibernateException ex) 
		{
			System.out.println(ex.getMessage());
		} catch (NoResultException ex) 
		{
			return null;
		} 
		finally 
		{
			if (session != null)
				session.close();
		}
		return null;
	}
	
	
	/** add solution to DB in our format*/
	public void add(Object obj) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			session.save(obj);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

}
