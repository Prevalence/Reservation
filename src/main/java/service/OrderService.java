package service;

import java.util.List;

import model.Order;

public interface OrderService {
	/**
	 * 查找用户的所有订单
	 * 
	 * @param userName
	 * @return
	 */
	public List<Order> getOrders(String userName);
}
