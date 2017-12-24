package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBManager;
import util.UserListener;

/**
 * Servlet implementation class LoginServlet
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
		HttpSession session = request.getSession(false);
		if (null != session) {

		} else {
			PrintWriter out = response.getWriter();
			out.println("Not logged in, Please log in first!");
			out.println("<a href=\"login.jsp\">login here</a>");
			out.close();
		}
		String loginResult = login(userName, password);
		if ("success".equals(loginResult)) {
			if (null == session.getAttribute("loginUserName")) {
				ServletContext context = request.getServletContext();
				context.setAttribute("logged in", (int) context.getAttribute("logged in") + 1);
				context.setAttribute("not logged in", (int) context.getAttribute("not logged in") - 1);
			}
			session.setAttribute("loginUserName", userName);
			response.sendRedirect("OrdersServlet");
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
			preStatement.close();
			if (password.equals(passwordInDatabase)) {
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

}
