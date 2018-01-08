package util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import service.OrderService;
import service.UserService;

public class Factory {
	public static OrderService getOrderServiceImpl() {
		final String URL = "ejb:/J2EEServer/OrderServiceImpl!service.OrderService";
		return (OrderService) getEJB(URL);
	}

	public static UserService getUserServiceImpl() {
		final String URL = "ejb:/J2EEServer/UserServiceImpl!service.UserService";
		return (UserService) getEJB(URL);
	}

	private static Object getEJB(String JNDIPath) {
		Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			Context context = new InitialContext(jndiProperties);
			return context.lookup(JNDIPath);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;

	}
}
