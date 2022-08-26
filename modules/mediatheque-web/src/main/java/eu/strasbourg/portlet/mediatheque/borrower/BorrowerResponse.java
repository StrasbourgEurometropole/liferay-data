package eu.strasbourg.portlet.mediatheque.borrower;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

public class BorrowerResponse {

	public String result;
	public JSONObject erreur_optionnel;
	public String email;
	public String err;
	public String count;
	public String erreur;
	public String code_erreur;

	public String cardNumber;
	public LocalDate expireDate;
	public List<Media> borrowings;
	public List<Media> reservations;

	public String err_class;
	public String err_desc;
	public String data;

	public BorrowerResponse(JSONObject json) {
		result = json.getString("result");
		erreur_optionnel = json.getJSONObject("erreur_optionnel");
		if (erreur_optionnel != null) {
			email = erreur_optionnel.getString("email");
			cardNumber = erreur_optionnel.getString("borrower_id");
		}
		err = json.getString("err");
		count = json.getString("count");
		erreur = json.getString("erreur");
		code_erreur = json.getString("code_erreur");

		err_class = json.getString("err_class");
		err_desc = json.getString("err_desc");
		data = json.getString("data");
	}

	public BorrowerResponse(String xml) {
		code_erreur = null;
		Document doc;
		try {
			doc = SAXReaderUtil.read(xml);
			Element borrower = doc.getRootElement().element("Borrower");
			cardNumber = borrower.element("General").element("OriginalBarcode").getText();
			if (Validator.isNotNull(borrower.element("General").element("ExpiryDate"))) {
				expireDate = LocalDate.parse(borrower.element("General").element("ExpiryDate").getText(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}
			borrowings = new ArrayList<Media>();
			List<Element> loans = borrower.element("Loans").elements("Loan");
			for (Element loan : loans) {
				borrowings.add(new Media(loan));
			}
			List<Media> borrowingsSorted = new ArrayList<Media>();
			// tri par date les emprunts
			borrowingsSorted.addAll(borrowings.stream().filter(b -> Validator.isNotNull(b.getReturnDate()))
					.sorted((l1, l2) -> l1.getReturnDate().compareTo(l2.getReturnDate())).collect(Collectors.toList()));
			// affiche les emprunts sans date en dernier
			borrowingsSorted.addAll(
					borrowings.stream().filter(b -> Validator.isNull(b.getReturnDate())).collect(Collectors.toList()));

			reservations = new ArrayList<Media>();
			List<Element> resas = borrower.element("Reservations").elements("Reservation");
			for (Element reservation : resas) {
				List<Element> items = reservation.element("Items").elements("Item");
				for (Element item : items) {
					reservations.add(new Media(item));
				}
			}
			List<Media> reservationsSorted = new ArrayList<Media>();
			// tri par date les réservations
			reservationsSorted.addAll(reservations.stream().filter(b -> Validator.isNotNull(b.getRequestDate()))
					.sorted((l1, l2) -> l1.getRequestDate().compareTo(l2.getRequestDate()))
					.collect(Collectors.toList()));
			// affiche les réservations sans date en dernier
			reservationsSorted.addAll(reservations.stream().filter(b -> Validator.isNull(b.getRequestDate()))
					.collect(Collectors.toList()));
		} catch (DocumentException e) {
			_log.error(e.getMessage() + " : " + xml);
		}
	}

	public BorrowerResponse() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public List<Media> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(List<Media> borrowings) {
		this.borrowings = borrowings;
	}

	public List<Media> getReservations() {
		return reservations;
	}

	public void setReservations(List<Media> reservations) {
		this.reservations = reservations;
	}

	public String getErr_class() {
		return err_class;
	}

	public void setErr_class(String err_class) {
		this.err_class = err_class;
	}

	public String getErr_desc() {
		return err_desc;
	}

	public void setErr_desc(String err_desc) {
		this.err_desc = err_desc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCode_erreur() {
		return code_erreur;
	}

	public void setCode_erreur(String code_erreur) {
		this.code_erreur = code_erreur;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final Log _log = LogFactoryUtil.getLog(BorrowerResponse.class.getName());
}
