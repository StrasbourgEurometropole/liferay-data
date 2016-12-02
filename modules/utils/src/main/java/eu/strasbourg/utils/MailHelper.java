package eu.strasbourg.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;

public class MailHelper {
	public static boolean sendMailWithPlainText(String from, String to,
		String subject, String body) {
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			MailServiceUtil.sendEmail(mailMessage);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sendMailWithHTML(String from, String to,
		String subject, String body) {
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		}
	}
}
