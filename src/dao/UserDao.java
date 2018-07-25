package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao extends BaseDao{

	//判断用户名是否存在
	
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
	
	//注册
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
	
	
	//当登陆时将登陆时间数据记录
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
	
	//获得昨日登陆记录
	public int lastdayCount() throws Exception {
		Connection con=getConnection();
		String sql="select  count(distinct(userName) ) as sum,loginTime  FROM loginrecord  GROUP BY Day(loginTime)  order by loginTime desc limit 1,1";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		rs.next();
		int sum=rs.getInt(1);
      
		return sum;
	}
	//统计昨日新增订单
	public int lastdayOrderItem() throws SQLException {
		String sql="select count(id) as sum,create_time from orderitem group by Day(create_time) order by create_time desc limit 1,1";
		ResultSet rs=BaseDao.commonQuery(sql);
		rs.next();
		int sum=rs.getInt(1);
		return sum;
		
	}
	
	
	//登陆
	
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
	
	//修改密码
	
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
	 * 更新信息
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
	 * 修改用户信息
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
