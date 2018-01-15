package util;

import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import service.OrderService;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class Factory {
	private static OrderDao orderDaoImpl = new OrderDaoImpl();

	private static UserDao userDaoImpl = new UserDaoImpl();

	private static OrderService orderServiceImpl = new OrderServiceImpl();

	private static UserService userServiceImpl = new UserServiceImpl();

	public static OrderService getOrderServiceImpl() {
		return orderServiceImpl;
	}

	public static UserService getUserServiceImpl() {
		System.out.println("get Service");
		return userServiceImpl;
	}

	public static OrderDao getOrderDaoImpl() {
		return orderDaoImpl;
	}

	public static UserDao getUserDaoImpl() {
		System.out.println("method");
		return userDaoImpl;
	}

}
