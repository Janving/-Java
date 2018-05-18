package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao extends BaseDao{

	//�ж��û����Ƿ����
	
	public boolean isExit(String uname) throws Exception{
		boolean b=false;
		Connection con=getConnection();
		String sql="select * from usertable where userName=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			b=true;
		}
		close(rs,ps,con);
		return b;
		
	}
	
	//ע��
	public boolean isRegist(String uname,String upass) throws Exception{
		boolean b= false;
		Connection con=getConnection();
		String sql="insert into usertable values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, upass);
		ps.setString(3, null);
		int i=ps.executeUpdate();
		if (i>0){
			b=true;
		}
		close(null,ps,con);
		return b;
	}
	
	//��½
	
	public boolean isLogin(User user) throws Exception{
		
		boolean b= false;
		Connection con =getConnection();
		String sql="select * from usertable where userName=? and password=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2,user.getUpass());
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			b=true;
			user.setEmail(rs.getString("email"));
		}
		close(rs,ps,con);
		return b;
	}
	
	//�޸�����
	
	public boolean updatePWD(String uname,String upass) throws Exception{
		boolean b =false;
		Connection con= getConnection();
		String sql ="update usertable set password=? where userName=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, upass);
		ps.setString(2, uname);
		int i=ps.executeUpdate();
		if(i>0) {
			b=true;
		}
		close(null,ps,con);
		return b;
	}
	
	/*
	 * ������Ϣ
	 */
	public boolean getUserbyName(User user) throws Exception {
		boolean b= false;
		Connection con=getConnection();
		String sql="select * from usertable where userName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,user.getUname());
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			b=true;
			user.setUpass(rs.getString("password"));
			user.setEmail(rs.getString("email"));
		}
		close(rs,ps,con);
		return b;
	}
	/*
	 * �޸��û���Ϣ
	 */
	public boolean updateUser(User user) throws Exception {
		boolean b= false;
		Connection con=getConnection();
		String sql="update usertable set password=? ,email=? where userName=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, user.getUpass());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getUname());
		int i=ps.executeUpdate();
		if(i>0) {
			b=true;
		}
		close(null,ps,con);
		return b;
		
		
	}
}
