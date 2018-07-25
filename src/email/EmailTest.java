
package email;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailTest {

	public static final String SMTPSERVER = "smtp.163.com";
    public static final String SMTPPORT = "465";
    public static final String ACCOUT = "m15602569291@163.com";
    public static final String PWD = "a1505924551";

   
    public void testSendEmail(String subject,String text,String email) throws Exception {

    	
        // �����ʼ�����
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", SMTPSERVER); // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.port", SMTPPORT); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������֤
        props.setProperty("mail.smtp.ssl.enable", "true");// ����ssl


        // �����ʼ����ô����Ự��ע��session�𵼴��
        Session session = Session.getDefaultInstance(props);
        // ����debugģʽ�����Կ���������ϸ��������־
        session.setDebug(true);
        //�����ʼ�
        MimeMessage message = createEmail(session,text,email,subject);
        //��ȡ����ͨ��
        Transport transport = session.getTransport();
        transport.connect(SMTPSERVER,ACCOUT, PWD);
        //���ӣ��������ʼ�
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }

/*
    private email.MimeMessage createEmail(email.Session session, String text) {
		// TODO Auto-generated method stub
		return null;
	}
*/

	public MimeMessage createEmail(Session session,String text,String email,String subject) throws Exception {
    	
       	
        // ���ݻỰ�����ʼ�
        MimeMessage msg = new MimeMessage(session);
        // address�ʼ���ַ, personal�ʼ��ǳ�, charset���뷽ʽ
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "�ͻ�����ϵͳ", "utf-8");
        // ���÷����ʼ���
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                email, "user", "utf-8");
        // �����ʼ����շ�
        msg.setRecipient(RecipientType.TO, receiveAddress);
        // �����ʼ�����
        msg.setSubject(subject, "utf-8");
        msg.setText(text);
        // ������ʾ�ķ���ʱ��
        msg.setSentDate(new Date());
        // ��������
        msg.saveChanges();
        return msg;
    }
  /*  public static void main(String []arg) throws Exception {
    	
    	EmailTest et= new EmailTest();
    	et.testSendEmail("6666666");
    }
    
    */
}

