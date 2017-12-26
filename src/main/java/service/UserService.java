package service;

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
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return "success"或者"duplicate"
	 */
	public String register(String userName, String password);

	/**
	 * 获取实现
	 * 
	 * @return
	 */
	public static UserService getImplments() {
		return new UserServiceImpl();
	}
}
