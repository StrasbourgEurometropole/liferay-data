package eu.strasbourg.picker.taglib;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;

import eu.strasbourg.utils.DLFileEntryHelper;

public class FileObject {

	private Long _id;
	private String _title;
	private String _url;
	private String _fileName;
	
	public FileObject(Long fileEntryId) {
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		if (file != null) {
			this._id = file.getFileEntryId();
			this._url = DLFileEntryHelper.getFileEntryURL(file);
			this._title = file.getTitle();
			this._fileName = file.getFileName();
		}
	}

	public Long getId() {
		return _id;
	}
	public void setId(Long id) {
		this._id = id;
	}
	public String getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		this._title = title;
	}
	public void setFileName(String fileName) {
		this._fileName = fileName;
	}
	public String getFileName() {
		return _fileName;
	}
	public String getUrl() {
		return _url;
	}
	public void setUrl(String url) {
		this._url = url;
	}
	
}
