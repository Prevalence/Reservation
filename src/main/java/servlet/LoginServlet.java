package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 业务逻辑层接口
	 */
	private UserService userService = UserService.getImplments();

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
		String loginResult = userService.login(userName, password);
		if ("success".equals(loginResult)) {
			if (null == session.getAttribute("loginUserName")) {
				ServletContext context = request.getServletContext();
				context.setAttribute("loggedIn", (int) context.getAttribute("loggedIn") + 1);
				context.setAttribute("tourist", (int) context.getAttribute("tourist") - 1);
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

}
