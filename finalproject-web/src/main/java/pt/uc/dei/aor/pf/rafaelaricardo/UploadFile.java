package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class UploadFile implements Serializable {

	private static final long serialVersionUID = -5537671907313363474L;

	private static final Logger log = LoggerFactory.getLogger(UploadFile.class);

	private Part file;
	private String path;
	private String localPath;
	private String fileType;
	private String diferentName;
	private String typeUser;

	public String upload(String diferentName, String fileType, String typeUser) {
		this.diferentName = diferentName;
		this.fileType = fileType;
		this.typeUser = typeUser;
		this.localPath = generatePath(diferentName, fileType);
		log.info("Uploading file: " + fileType);

		try {
			if (validExtension(localPath)) {
				file.write(localPath);
				path = generateServerPath(this.diferentName, this.fileType,
						this.typeUser);
				return localPath;
			} else {
				String errorMsg = "The format of file is not valid!";
				log.error(errorMsg);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
								null));
				return null;
			}
		} catch (IOException ioe) {
			String errorMsg = "Error in the file upload: " + ioe.getMessage();
			log.error(errorMsg);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg,
							null));
			return null;
		}
	}

	private boolean validExtension(String path) {

		if (path.substring(path.length() - 3).equals("pdf")) {
			return true;
		} else if (path.substring(path.length() - 4).equals("xlsx")) {
			return true;
		} else {
			return false;
		}
	}

	public String generatePath(String diferent, String fileType) {
		localPath = System.getProperty("jboss.home.dir")
				+ "\\ProjFinalUploadedFiles\\" + fileType + "\\" + fileType
				+ "_" + diferentName + "_" + getCurrentTimeStamp() + "_"
				+ getFilename(file);
		return localPath;
	}

	private String generateServerPath(String diferentName, String fileType,
			String tipeUser) {
		return "\\" + tipeUser + "\\" + fileType + "_" + diferentName + "_"
				+ getCurrentTimeStamp() + "_" + getFilename(file);
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1);
			}
		}
		return null;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getTypeFile() {
		return fileType;
	}

	public void setTypeFile(String typeFile) {
		this.fileType = typeFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDiferentName() {
		return diferentName;
	}

	public void setDiferentName(String diferentName) {
		this.diferentName = diferentName;
	}

	public String getTipeUser() {
		return typeUser;
	}

	public void setTipeUser(String tipeUser) {
		this.typeUser = tipeUser;
	}

}
