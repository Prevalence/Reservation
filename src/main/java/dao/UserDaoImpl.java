package dao;

import java.util.List;

import model.User;
import util.HQLTools;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
		super();
		System.out.println("constructor");
	}

	@Override
	public String login(String userName, String password) {
		final String HQL = "select u.password from User u where u.userName='" + userName + "'";
		List<String> result = HQLTools.find(HQL);
		if (result.isEmpty()) {
			return "failure";
		} else {
			String rightPassword = result.get(0);
			if (rightPassword.equals(password))
				return "success";
		}
		return "failure";

	}

	@Override
	public String register(User user) {
		String userName = user.getUserName();
		final String HQL = " from User u where u.userName='" + userName + "'";
		List<String> result = HQLTools.find(HQL);
		if (null == result) {
			System.err.println("null");
			return "failure";
		} else if (result.size() == 0) {
			System.err.println("size为0");
			HQLTools.add(user);
			return "success";
		}
		return "failure";

	}

}
