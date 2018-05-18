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



	//标记为，为0判断用户名是否可用；为1注册
	private String flag;

	private User user = new User();
	private Map<String,Object> request;
	private Map<String,Object> session;
	UserDao ud= new UserDao();
	
	/*
	 * 注册
	 */
	public String register() {
		try {
			if("0".equals(flag)) {                  //判断用户名是否可用
				if(ud.isExit(user.getUname())) {              //用户名存在
					request.put("isExit","false");
				}else {
					request.put("isExit", "true");
				}
				request.put("uname",user.getUname());
				return "register";
			}else {                               //实现注册功能
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
	 * 登陆
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
			//在页面使用<s:fielderror/>中取出错误信息
			this.addFieldError("fali", "用户名或密码错误");
			
			return "loginFail";
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/*
	 * 修改密码
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
	 * 更新session中的账户信息
	 */
	
	public void refreshsession() throws Exception {
		
		if(ud.getUserbyName(user)) {
			session.put("user", user);
		}
	}
	
	/*
	 * 修改用户信息
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
	 * 忘记密码发邮件
	 */
	public String forgot() throws Exception {
		//凭借用户名获取信息
		
		if(ud.getUserbyName(user)) {

			System.out.println(user.getUname());
			String text="你的密码是： "+user.getUpass()+" 不要再弄丢了哦";
			System.out.println(text);
	    	EmailTest et= new EmailTest();
	    	et.testSendEmail(text,user.getEmail());
		}
		return SUCCESS;
		
		
	}
	/*
	 * 安全退出
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
