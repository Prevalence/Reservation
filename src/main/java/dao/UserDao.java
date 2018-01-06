package dao;

import javax.ejb.Remote;

import model.User;

/**
 * Dao层接口
 * 
 * @author njdx
 *
 */
@Remote
public interface UserDao {
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
	 *            用户对象
	 * @return "success"或者"duplicate"
	 */
	public String register(User user);

}
