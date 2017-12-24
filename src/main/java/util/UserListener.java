package util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class UserListener
 *
 */
public class UserListener implements HttpSessionBindingListener {

	/**
	 * Default constructor.
	 */
	public UserListener() {
	}

	/**
	 * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		ServletContext context = event.getSession().getServletContext();
		if (null == context.getAttribute("numberOfUser")) {
			context.setAttribute("numberOfUser", 1);
		} else {
			context.setAttribute("numberOfUser", (int) context.getAttribute("numberOfUser") + 1);
		}
		System.out.println(context.getAttribute("numberOfUser"));
	}

	/**
	 * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		ServletContext context = event.getSession().getServletContext();
		if (null == context.getAttribute("numberOfUser")) {
			context.setAttribute("numberOfUser", 0);
		} else {
			context.setAttribute("numberOfUser", (int) context.getAttribute("numberOfUser") - 1);
		}
		System.out.println(context.getAttribute("numberOfUser"));
	}

}
