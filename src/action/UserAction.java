package action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.UserDao;
import email.EmailTest;
import entity.Card;
import entity.User;

public class UserAction  extends ActionSupport implements RequestAware, ModelDriven<User>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	//���Ϊ��Ϊ0�ж��û����Ƿ���ã�Ϊ1ע��
	private String flag;

	private User user = new User();
	private Map<String,Object> request;
	private Map<String,Object> session;
	UserDao ud= new UserDao();
	
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
				return "register";
			}else {                               //ʵ��ע�Ṧ��
				if(ud.isRegist(user.getUname(), user.getUpass())) {
					return SUCCESS;
				}else {
					return "register";
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	/*
	 * ��½
	 */
	public String login() {
		try {
	
			System.out.println(user.getUname()+user.getUpass());
			if(ud.isLogin(user)){
			    session.put("uemail", user.getEmail());
				session.put("userName",user.getUname());
				session.put("userPWD", user.getUpass());
				session.put("user",user);
			    System.out.println(user.getEmail());
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
		System.out.println(user.getEmail());
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

			System.out.println(user.getUname());
			String text="��������ǣ� "+user.getUpass()+" ��Ҫ��Ū����Ŷ";
			System.out.println(text);
	    	EmailTest et= new EmailTest();
	    	et.testSendEmail(text,user.getEmail());
		}
		return SUCCESS;
		
		
	}
	/*
	 * ��ȫ�˳�
	 */
	public String logout() {
		//session.clear();
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
	
	
}
