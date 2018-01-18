package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Order;
import util.HQLTools;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private HQLTools hqlTools;

	@Override
	public List<Order> findOrders(String userName) {
		final String HQL = "from Order o where o.userName='" + userName + "'";
		List<Order> orders = hqlTools.find(HQL);
		return orders;
	}

}
