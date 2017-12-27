package service;

import dao.UserDao;
import model.User;

/**
 * service层实现
 * 
 * @author njdx
 *
 */
public class UserServiceImpl implements UserService {
	/**
	 * dao层对象
	 */
	private UserDao userdao = UserDao.getImplements();

	@Override
	public String login(String userName, String password) {
		return userdao.login(userName, password);
	}

	@Override
	public String register(User user) {
		return userdao.register(user);
	}

}
