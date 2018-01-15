package service;

import java.util.List;

import javax.annotation.Resource;

import dao.OrderDao;
import model.Order;
import util.Factory;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = Factory.getOrderDaoImpl();

	@Override
	public List<Order> getOrders(String userName) {
		return orderDao.findOrders(userName);
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
