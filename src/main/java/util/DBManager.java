package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 负责管理数据库连接的单例工具类
 * 
 * @author njdx
 *
 */
public enum DBManager {
	/**
	 * 单例实例
	 */
	INSTANCE;
	/**
	 * 数据源
	 */
	private DataSource dataSource;

	/**
	 * 私有构造器
	 */
	private DBManager() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/myJDBC");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获得数据库的一个连接
	 * 
	 * @return 一个Connection对象
	 */
	public Connection getConnection() {
		try {
			return INSTANCE.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
