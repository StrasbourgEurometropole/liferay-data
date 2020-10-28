package eu.strasbourg.serviceOverride.DLFileEntry;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.portal.kernel.image.ImageToolUtil;

import org.osgi.service.component.annotations.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
/**
 * @author louis.politanski
 */
@Component(
	immediate = true,
	property = {
	},
	service = ServiceWrapper.class
)
public class DLFileEntryServiceOverride extends DLFileEntryLocalServiceWrapper {

	public DLFileEntryServiceOverride() {
		super(null);
	}

	@Override
	public DLFileEntry addFileEntry(long userId, long groupId, long repositoryId, long folderId, String sourceFileName,
									String mimeType, String title, String description, String changeLog,
									long fileEntryTypeId, Map<String,DDMFormValues> ddmFormValuesMap,
									File file, InputStream is, long size, ServiceContext serviceContext)
									throws PortalException {

		_log.error("DLFILEENTRY DDMFormValuesMap : "+ddmFormValuesMap.toString());

		// On verifie ou se trouve le document d'entree
		boolean imageInFile;
		if (file != null) { imageInFile = true; }
		else if (is != null) { imageInFile = false; }
		else {
			return super.addFileEntry(userId, groupId, repositoryId, folderId, sourceFileName, mimeType, title, description,
					changeLog, fileEntryTypeId, ddmFormValuesMap, file, is, size, serviceContext);
		}
		// Si title deja dans la lib, return
		DLFileEntry entry = DLFileEntryLocalServiceUtil.fetchFileEntry(groupId, folderId, title);
		if (entry == null && mimeType.equals("image/jpeg")) {
			try {
				_log.error("Image JPEG detectee");
				// Lecture de l'image
				RenderedImage image;
				image = readImage(imageInFile, is, file);
				// Calcul ratio de compression
				int height = image.getHeight();
				int width = image.getWidth();
				float compressionRatio = (float) width * height / size;
				if (height > 1920 || width > 1920) {
					_log.error("Scaling et compression");
					// Scaling
					image = ImageToolUtil.scale(image, 1920, 1920);
					// Compression
					float compressionQuality = 0.88f;
					ByteArrayOutputStream baos = compressImage(image, compressionQuality);
					// Remplacement de l'image
					Path path = Paths.get(file.getPath());
					is = saveImage(imageInFile, baos, path);
					size = getNewSize(imageInFile, baos, path);
				}
				else if (compressionRatio < 7) {
					_log.error("Compression");
					// Compression
					float compressionQuality = 0.88f;
					ByteArrayOutputStream baos = compressImage(image, compressionQuality);
					// Calcul du nouveau pourcentage de compression
					float newCompressionRatio = 100 * (float)baos.size()/size;
					// Remplacement de l'image si le pourcentage de compression est satisfaisant
					if (newCompressionRatio < 90) {
						Path path = Paths.get(file.getPath());
						is = saveImage(imageInFile, baos, path);
						size = getNewSize(imageInFile, baos, path);
					}
				}
			}
			catch (IOException e)
			{ _log.error(e); } catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (entry == null && mimeType.equals("image/png") && size > 500000) { //
			_log.error("PNG lourd detecte");
			try {
				// Lecture de l'image
				RenderedImage image;
				image = readImage(imageInFile, is, file);
				int height = image.getHeight();
				int width = image.getWidth();
				// Scaling
				if (height > 1920 || width > 1920) {
					_log.error("Scaling");
					image = ImageToolUtil.scale(image, 1920, 1920);
				}
				// Elimination de la transparence du PNG
				BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
				newImg.createGraphics().drawImage((Image) image, 0, 0, Color.WHITE, null);
				image = newImg;
				// Compression
				float compressionQuality = 0.88f;
				ByteArrayOutputStream baos = compressImage(image, compressionQuality);
				// Calcul du nouveau pourcentage de compression
				float newCompressionRatio = 100 * (float)baos.size()/size;
				// Remplacement de l'image si le pourcentage de compression est satisfaisant
				if (newCompressionRatio < 90) {
					_log.error("On remplace le PNG.");
					Path path = Paths.get(file.getPath());
					is = saveImage(imageInFile, baos, path);
					size = getNewSize(imageInFile, baos, path);
					// Modif extension fichier
					mimeType = "image/jpeg";
					if (sourceFileName.endsWith(".png")) {
						sourceFileName = sourceFileName.substring(0, sourceFileName.length()-4) + ".jpg";
						_log.error("New filename:"+sourceFileName);
					}
					if (title.endsWith(".png")) {
						title = title.substring(0, title.length()-4) + ".jpg";
						_log.error("New title:"+title);
					}
				}
				else {
					_log.error("Compression insatisfaisante, on garde le PNG.");
				}
			}
			catch (IOException e)
			{ _log.error(e); } catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.addFileEntry(userId, groupId, repositoryId, folderId, sourceFileName, mimeType, title, description,
				changeLog, fileEntryTypeId, ddmFormValuesMap, file, is, size, serviceContext);
	}

	private RenderedImage readImage(boolean imageInFile, InputStream is, File file) throws IOException {
		RenderedImage image;
		if (!imageInFile) {
			image = ImageIO.read(is);
		}
		else {
			image = ImageIO.read(file);
		}
		return image;
	}

	private InputStream saveImage(boolean imageInFile, ByteArrayOutputStream baos, Path path)  throws IOException {
		if (imageInFile) {
			Files.write(path, baos.toByteArray());
			return null;
		}
		else {
			return new ByteArrayInputStream(baos.toByteArray());
		}
	}

	private long getNewSize(boolean imageInFile, ByteArrayOutputStream baos, Path path) throws IOException {
		if (imageInFile) { return baos.size(); }
		else { return Files.size(path); }
	}

	private ByteArrayOutputStream compressImage(RenderedImage image, float quality) throws IOException {
		// Configuration des parametres de compression
		ImageWriter jpgWriter = ImageIO.getImageWritersByMIMEType("image/jpeg").next();
		ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
		jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpgWriteParam.setCompressionQuality(quality);
		// Configuration de l'output de compression
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		jpgWriter.setOutput(new MemoryCacheImageOutputStream(baos));
		IIOImage outputImage = new IIOImage(image, null, null);
		// Compression
		jpgWriter.write(null, outputImage, jpgWriteParam);
		baos.flush();
		jpgWriter.dispose();
		return baos;
	}
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}