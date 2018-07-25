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
		//ͨ�����ĵ�����invocation����õ��ȵ�Action������
		ActionContext actionContext=arg0.getInvocationContext();
		//���session����
		Map<String,Object>session =actionContext.getSession();
		//��session��ȡ����Ϊuser��session����
		String user=(String)session.get("userName");
	
		if(user!= null) {
			return arg0.invoke();
			
		}else{
			
			//����ȫ����ͼ"nologon"
			return "nologin";			
			
		}
	
	}

}
