package service;

import java.util.List;

import javax.ejb.Remote;

import model.Order;

@Remote
public interface OrderService {
	/**
	 * 查找用户的所有订单
	 * 
	 * @param userName
	 * @return
	 */
	public List<Order> getOrders(String userName);
}
