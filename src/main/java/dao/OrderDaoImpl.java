package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import util.DBManager;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> findOrders(String userName) {
		Connection con = DBManager.INSTANCE.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from orders where userName=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			ArrayList<Order> ordersList = new ArrayList<Order>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(7), userName, rs.getString(2), rs.getString(3), rs.getDouble(4),
						rs.getInt(5), rs.getDouble(6));
				ordersList.add(order);
			}
			con.close();
			return ordersList;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Order>();
		}
	}

}
