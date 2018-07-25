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
	//��װ��Ƭ��Ϣ
	private Card card = new Card();
	String act="";               //�����ж϶�������ѯ��ɾ����ѯ���޸Ĳ�ѯ
	String ids[];             //���ܶ��id��ɾ�����
	private int pageCur;      
	private int totalCount=0;//�����ܹ��ж��ٸ���Ƭ
	private int totalPage=0;
	private List<PageUrl> listPage=new ArrayList<PageUrl>();
	File logo;                //ͼƬ�ļ�����
	String logoFileName;
	//��ѯ��Ƭ��Ϣ
	private ArrayList<Card> allCards;
	//��ѯһ����Ƭ
	private Card acard;
	
	public String getSearchKey() {
		return SearchKey;
	}


	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	private String SearchKey="";
	/**
	 * �����Ƭ��Ϣ
	 */
	public String add() {
		
		//ѡ��logo�ļ�
				System.out.println("logoFileName:"+logoFileName);
						if(logoFileName!= null) {
							//�ϴ��ļ��ı���λ��"/logo",��λ����ָTomcat��webapps\cardManage\logo
							String realpath = ServletActionContext.getServletContext().getRealPath("/logos");
							//�����ļ�Ŀ¼logo
							File file= new File(realpath);
							//���Ŀ¼�����ڣ��򴴽�Ŀ¼
							if(!file.exists()) {
								file.mkdirs();
							}
							
							//�ϴ���workspace�£�����ʱʹ�ã�������鹦�ܿ�ע��
							File file1 = new File("D:/workplace/cardManage/WebContent/logos");
							//ʵ���ļ��ϴ�
							String fileType=logoFileName.substring(logoFileName.lastIndexOf('.'));
							String newFileName=MyUtil.getStringID()+fileType;
							card.setNewFileName(newFileName);
							System.out.println("filename:"+newFileName);
							try {
								FileUtils.copyFile(logo, new File(file,newFileName));
								//�ϴ���workspace�£�����ʱʹ�ã�������鹦�ܿ�ע��
								FileUtils.copyFile(logo, new File(file1,newFileName));
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
						
						
		//���浽���ݿ�
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
	 * �޸�
	 * 
	 */
	 /**
	 * @return
	 */
	public String update() {
		System.out.println("logoFileName:"+logoFileName);
		//ѡ��logo�ļ�
				System.out.println("logoFileName:"+logoFileName);
						if(logoFileName!= null) {
							//�ϴ��ļ��ı���λ��"/logo",��λ����ָTomcat��webapps\cardManage\logo
							String realpath = ServletActionContext.getServletContext().getRealPath("/logos");
							//�����ļ�Ŀ¼logo
							File file= new File(realpath);
							//���Ŀ¼�����ڣ��򴴽�Ŀ¼
							if(!file.exists()) {
								file.mkdirs();
							}
							
							//�ϴ���workspace�£�����ʱʹ�ã�������鹦�ܿ�ע��
							File file1 = new File("D:/workplace/cardManage/WebContent/logos");
							//ʵ���ļ��ϴ�
							String fileType=logoFileName.substring(logoFileName.lastIndexOf('.'));
							String newFileName=MyUtil.getStringID()+fileType;
							card.setNewFileName(newFileName);
							System.out.println("filename:"+newFileName);
							try {
								FileUtils.copyFile(logo, new File(file,newFileName));
								//�ϴ���workspace�£�����ʱʹ�ã�������鹦�ܿ�ע��
								FileUtils.copyFile(logo, new File(file1,newFileName));
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
		 //ɾ������Ŀ¼��ԭ�ȵ�ͼƬ
		 if(card.getOldFileName().length()>0) {
			 //�ϴ��ļ��ı���λ�á�/logo������λ����ָTomcat��webapps\cardmanage\logo
			 String realpath=ServletActionContext.getServletContext().getRealPath("/logos");
			 //�����ļ�Ŀ¼logo
			 File file = new File(realpath);
			 File file1= new File("D:/workplace/cardManage/WebContent/logos");
			 File f1= new File(file,card.getOldFileName());
			 File f2 = new File(file1,card.getOldFileName());
			 f1.delete();
			 f2.delete();
			 
		 }
		 
		 //�޸���Ƭ��Ϣ
		 try {
			 cd.update(card);
			
			 return selectA();
		 }catch(Exception e) {
			 e.printStackTrace();
			 return ERROR;
		 }
		 
	 }
	
	/*
	 * ��ѯ������Ƭ,��ҳ
	 */
	
	public String query() {
		try {
			ArrayList<Card> acl=cd.queryAll((String)session.get("userName"),SearchKey);
			int temp=acl.size();
			setTotalCount(temp);
			if(temp==0) {
				totalPage=0;//��ҳ��
				
			}else {
				//���ش��ڻ����ָ�����ʽ����С����
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
	 * ��ѯһ����Ƭ
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
	 * ɾ��
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
