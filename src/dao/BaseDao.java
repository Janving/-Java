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
	//�����ӳ��л��һ������
	
	public synchronized static Connection getConnection()throws Exception{
		

		Connection con =null;
		if(list.size()>0) {
			return list.remove(0);
			
		}
		//���ӳ���û������
		else {
			Properties p = new Properties();
			//���������ļ�
			p.load(BaseDao.class.getClassLoader().getResourceAsStream("dao/jdbc.properties"));
			
			String driverClass=p.getProperty("jdbc.driverClass");
			String jdbcUrl=p.getProperty("jdbc.jdbcUrl");
			String username=p.getProperty("jdbc.username");
			String password=p.getProperty("jdbc.password");
			//��������
			Class.forName(driverClass);
			
			//��ָ�������ݿ⽨������
			for(int i=0;i<10;i++) {
				con=DriverManager.getConnection(jdbcUrl,username,password);
			
				list.add(con);
			}
		}
	
		return list.remove(0);
	}
	
	//�رս����
	
	public static void close(ResultSet rs) throws Exception{
		if(rs!=null)
			rs.close();
		
	}
	//�ر�Ԥ����
	
	public static void close(PreparedStatement pst) throws Exception{
		if(pst!=null)
			pst.close();
	}
	
	//�ر����Ӷ���
	
	public synchronized static void close(Connection con) {
		if(con!=null)
			list.add(con);
	}
	
	//�رս������Ԥ�������ӵȶ���
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection con)throws Exception{
		close(rs);
		close(ps);
		close(con);
	}
	
	
	/*
	 * ��ɾ
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
	 * ��
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
