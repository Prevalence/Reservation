package util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CountUserListener
 *
 */
public class CountUserListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public CountUserListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 *      初次访问JSP,Servlet的时候会建立Session，此时总人数加一，游客数加一，数量均保存在ServletContent中
	 */
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
		if (null == context.getAttribute("totalNumberOfVisitor")) {
			context.setAttribute("totalNumberOfVisitor", 1);
			context.setAttribute("logged in", 0);
			context.setAttribute("not logged in", 1);
		} else {
			context.setAttribute("totalNumberOfVisitor", (int) context.getAttribute("totalNumberOfVisitor") + 1);
			context.setAttribute("not logged in", (int) context.getAttribute("not logged in") + 1);
		}
		System.out.println("total: " + context.getAttribute("totalNumberOfVisitor"));
		System.out.println("log in: " + context.getAttribute("logged in"));
		System.out.println("not logged in: " + context.getAttribute("not logged in"));

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
		HttpSession session = se.getSession();
		// 这个Session已经登陆
		if (null == session.getAttribute("userName")) {
			context.setAttribute("logged in", (int) context.getAttribute("logged in") - 1);
		} else {
			context.setAttribute("not logged in", (int) context.getAttribute("not logged in") - 1);
		}
		context.setAttribute("totalNumberOfVisitor", (int) context.getAttribute("totalNumberOfVisitor") - 1);
		System.out.println("total: " + context.getAttribute("totalNumberOfVisitor"));
		System.out.println("log in: " + context.getAttribute("logged in"));
		System.out.println("not logged in: " + context.getAttribute("not logged in"));
	}

}
