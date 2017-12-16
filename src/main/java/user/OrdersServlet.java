package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrdersServlet
 */
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void getMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		// 从jsp页面获取当前页数
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 查询数据库获得数据计算出总页数
		int countPage = messageService.getCountPage();

		// 将当前页数，总页数，以及找出的数据返回给jsp页面
		request.setAttribute("countPage", countPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("messages", messageService.getMessage(currentPage));
		request.getRequestDispatcher("getMessage.jsp").forward(request, response);
	}

	private int getTotalNumberOfOrders() {
		return 0;
	}

}
