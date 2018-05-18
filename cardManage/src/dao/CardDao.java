package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Card;


public class CardDao extends BaseDao {
	
	
	//�����Ƭ��Ϣ
	
	public boolean add(Card card)throws Exception{
		boolean b= false;
		Connection con=getConnection();
		//IDΪ����
		
		String sql="insert into cardinfo values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement ps =con.prepareStatement(sql);
		ps.setString(1, card.getName());
		ps.setString(2, card.getTelephone());
		ps.setString(3, card.getEmail());
		ps.setString(4, card.getCompany());
		ps.setString(5, card.getPost());
		ps.setString(6, card.getAddress());
		ps.setString(7, card.getNewFileName());
		ps.setString(8, card.getUserName());
		int i=ps.executeUpdate();
		if(i>0)
			b=true;
		return b;
	}
	
	
	//�޸���Ƭ��Ϣ
	
	public boolean update(Card card) throws Exception{
		boolean b= false;
		Connection con=getConnection();
		
		String sql="update cardinfo set name=?,"
				+" telephone=?,"
				+" email=?,"
				+" company=?,"
				+" post=?,"
				+" address=? ";
		if(card.getNewFileName()!=null) {
			sql=sql+", logo=?";
		}
		
		sql=sql+" where id=? ";
	    PreparedStatement ps=con.prepareStatement(sql);
	    ps.setString(1, card.getName());
		ps.setString(2, card.getTelephone());
		ps.setString(3, card.getEmail());
		ps.setString(4, card.getCompany());
		ps.setString(5, card.getPost());
		ps.setString(6, card.getAddress());
		
		if(card.getNewFileName()!=null) {
		ps.setString(7, card.getNewFileName());
		ps.setString(8, card.getId());
		}else {
			ps.setString(7, card.getId());
		}
		
		int i=ps.executeUpdate();
		if(i>0)
			b=true;
		
		return b;
	}
	
	//��ѯ������Ƭ
    public ArrayList<Card> queryAll(String userName,String SearchKey) throws Exception{
    	
    	ArrayList<Card> ac=new ArrayList<Card>();
    	Connection con=getConnection();
    	String sql="select * from cardinfo where userName=?";
    	if(!"".equals(SearchKey)) {
    		sql=sql+" and cardinfo.name  like '%"+SearchKey+"%' ";
    		
    	}
    	PreparedStatement ps= con.prepareStatement(sql);
    	
    	ps.setString(1, userName);
    	
    	ResultSet rs= ps.executeQuery();
    	while(rs.next()) {
    		Card c= new Card();
    		c.setId(rs.getString("id"));
    		c.setName(rs.getString("name"));
    		c.setTelephone(rs.getString("telephone"));
    		c.setEmail(rs.getString("email"));
    		c.setCompany(rs.getString("company"));
    		c.setPost(rs.getString("post"));
    		c.setAddress(rs.getString("address"));
    		c.setNewFileName(rs.getString("logo"));
    		
    		ac.add(c);
    		
    	}
    	return ac;
    	
    }
    
    //��ҳ��ѯ
    
    public ArrayList<Card> queryByPage(int pageCur,String userName,String SearchKey) throws Exception{
    	ArrayList<Card>ac = new ArrayList<Card>();
    	Connection con=getConnection();
    	String sql="select * from card.cardinfo where userName=? ";
    	if(!"".equals(SearchKey)) {
    		sql=sql+" and cardinfo.name  like '%"+SearchKey+"%' ";
    		
    	}
    	sql=sql+"limit ?,?";
    	/*String sql="select a.id,a.name,"
    			+" a.telephone , a.email , a.company ,a.post, a.address, a.logo"
    			+" from cardinfo a where a.usrName=? limit?,?";*/
    	PreparedStatement ps= con.prepareStatement(sql);
    	
    	ps.setString(1, userName);
    	ps.setInt(2, (pageCur-1)*10);
    	ps.setInt(3, (pageCur*10));
    	ResultSet rs=ps.executeQuery();
    	while(rs.next()) {
    		Card c= new Card();
    		c.setId(rs.getString("id"));
    		c.setName(rs.getString("name"));
    		c.setTelephone(rs.getString("telephone"));
    		c.setEmail(rs.getString("email"));
    		c.setCompany(rs.getString("company"));
    		c.setPost(rs.getString("post"));
    		c.setAddress(rs.getString("address"));
    		c.setNewFileName(rs.getString("logo"));
    		ac.add(c);
    	}
    	return ac;
    }
    
    //��ѯһ����Ƭ
    
    public Card selectA(String id) throws Exception{
    	Card c = new Card();
    	Connection con=getConnection();
    	String sql=" select * from cardinfo where id=?";
    	PreparedStatement ps= con.prepareStatement(sql);
    	ps.setString(1, id);
    	ResultSet rs=ps.executeQuery();
    	if(rs.next()) {
    		c.setId(rs.getString("id"));
    		c.setName(rs.getString("name"));
    		c.setTelephone(rs.getString("telephone"));
    		c.setEmail(rs.getString("email"));
    		c.setCompany(rs.getString("company"));
    		c.setPost(rs.getString("post"));
    		c.setAddress(rs.getString("address"));
    		c.setNewFileName(rs.getString("logo"));
    	}
    	return c;
    	
    	
    }
    
    //ɾ��һ����Ƭ��Ϣ
    
    public boolean delete (String id) throws Exception{
    	boolean b =false;
    	Connection con= getConnection();
    	String sql="delete from cardinfo where id=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, id);
    	int i=ps.executeUpdate();
    	if(i>0)
    		b=true;
    	return b;
    	
    }

	//ɾ�������Ƭ��Ϣ
    
    public boolean delete(String ids[]) throws Exception{
    	boolean b= false;
    	Connection con=getConnection();
    	String sql="delete from cardinfo where id in(";
    	for(int i=0;i<ids.length-1;i++) {
    		sql=sql+ids[i]+",";
    		
    	}
    	sql=sql+ids[ids.length-1]+")";
    	PreparedStatement ps=con.prepareStatement(sql);
    	int i=ps.executeUpdate();
    	if(i>0)
    		b=true;
    	return b;
    }

}
