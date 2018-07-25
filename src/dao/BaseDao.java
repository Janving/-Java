package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;



public class BaseDao {

	 public  static PreparedStatement pstmt;
	 public static ResultSet rs;
	 public static Connection conn;
	
	
	static ArrayList<Connection>list = new ArrayList<Connection>();
	//从连接池中获得一个连接
	
	public synchronized static Connection getConnection()throws Exception{
		

		Connection con =null;
		if(list.size()>0) {
			return list.remove(0);
			
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
			
			//和指定的数据库建立连接
			for(int i=0;i<10;i++) {
				con=DriverManager.getConnection(jdbcUrl,username,password);
			
				list.add(con);
			}
		}
	
		return list.remove(0);
	}
	
	//关闭结果集
	
	public static void close(ResultSet rs) throws Exception{
		if(rs!=null)
			rs.close();
		
	}
	//关闭预处理
	
	public static void close(PreparedStatement pst) throws Exception{
		if(pst!=null)
			pst.close();
	}
	
	//关闭连接对象
	
	public synchronized static void close(Connection con) {
		if(con!=null)
			list.add(con);
	}
	
	//关闭结果集、预处理、连接等对象
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection con)throws Exception{
		close(rs);
		close(ps);
		close(con);
	}
	
	
	/*
	 * 增删
	 * */
	public static int commonUpdate(String sql,Object ...params) throws SQLException, Exception{
		int result=0;
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null && params.length>0){
				setvalues(pstmt, params);
				
			}
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
		close(null,pstmt,conn);
		return result;
	}
	
	/**
	 * 查
	 * */
	public static ResultSet commonQuery(String sql,Object ...params){
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			if(params!=null && params.length>0){
				setvalues(pstmt, params);
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * 
	 * @throws Exception 
	 * */
	protected static void setvalues(PreparedStatement pstmt,Object ...params) throws Exception{
		for(int i=0;i<params.length;i++){
			Object obj=params[i];
			pstmt.setObject(i+1, obj);
		}
	}
	
}
