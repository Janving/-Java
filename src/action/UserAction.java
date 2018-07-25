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
	//标记为，为0判断用户名是否可用；为1注册
	private String flag;

	private User user = new User();
	private String reupass;
	private Map<String,Object> request;
	private Map<String,Object> session;
	UserDao ud= new UserDao();
	
	//邮箱验证码
	private String eName;
	private String eIden;
	
	/*
	 * 预备
	 */
	public String preregister() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();  
		String path=request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
		
		String iden=MyUtil.getStringID();//生成邮箱识别码
		user.setIden(iden);
		application.put(user.getUname(), user);
			
		String text="请点击以下连接来验证你的邮箱"+ basePath+"user/confirm.action?eName="+user.getUname()+"&eIden="+user.getIden();
		EmailTest et= new EmailTest();
	    et.testSendEmail("验证邮箱",text,user.getEmail());
	    	
		return "preregister";
			
			
		
	}
	
	/*
	 * 注册验证
	 */
	
	public String confirm() throws Exception {
		if(eName==null|ud.isExit(user.getUname())){
			return "confirmfail";
		}
		 if(application.get(eName)!=null) {	 
			 user=(User) application.get(eName); 
			 if(user.getIden().equals(eIden)) {
				 //如果验证码相等，则注入数据库
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
				request.put("upass",user.getUpass());
				request.put("email",user.getEmail());
				request.put("reupass",reupass);
				
				return "register";
			}else {
				
				String pre=preregister();//发送邮件
				return pre;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	
	}
	
	

	
	
	
	/*
	 * 登陆
	 */
	public String login() {
		try {
	
			
			if(ud.isLogin(user)){
			    session.put("uemail", user.getEmail());
				session.put("userName",user.getUname());
				session.put("userPWD", user.getUpass());
				session.put("user",user);
			  
				//录入登记时间记录
				ud.isLoginRecord(user.getUname());
				int userCount=ud.userCount();
				int cardCount=ud.cardCount();
				int lastdayCount=ud.lastdayCount();
				int lastdayOrderItem=ud.lastdayOrderItem();
				//存放用户客户总数
				application.put("userCount", userCount);
				application.put("cardCount", cardCount);
				application.put("lastdayCount",lastdayCount);
				application.put("lastdayOrderItem",lastdayOrderItem);
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

			
			String text="你的密码是： "+user.getUpass()+" 不要再弄丢了哦";
		
	    	EmailTest et= new EmailTest();
	    	et.testSendEmail("找回密码",text,user.getEmail());
		}
		return SUCCESS;
		
		
	}
	/*
	 * 安全退出
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
