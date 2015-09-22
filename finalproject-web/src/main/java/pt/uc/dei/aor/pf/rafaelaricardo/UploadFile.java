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

	private Part cvFile;

	private String cvPath;
	private Part coverLetterFile;

	private String coverLetterPath;
	private String localPath;
	private String typeFile = "CV";

	public String upload(String email) {
		log.info("Uploading CV file");

		try {
			if (validExtension(localPath)) {
				cvFile.write(localPath);
				cvPath = generateServerPath(email, typeFile);
				System.out.println(localPath + "  Localpath");
				return localPath;
				// if (coverLetterFile.getSize()!=0) {
				// uploadCoverLetter()
				// }
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

	// public String deleteUploadedFile() {
	// try {
	// cvFile.delete();
	// return "file deleted";
	// } catch (IOException ie) {
	// ie.getMessage();
	// return "error while deleting file " + ie.getMessage();
	// }
	//
	// }

	private boolean validExtension(String path) {

		String extension = path.substring(path.length() - 3);

		if (extension.equals("pdf") || extension.equals("doc")
				|| extension.equals("docx"))
			return true;

		return false;
	}

	public String generatePath(String email) {
		System.out.println(typeFile + " typefile");
		localPath = System.getProperty("jboss.home.dir")
				+ "\\ProjFinalUploadedFiles\\" + typeFile + "_" + email + "_"
				+ getCurrentTimeStamp() + "_" + getFilename(cvFile);
		return localPath;
	}

	private String generateServerPath(String email, String typeFile) {
		return "\\candidate\\" + typeFile + "_" + email + "_"
				+ getCurrentTimeStamp() + "_" + getFilename(cvFile);
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

	public Part getCvFile() {
		return cvFile;
	}

	public void setCvFile(Part cvFile) {
		this.cvFile = cvFile;
	}

	public String getCvPath() {
		return cvPath;
	}

	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}

	public Part getCoverLetterFile() {
		return coverLetterFile;
	}

	public void setCoverLetterFile(Part coverLetterFile) {
		this.coverLetterFile = coverLetterFile;
	}

	public String getCoverLetterPath() {
		return coverLetterPath;
	}

	public void setCoverLetterPath(String coverLetterPath) {
		this.coverLetterPath = coverLetterPath;
	}

}
