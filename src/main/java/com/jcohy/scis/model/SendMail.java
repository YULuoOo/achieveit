package com.jcohy.scis.model;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

	private Properties props;
	private Session mailSession;
	private MimeMessage mimeMsg;


	public SendMail(String SMTPHost, String Port, String MailUsername, String MailPassword) {
		Auth au = new Auth(MailUsername, MailPassword);

		props = System.getProperties();
		props.put("mail.smtp.host", SMTPHost);
		props.put("mail.smtp.port", Port);
		props.put("mail.smtp.auth", "true");

		mailSession = Session.getInstance(props, au);
	}
  
	public boolean sendingMimeMail(String MailFrom, String MailTo,
			String MailCopyTo, String MailBCopyTo, String MailSubject,
			String MailBody) {
		try { 

			mimeMsg = new MimeMessage(mailSession);

			mimeMsg.setFrom(new InternetAddress(MailFrom));

			if (MailTo != null) {
				mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(MailTo));
			} 

			if (MailCopyTo != null) {
				mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC,
						InternetAddress.parse(MailCopyTo));
			}

			if (MailBCopyTo != null) {
				mimeMsg.setRecipients(javax.mail.Message.RecipientType.BCC,
						InternetAddress.parse(MailBCopyTo));
			}

			mimeMsg.setSubject(MailSubject, "gb2312");

			mimeMsg.setContent(MailBody, "text/html;charset=gb2312");

			Transport.send(mimeMsg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
