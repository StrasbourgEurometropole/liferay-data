package eu.strasbourg.service.objtp.service.util;

import java.util.ArrayList;

import com.liferay.portal.kernel.util.Validator;


public class ImportReportLineObjtp {
	
	private String number;
	private String logs;
	private int status;
	
	public ImportReportLineObjtp() {
		this.setStatus(ImportReportStatusObjtp.SUCCESS);
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getLogs() {
		return logs;
	}
	public void setLogs(String logs) {
		this.logs = logs;
	}
	
	/*
	 * Création et modification du log des erreurs
	 */
	public void error(String cause) {
		if (Validator.isNotNull(this.getLogs())) {
			this.setLogs(this.getLogs() + ", " + cause);
		} else {
			this.setLogs(cause);
		}
		this.setStatus(ImportReportStatusObjtp.FAILURE);
	}
	
}
