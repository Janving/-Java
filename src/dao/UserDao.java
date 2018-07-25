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
	public boolean isRegist(User user) throws Exception{
		boolean b= false;
		Connection con=getConnection();
		String sql="insert into usertable (userName,password,email) values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getUpass());
		ps.setString(3, user.getEmail());
		int i=ps.executeUpdate();
		if (i>0){
			b=true;
		}
		close(null,ps,con);
		return b;
	}
	
	
	//����½ʱ����½ʱ�����ݼ�¼
	public boolean isLoginRecord(String uname) throws Exception {	
		boolean b= false;
		Connection con=getConnection();
		String sql="insert into loginrecord value(null,?,CURRENT_TIMESTAMP)";
		System.out.println(uname);
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, uname);
		int i=ps.executeUpdate();
		if (i>0){
			b=true;
		}
		close(null,ps,con);
		return b;
		
	}
	
	//������յ�½��¼
	public int lastdayCount() throws Exception {
		Connection con=getConnection();
		String sql="select  count(distinct(userName) ) as sum,loginTime  FROM loginrecord  GROUP BY Day(loginTime)  order by loginTime desc limit 1,1";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		rs.next();
		int sum=rs.getInt(1);
      
		return sum;
	}
	//ͳ��������������
	public int lastdayOrderItem() throws SQLException {
		String sql="select count(id) as sum,create_time from orderitem group by Day(create_time) order by create_time desc limit 1,1";
		ResultSet rs=BaseDao.commonQuery(sql);
		rs.next();
		int sum=rs.getInt(1);
		return sum;
		
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
		    user.setId(rs.getInt("id"));
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
	
	public int userCount() throws Exception {
		
		Connection con=getConnection();
		String sql="select count(*) from usertable";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		rs.next();
		int sum=rs.getInt(1);

		return sum;
	
	}
	
	public int cardCount() throws Exception {

		Connection con=getConnection();
		String sql="select count(*) from cardinfo";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		rs.next();
		int sum=rs.getInt(1);
	
		return sum;
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
