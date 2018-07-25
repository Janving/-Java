 package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.UserDao;
import email.EmailTest;
import entity.Card;
import entity.User;
import util.MyUtil;

public class UserAction  extends ActionSupport implements RequestAware, ModelDriven<User>,SessionAware,ApplicationAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Map<String,Object> application;
	//���Ϊ��Ϊ0�ж��û����Ƿ���ã�Ϊ1ע��
	private String flag;

	private User user = new User();
	private String reupass;
	private Map<String,Object> request;
	private Map<String,Object> session;
	UserDao ud= new UserDao();
	
	//������֤��
	private String eName;
	private String eIden;
	
	/*
	 * Ԥ��
	 */
	public String preregister() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();  
		String path=request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
		
		String iden=MyUtil.getStringID();//��������ʶ����
		user.setIden(iden);
		application.put(user.getUname(), user);
			
		String text="����������������֤�������"+ basePath+"user/confirm.action?eName="+user.getUname()+"&eIden="+user.getIden();
		EmailTest et= new EmailTest();
	    et.testSendEmail("��֤����",text,user.getEmail());
	    	
		return "preregister";
			
			
		
	}
	
	/*
	 * ע����֤
	 */
	
	public String confirm() throws Exception {
		if(eName==null|ud.isExit(user.getUname())){
			return "confirmfail";
		}
		 if(application.get(eName)!=null) {	 
			 user=(User) application.get(eName); 
			 if(user.getIden().equals(eIden)) {
				 //�����֤����ȣ���ע�����ݿ�
				 if(ud.isRegist(user)) {
						return SUCCESS;
					}else {
						return "registerfail";
					}
				 	
			 }else {			 
				 return "confirmfail";
			 }
			 
		 }
		 
		return "error";	
	}
	
	
	/*
	 * ע��
	 */
	public String register() {
		try {
			if("0".equals(flag)) {                  //�ж��û����Ƿ����
				if(ud.isExit(user.getUname())) {              //�û�������
					request.put("isExit","false");
				}else {
					request.put("isExit", "true");
				}
				request.put("uname",user.getUname());
				request.put("upass",user.getUpass());
				request.put("email",user.getEmail());
				request.put("reupass",reupass);
				
				return "register";
			}else {
				
				String pre=preregister();//�����ʼ�
				return pre;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	
	}
	
	

	
	
	
	/*
	 * ��½
	 */
	public String login() {
		try {
	
			
			if(ud.isLogin(user)){
			    session.put("uemail", user.getEmail());
				session.put("userName",user.getUname());
				session.put("userPWD", user.getUpass());
				session.put("user",user);
			  
				//¼��Ǽ�ʱ���¼
				ud.isLoginRecord(user.getUname());
				int userCount=ud.userCount();
				int cardCount=ud.cardCount();
				int lastdayCount=ud.lastdayCount();
				int lastdayOrderItem=ud.lastdayOrderItem();
				//����û��ͻ�����
				application.put("userCount", userCount);
				application.put("cardCount", cardCount);
				application.put("lastdayCount",lastdayCount);
				application.put("lastdayOrderItem",lastdayOrderItem);
				return SUCCESS;
			}
			//��ҳ��ʹ��<s:fielderror/>��ȡ��������Ϣ
			this.addFieldError("fali", "�û������������");
			
			return "loginFail";
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/*
	 * �޸�����
	 */
	public String updatePwd() {
		try {
			ud.updatePWD((String)session.get("userName"), user.getUpass());
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/*
	 * 
	 * ����session�е��˻���Ϣ
	 */
	
	public void refreshsession() throws Exception {
		
		if(ud.getUserbyName(user)) {
			session.put("user", user);
		}
	}
	
	/*
	 * �޸��û���Ϣ
	 */
	public String updateUser() {
		
		try {
			ud.updateUser(user);
			refreshsession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	
	}
	/*
	 * �������뷢�ʼ�
	 */
	public String forgot() throws Exception {
		//ƾ���û�����ȡ��Ϣ
		
		if(ud.getUserbyName(user)) {

			
			String text="��������ǣ� "+user.getUpass()+" ��Ҫ��Ū����Ŷ";
		
	    	EmailTest et= new EmailTest();
	    	et.testSendEmail("�һ�����",text,user.getEmail());
		}
		return SUCCESS;
		
		
	}
	/*
	 * ��ȫ�˳�
	 */
	public String logout() {
		session.clear();
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}


	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	


	public UserDao getUd() {
		return ud;
	}


	public void setUd(UserDao ud) {
		this.ud = ud;
	}


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Map<String, Object> getApplication() {
		return application;
	}


	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteIden() {
		return eIden;
	}

	public void seteIden(String eIden) {
		this.eIden = eIden;
	}

	public String getReupass() {
		return reupass;
	}

	public void setReupass(String reupass) {
		this.reupass = reupass;
	}
	
	
}
