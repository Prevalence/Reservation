package service;

import dao.UserDao;
import model.User;
import util.Factory;

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
	private UserDao userdao = Factory.getUserDaoImpl();

	@Override
	public String login(String userName, String password) {
		return userdao.login(userName, password);
	}

	@Override
	public String register(User user) {
		return userdao.register(user);
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

}
