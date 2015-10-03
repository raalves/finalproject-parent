package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@RequestScoped
public class DownloadFile implements Serializable {

	private static final long serialVersionUID = 5762846438465951042L;
	private String path;
	private String downloadName;
	private StreamedContent file;
	private String mimeType;
	private static final Logger log = LoggerFactory
			.getLogger(DownloadFile.class);

	public DownloadFile() {
	}

	public void generateMimeType(String path) {

		if ((path.substring(path.length() - 4).equalsIgnoreCase("xlsx"))) {
			this.mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			this.downloadName = "guide.xlsx";
		} else if ((path.substring(path.length() - 3).equalsIgnoreCase("pdf"))) {
			this.mimeType = "application/pdf";
			this.downloadName = "cv.pdf";
		} else {
			log.error("This file type is not correct");

		}

	}

	public void prepare(String path) {

		this.path = path;
		generateMimeType(this.path);

		try {
			log.info("Downloading file: " + downloadName);
			InputStream stream = new FileInputStream(this.path);

			file = new DefaultStreamedContent(stream, this.mimeType,
					this.downloadName);

		} catch (Exception e) {
			log.error("File not found: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public StreamedContent getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
