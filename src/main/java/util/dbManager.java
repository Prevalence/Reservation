package util;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum dbManager {
	INSTANCE;
	public static dbManager getInstance() {
		return INSTANCE;
	}

	private static void init() {
		try {
			Context ctx = new InitialContext();
			DataSource ods = (DataSource) ctx.lookup("jdbc/myJDBC");
			ods.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		dbManager db = dbManager.getInstance();
		db.init();
	}
}
