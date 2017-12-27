package service;

import java.util.List;

import dao.OrderDao;
import model.Order;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderdao;

	@Override
	public List<Order> getOrders(String userName) {
		return null;
	}

}
