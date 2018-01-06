package service;

import javax.ejb.Remote;

import model.User;

@Remote
public interface UserService {
	/**
	 * 登陆
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return "success"或者"failure"
	 */
	public String login(String userName, String password);

	/**
	 * 注册
	 * 
	 * @param user
	 *            user对象
	 * @return "success"或者"duplicate"
	 */
	public String register(User user);

}
