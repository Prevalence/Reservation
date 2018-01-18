package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;
import util.HQLTools;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private HQLTools hqlTools;

	@Override
	public String login(String userName, String password) {
		final String HQL = "select u.password from User u where u.userName='" + userName + "'";
		List<String> result = hqlTools.find(HQL);
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
		List<String> result = hqlTools.find(HQL);
		if (null == result) {
			System.err.println("null");
			return "failure";
		} else if (result.size() == 0) {
			hqlTools.add(user);
			return "success";
		}
		return "failure";

	}

}
