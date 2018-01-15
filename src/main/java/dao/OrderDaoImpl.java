package dao;

import java.util.List;

import model.Order;
import util.HQLTools;

public class OrderDaoImpl implements OrderDao {

	public OrderDaoImpl() {
		super();
	}

	@Override
	public List<Order> findOrders(String userName) {
		final String HQL = "from Order o where o.userName='" + userName + "'";
		List<Order> orders = HQLTools.find(HQL);
		return orders;
	}

}
