package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import model.User;

/**
 * service层实现
 * 
 * @author njdx
 *
 */
@Service
public class UserServiceImpl implements UserService {
	/**
	 * dao层对象
	 */
	@Autowired
	private UserDao userdao;

	@Override
	@Transactional
	public String login(String userName, String password) {
		return userdao.login(userName, password);
	}

	@Override
	@Transactional
	public String register(User user) {
		return userdao.register(user);
	}

}
