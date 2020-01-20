package eu.strasbourg.service.office.exporter.api;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.comment.model.Comment;

import java.io.OutputStream;
import java.util.List;

@ProviderType
public interface CommentsXlsxExporter {
	
	public void exportComments(OutputStream stream, long groupId);
	
	public void exportComments(OutputStream stream, List<Comment> CommentsXlsxExporterents);
}
