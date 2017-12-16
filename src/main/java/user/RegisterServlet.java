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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String registerResult = register(userName, password);
		if ("success".equals(registerResult)) {
			response.sendRedirect("registerSuccess.jsp");
		} else if ("duplicate".equals(registerResult)) {
			response.sendRedirect("registerFail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 注册
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 注册结果
	 */
	private String register(String userName, String password) {
		Connection conn = DBManager.INSTANCE.getConnection();
		final String SQL = "select * from user where userName=?";
		final String SQLForAdd = "insert into user values(?,?)";
		try {
			PreparedStatement preStatement = conn.prepareStatement(SQL);
			preStatement.setString(1, userName);
			ResultSet resultSet = preStatement.executeQuery();
			if (resultSet.getFetchSize() == 0) {
				PreparedStatement preStatementForAdd = conn.prepareStatement(SQLForAdd);
				preStatementForAdd.setString(1, userName);
				preStatementForAdd.setString(2, password);
				preStatementForAdd.execute();
				return "success";
			} else {
				return "duplicate";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "duplicate";
	}

}
