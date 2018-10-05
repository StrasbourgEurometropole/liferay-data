package eu.strasbourg.portlet.mediatheque.borrower;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

public class Media {

	public String name;
	public String type;
	public LocalDate returnDate;
	public LocalDate requestDate;
	
	public Media(Element element) {
	    	name = element.element("Title").getText();
	    	type = element.element("MaterialType").getText();
	    	if(Validator.isNotNull(element.element("DueDate"))) {
	    		returnDate = LocalDate.parse(element.element("DueDate").getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    	}
	    	if(Validator.isNotNull(element.element("RequestDateTime"))) {
	    		requestDate = LocalDate.parse(element.element("RequestDateTime").getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss"));
	    	}
	}

	public Media() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

}
