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
			} else {
				request.setAttribute("pageNow", pageNow);
			}
			/**
			 * 获得总订单
			 */
			ArrayList<Order> allOrders = (ArrayList<Order>) orderService.getOrders(userName);
			System.out.println(allOrders.size());
			final int PAGE_SIZE = 2;
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

	private void displayOrders(int pageSize, int pageNow, String userName, PrintWriter out) {
		// 三个变量先初始化
		int pageCount = 1;// 总共的页数，该变量是计算出来的
		int rowCount = 1;// 总共有多少条记录，该变量需要读取数据库得到
		// 定义第四个变量，即当前要显示的页数，初始化为1
		// 这个当前页是用户决定的，所以由用户请求参数来确定
		// 这个参数是下文中<a>链接中的href传递过来的，形如：/UserManager/ManageUsers?pageNow=xx

		Connection con = DBManager.INSTANCE.getConnection();
		// 下面要连接数据库读取数据了

		// 算出共有多少页
		// 1.查询rowCount
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select count(*) from orders where userName=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			rs.next();// 游标下移
			rowCount = rs.getInt(1);// 得到rowCount，总共多少条记录
			// 得到总共有多少页：总共有rowCount条记录，每一页显示pageSize条记录，这个页数根据上文提到的三种算法很好计算
			pageCount = (rowCount - 1) / pageSize + 1;
			// 到数据库中查询对应的数据，这个select语句要根据pageSize和pageNow计算出来
			final String SQL = "select * from orders order by '订单号' desc limit ?,?";
			ps = con.prepareStatement(SQL);
			ps.setInt(1, pageSize * (pageNow - 1));
			ps.setInt(2, pageSize * pageNow);

			rs = ps.executeQuery();
			out.println("<table border=1 width=800px align=center>");
			out.println(
					"<tr align=center><th>用户名</th><th>交易时间</th><th>货物</th><th>单价</th><th>数量</th><th>总价</th><th>订单ID</th></tr>");
			// 开始循环显示所有用户信息
			while (rs.next()) {
				out.println("<tr align=center><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>"
						+ rs.getString(3) + "</td><td>" + rs.getDouble(4) + "</td><td>" + rs.getInt(5) + "</td><td>"
						+ rs.getDouble(6) + "</td><td>" + rs.getInt(7));
			}
			out.println("</table>");
			out.println("<br>");
			// 显示上一页，注意href的写法
			if (pageNow != 1) {
				out.println("<a title='上一页' href='OrdersServlet?pageNow=" + (pageNow - 1) + "'><</a>");
			}
			// 显示分页
			for (int i = 1; i <= pageCount; i++) {
				// 这个href的写法很重要
				out.println("<a href='OrdersServlet?pageNow=" + i + "'>" + i + "</a>");
			}
			// 显示下一页
			if (pageNow != pageCount) {
				out.println("<a title='下一页' href='OrdersServlet?pageNow=" + (pageNow + 1) + "'>></a>");
			}
			// 显示分页信息
			out.println("&nbsp;&nbsp;&nbsp;当前页" + pageNow + "/总共页数" + pageCount + "<br>");
			checkhortage(userName, con, out);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
