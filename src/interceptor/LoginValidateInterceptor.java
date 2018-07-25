package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class LoginValidateInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//通过核心调度器invocation来获得调度的Action上下文
		ActionContext actionContext=arg0.getInvocationContext();
		//获得session对象
		Map<String,Object>session =actionContext.getSession();
		//从session中取出名为user的session属性
		String user=(String)session.get("userName");
	
		if(user!= null) {
			return arg0.invoke();
			
		}else{
			
			//返回全局视图"nologon"
			return "nologin";			
			
		}
	
	}

}
