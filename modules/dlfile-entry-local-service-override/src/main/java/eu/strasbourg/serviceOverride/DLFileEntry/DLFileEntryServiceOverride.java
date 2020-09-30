package eu.strasbourg.serviceOverride.DLFileEntry;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
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

import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.MemoryCacheImageOutputStream;


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

		// Si title deja dans la lib, return
		DLFileEntry entry = DLFileEntryLocalServiceUtil.fetchFileEntry(groupId, folderId, title);
		if (entry == null && mimeType.equals("image/jpeg")) {
			try {
				RenderedImage image = ImageIO.read(is);
				// Calcul ratio de compression
				int height = image.getHeight();
				int width = image.getWidth();
				float compressionRatio = (float) width * height / size;

				if (height > 1920 || width > 1920) {
					// Scaling
					image = ImageToolUtil.scale(image, 1920, 1920);
					// Compression
					ByteArrayOutputStream baos = compressImage(image);
					// Remplacement de l'image
					is = new ByteArrayInputStream(baos.toByteArray());
					size = baos.size();
				}
				else if (compressionRatio < 7) {
					// Compression
					ByteArrayOutputStream baos = compressImage(image);
					// Calcul du nouveau pourcentage de compression
					float newCompressionRatio = 100 * (float)baos.size()/size;
					// Remplacement de l'image si le pourcentage de compression est satisfaisant
					if (newCompressionRatio < 90) {
						is = new ByteArrayInputStream(baos.toByteArray());
						size = baos.size();
					}
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

	private ByteArrayOutputStream compressImage(RenderedImage image) throws IOException {
		// Configuration des parametres de compression
		ImageWriter jpgWriter = ImageIO.getImageWritersByMIMEType("image/jpeg").next();
		ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
		jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpgWriteParam.setCompressionQuality(0.88f);
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