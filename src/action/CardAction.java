package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.CardDao;
import entity.Card;
import entity.PageUrl;
import util.MyUtil;

public class CardAction extends ActionSupport implements ModelDriven<Card>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String,Object> session;
	CardDao cd = new CardDao();
	//封装名片信息
	private Card card = new Card();
	String act="";               //接受判断动作，查询，删除查询，修改查询
	String ids[];             //接受多个id，删除多个
	private int pageCur;      
	private int totalCount=0;//计算总共有多少个名片
	private int totalPage=0;
	private List<PageUrl> listPage=new ArrayList<PageUrl>();
	File logo;                //图片文件对象
	String logoFileName;
	//查询名片信息
	private ArrayList<Card> allCards;
	//查询一个名片
	private Card acard;
	
	public String getSearchKey() {
		return SearchKey;
	}


	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	private String SearchKey="";
	/**
	 * 添加名片信息
	 */
	public String add() {
		
		//选择logo文件
				System.out.println("logoFileName:"+logoFileName);
						if(logoFileName!= null) {
							//上传文件的保存位置"/logo",该位置是指Tomcat的webapps\cardManage\logo
							String realpath = ServletActionContext.getServletContext().getRealPath("/logos");
							//声明文件目录logo
							File file= new File(realpath);
							//如果目录不存在，则创建目录
							if(!file.exists()) {
								file.mkdirs();
							}
							
							//上传到workspace下，开发时使用，部署这块功能可注释
							File file1 = new File("D:/workplace/cardManage/WebContent/logos");
							//实现文件上传
							String fileType=logoFileName.substring(logoFileName.lastIndexOf('.'));
							String newFileName=MyUtil.getStringID()+fileType;
							card.setNewFileName(newFileName);
							System.out.println("filename:"+newFileName);
							try {
								FileUtils.copyFile(logo, new File(file,newFileName));
								//上传到workspace下，开发时使用，部署这块功能可注释
								FileUtils.copyFile(logo, new File(file1,newFileName));
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
						
						
		//保存到数据库
		card.setUserName((String)session.get("userName"));
		try {
		
			if(cd.add(card)) {
				return "addSuccess";
			}
			return "addFail";
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
			}
		
	}
	
	
	/*
	 * 修改
	 * 
	 */
	 /**
	 * @return
	 */
	public String update() {
		System.out.println("logoFileName:"+logoFileName);
		//选择logo文件
				System.out.println("logoFileName:"+logoFileName);
						if(logoFileName!= null) {
							//上传文件的保存位置"/logo",该位置是指Tomcat的webapps\cardManage\logo
							String realpath = ServletActionContext.getServletContext().getRealPath("/logos");
							//声明文件目录logo
							File file= new File(realpath);
							//如果目录不存在，则创建目录
							if(!file.exists()) {
								file.mkdirs();
							}
							
							//上传到workspace下，开发时使用，部署这块功能可注释
							File file1 = new File("D:/workplace/cardManage/WebContent/logos");
							//实现文件上传
							String fileType=logoFileName.substring(logoFileName.lastIndexOf('.'));
							String newFileName=MyUtil.getStringID()+fileType;
							card.setNewFileName(newFileName);
							System.out.println("filename:"+newFileName);
							try {
								FileUtils.copyFile(logo, new File(file,newFileName));
								//上传到workspace下，开发时使用，部署这块功能可注释
								FileUtils.copyFile(logo, new File(file1,newFileName));
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
		 //删除两个目录下原先的图片
		 if(card.getOldFileName().length()>0) {
			 //上传文件的保存位置“/logo”，该位置是指Tomcat的webapps\cardmanage\logo
			 String realpath=ServletActionContext.getServletContext().getRealPath("/logos");
			 //声明文件目录logo
			 File file = new File(realpath);
			 File file1= new File("D:/workplace/cardManage/WebContent/logos");
			 File f1= new File(file,card.getOldFileName());
			 File f2 = new File(file1,card.getOldFileName());
			 f1.delete();
			 f2.delete();
			 
		 }
		 
		 //修改名片信息
		 try {
			 cd.update(card);
			
			 return selectA();
		 }catch(Exception e) {
			 e.printStackTrace();
			 return ERROR;
		 }
		 
	 }
	
	/*
	 * 查询所有名片,主页
	 */
	
	public String query() {
		try {
			ArrayList<Card> acl=cd.queryAll((String)session.get("userName"),SearchKey);
			int temp=acl.size();
			setTotalCount(temp);
			if(temp==0) {
				totalPage=0;//总页数
				
			}else {
				//返回大于或等于指定表达式的最小整数
				totalPage=(int)Math.ceil((double)temp/10);
			}
			
			for(int i=1;i<totalPage+1;i++) {
				
				if(i>(pageCur-3)&&i<(pageCur+3)) {
				PageUrl pu= new PageUrl();
				
				pu.setPage(i);
				String url;
				if("updateSelect".equals(act)|"deleteSelect".equals(act)) {
					url="card/queryCard.action?act=updateSelect&pageCur="+i;
				}else {
					 url="card/queryCard.action?pageCur="+i;
				}
				
				
				pu.setUrl(url);
				listPage.add(pu);
				}
			}
			
			if((pageCur-1)*10>=temp) {
				pageCur=pageCur-1;
			}
			if(pageCur==0) {
				pageCur=1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		try {
			allCards=cd.queryByPage(pageCur, (String)session.get("userName"),SearchKey);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		if("deleteSelect".equals(act)) {
			return "deleteSelect";
		}
		if("updateSelect".equals(act)) {
			return "updateSelect";
		}
		return "querySuccess";
	}
	/*
	 * 查询一个名片
	 */
	public String selectA() {
		
		try {
			acard=cd.selectA(card.getId());
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		if("updateAcard".equals(act)) {
			return "updateAcard";
			
		}
		return "detailcard";
		
	}
	
	
	/*
	 * 删除
	 */
	public String delete() {
		System.out.println(act);
		try {
			if("link".equals(act)){
				cd.delete(card.getId());
			}
			
			if("button".equals(act)) {
				cd.delete(ids);
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return "deleteSuccess";
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public CardDao getCd() {
		return cd;
	}
	public void setCd(CardDao cd) {
		this.cd = cd;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public int getPageCur() {
		return pageCur;
	}
	public void setPageCur(int pageCur) {
		this.pageCur = pageCur;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public File getLogo() {
		return logo;
	}
	public void setLogo(File logo) {
		this.logo = logo;
	}
	public String getLogoFileName() {
		return logoFileName;
	}
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	public ArrayList<Card> getAllCards() {
		return allCards;
	}
	public void setAllCards(ArrayList<Card> allCards) {
		this.allCards = allCards;
	}
	public Card getAcard() {
		return acard;
	}
	public void setAcard(Card acard) {
		this.acard = acard;
	}
	@Override
	public Card getModel() {
		// TODO Auto-generated method stub
		return card;
	}


	public List getListPage() {
		return listPage;
	}


	public void setListPage(List listPage) {
		this.listPage = listPage;
	}
	

	
}
