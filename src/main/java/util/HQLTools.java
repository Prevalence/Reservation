/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author John 执行HQL的工具类，封装HQL查询语句
 */
public class HQLTools {

	/**
	 * SessionFactory对象，自动注入
	 */
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 查询方法
	 * 
	 * @param operation
	 *            查询的语句
	 * @return 结果List，需要自己手动转为ArrayLists
	 */
	public <T> List<T> find(String operation) {
		Session session = sessionFactory.getCurrentSession();
		try {
			// 利用 session 建立 query

			@SuppressWarnings("unchecked")
			Query<T> query = session.createQuery(operation);

			// 序列化 query 的结果为一个 list 集合
			List<T> result = query.list();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库查询出错-------");
			return null;
		}

	}

	/**
	 * 增加方法
	 * 
	 * @param objToAdd
	 *            需要增加的对象ArrayList<T>
	 * @return 增加结果
	 */
	public <T> boolean add(ArrayList<T> objToAdd) {
		try {
			Session session = sessionFactory.getCurrentSession();
			for (T t : objToAdd) {
				session.save(t);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库增加出错-------");
			return false;
		}

	}

	public <T> boolean add(T objToAdd) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.save(objToAdd);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库增加出错-------");
			return false;
		}
	}

	/**
	 * 删除方法
	 * 
	 * @param objToAdd
	 *            需要删除的对象ArrayList<T>
	 * @return 删除结果
	 */
	public <T> boolean delete(ArrayList<T> objToDelete) {
		try {
			Session session = sessionFactory.getCurrentSession();
			for (T t : objToDelete) {
				session.delete(t);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库删除出错-------");
			return false;
		}

	}

	public <T> boolean delete(T objToDelete) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.delete(objToDelete);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库删除出错-------");
			return false;
		}

	}

	/**
	 * 更新方法
	 * 
	 * @param objToAdd
	 *            需要更新的对象ArrayList<T>
	 * @return 更新结果
	 */
	public <T> boolean update(ArrayList<T> objToUpdate) {
		try {
			Session session = sessionFactory.getCurrentSession();
			for (T t : objToUpdate) {
				session.update(t);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库更新出错-------");
			return false;
		}

	}

	public <T> boolean update(T objToUpdate) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.update(objToUpdate);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库更新出错-------");
			return false;
		}
	}

	/**
	 * 提供给多线程块SessionFactory以创造绑定线程的Session
	 * 
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public boolean executeQuery(String operation) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(operation);
			query.executeUpdate();

			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------数据库执行Query出错-------");
			return false;
		}

	}

}
