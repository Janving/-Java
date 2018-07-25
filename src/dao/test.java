package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import entity.Order;
import entity.OrderItem;
import entity.Product;

public class test {

	public static void main(String[] args) throws Exception {
	
		OrderItemDao od = new OrderItemDao();
	
		od.delete(2);
	
	
	}
	

}
