package eu.strasbourg.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

public class MailHelper {

	/**
	 * Envoie un mail en mode "plain text"
	 * 
	 * @param from
	 *            Adresse de l'expéditeur
	 * @param to
	 *            Addresses du ou des destinataires, séparées par des virgules
	 *            si elles sont plusieurs
	 * @param subject
	 *            Sujet du mail
	 * @param body
	 *            Body du mail
	 * @return True si le mail a correctement été envoyé, false sinon
	 */
	public static boolean sendMailWithPlainText(String from, String to,
		String subject, String body) {
		InternetAddress fromAddress = null;
		InternetAddress[] toAddresses = new InternetAddress[0];

		try {
			fromAddress = new InternetAddress(from);
			for (String toAddress : to.split(",")) {
				try {
					InternetAddress address = new InternetAddress(toAddress);
					toAddresses = ArrayUtil.append(toAddresses, address);
				} catch (AddressException ex) {
					log.error(ex);
				}
			}

			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(toAddresses);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			MailServiceUtil.sendEmail(mailMessage);
			return true;
		} catch (AddressException e) {
			log.error(e);
			return false;
		}
	}
	
	/**
	 * Envoie un mail en mode "plain text"
	 * 
	 * @param from
	 *            Adresse de l'expéditeur
	 * @param to
	 *            Addresses du ou des destinataires, séparées par des virgules
	 *            si elles sont plusieurs
	 * @param subject
	 *            Sujet du mail
	 * @param body
	 *            Body du mail
	 * @return True si le mail a correctement été envoyé, false sinon
	 */
	public static boolean sendMailWithHTML(String from, String to,
		String subject, String body) {
		InternetAddress fromAddress = null;
		InternetAddress[] toAddresses = new InternetAddress[0];

		try {
			fromAddress = new InternetAddress(from);
			for (String toAddress : to.split(",")) {
				try {
					InternetAddress address = new InternetAddress(toAddress);
					toAddresses = ArrayUtil.append(toAddresses, address);
				} catch (AddressException ex) {
					log.error(ex);
				}
			}

			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(toAddresses);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			return true;
		} catch (AddressException e) {
			log.error(e);
			return false;
		}
	}

	private static final Log log = LogFactoryUtil
		.getLog(MailHelper.class.getName());
}
