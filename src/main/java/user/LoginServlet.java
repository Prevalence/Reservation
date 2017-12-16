package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

/**
 * Servlet implementation class UserServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String loginResult = login(userName, password);
		if ("success".equals(loginResult)) {
			response.sendRedirect("orders.jsp");
		} else if ("failure".equals(loginResult)) {
			response.sendRedirect("loginFail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 登陆
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	private String login(String userName, String password) {
		Connection conn = DBManager.INSTANCE.getConnection();
		final String SQL = "select password from user where userName=?";
		try {
			PreparedStatement preStatement = conn.prepareStatement(SQL);
			preStatement.setString(1, userName);
			ResultSet resultSet = preStatement.executeQuery();
			resultSet.next();
			String passwordInDatabase = resultSet.getString(1);

			if (password.equals(passwordInDatabase)) {
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

}
