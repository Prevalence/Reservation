package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.User;
import util.DBManager;

public class UserDaoImpl implements UserDao {
	/**
	 * 数据库管理对象
	 */
	private DBManager dbManager = DBManager.INSTANCE;

	@Override
	public String login(String userName, String password) {
		Connection conn = dbManager.getConnection();
		final String SQL = "select password from user where userName=?";
		try {
			PreparedStatement preStatement = conn.prepareStatement(SQL);
			preStatement.setString(1, userName);
			ResultSet resultSet = preStatement.executeQuery();
			resultSet.next();
			String passwordInDatabase = resultSet.getString(1);
			preStatement.close();
			if (password.equals(passwordInDatabase)) {
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String register(User user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		assert (null != userName);
		assert (null != password);
		Connection conn = dbManager.getConnection();
		final String SQLForAdd = "insert into user values(?,?)";
		try {
			PreparedStatement preStatementForAdd = conn.prepareStatement(SQLForAdd);
			preStatementForAdd.setString(1, userName);
			preStatementForAdd.setString(2, password);
			preStatementForAdd.execute();
			preStatementForAdd.close();
			return "success";
		} catch (SQLIntegrityConstraintViolationException e) {
			return "duplicate";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "duplicate";
	}

}
