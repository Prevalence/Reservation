package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import service.OrderService;
import util.DBManager;

/**
 * Servlet implementation class OrdersServlet
 */
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrderService orderService = OrderService.getImplement();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null == session) {
			PrintWriter out = response.getWriter();
			out.println("Not logged in, Please log in first!");
			out.println("<a href=\"login.jsp\">login here</a>");
			out.close();
		} else {
			String userName = "";
			if (null != session.getAttribute("loginUserName"))
				userName = (String) session.getAttribute("loginUserName");
			else {
				PrintWriter out = response.getWriter();
				out.println("Not logged in, Please log in first!");
				out.println("<a href=\"login.jsp\">login here</a>");
				out.close();
			}

			int pageNow = 1;
			if (null != request.getParameter("pageNow")) {
				pageNow = Integer.valueOf(request.getParameter("pageNow"));
			}
			request.setAttribute("pageNow", pageNow);

			/**
			 * 获得总订单
			 */
			ArrayList<Order> allOrders = (ArrayList<Order>) orderService.getOrders(userName);
			final int PAGE_SIZE = 1;
			session.setAttribute("totalPage", allOrders.size() / PAGE_SIZE);
			/**
			 * 根据当前页号，绑定子List到Session上
			 */
			session.setAttribute("orderList", allOrders.subList(PAGE_SIZE * (pageNow - 1), PAGE_SIZE * pageNow));

			request.getRequestDispatcher("order.jsp").forward(request, response);

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

	private void checkhortage(String userName, Connection con, PrintWriter out) throws SQLException {
		final String SQL = "select orders.`订单ID`,orders.`库存数量`,orders.`货物数量` from orders where orders.`货物数量` > orders.`库存数量`";
		ResultSet result = con.prepareStatement(SQL).executeQuery();
		result.last();
		int resultSize = result.getRow();
		result.beforeFirst();
		if (resultSize > 0) {
			while (result.next()) {
				out.println(result.getInt(1) + " 号订单有缺货，库存剩余 " + result.getInt(2) + "，订购数量为 " + result.getInt(3));
			}
		}
	}

}
