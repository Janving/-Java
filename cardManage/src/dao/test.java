package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class test {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		ArrayList<Connection>list = new ArrayList<Connection>();
		Connection con =null;
		if(list.size()>0) {
			//return list.remove(0);
			System.out.println("return list.remove(0)");
			
		}
		//连接池中没有连接
		else {
			Properties p = new Properties();
			//加载配置文件
			p.load(BaseDao.class.getClassLoader().getResourceAsStream("dao/jdbc.properties"));
			
			String driverClass=p.getProperty("jdbc.driverClass");
			String jdbcUrl=p.getProperty("jdbc.jdbcUrl");
			String username=p.getProperty("jdbc.username");
			String password=p.getProperty("jdbc.password");
			//加载驱动
			Class.forName(driverClass);
			System.out.println("Sussce driver");
			//和指定的数据库建立连接
			for(int i=0;i<10;i++) {
				con=DriverManager.getConnection(jdbcUrl,username,password);
				System.out.println("Sussce connection");
				list.add(con);
			}
		}System.out.println("return list.remove(0)");
		
	}
	

}
