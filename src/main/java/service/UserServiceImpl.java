package service;

import dao.UserDao;

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
		return null;
	}

	@Override
	public String register(String userName, String password) {
		return null;
	}

}
