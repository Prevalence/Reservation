package dao;

import java.util.List;

import model.Order;

public interface OrderDao {
	/**
	 * 查找某个用户的订单
	 * 
	 * @param userName
	 * @return
	 */
	public List<Order> findOrders(String userName);
}
