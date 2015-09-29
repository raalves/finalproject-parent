package pt.uc.dei.aor.pf.rafaelaricardo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
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

	public String generateMimeType(String path) {

		if ((path.substring(path.length() - 4).equalsIgnoreCase("xlsx"))) {
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		} else if ((path.substring(path.length() - 3).equalsIgnoreCase("pdf"))) {
			return "application/pdf";
		} else {
			log.error("This file type is not correct");
			return null;
		}

	}

	// public void prepareFile(String path, String downloadName) {
	// this.path = path;
	// this.downloadName = downloadName;
	//
	// System.out.println(path);
	// System.out.println(" mm " + mimeType + " down " + downloadName);
	// }

	public DownloadFile(String downloadName, String path) {
		this.downloadName = downloadName;
		this.path = path;
		this.mimeType = generateMimeType(this.path);
		log.info("Downloading: " + downloadName);
		try {
			// System.out.println(file.getName());
			System.out.println(" mm " + mimeType + " down " + downloadName);
			InputStream stream = new FileInputStream(this.path);
			file = new DefaultStreamedContent(stream, this.mimeType,
					this.downloadName);
			System.out.println(file.getName());
			System.out.println(stream.toString() + " mm " + mimeType + " down "
					+ downloadName);
		} catch (Exception e) {
			log.error("File not found: " + e.getMessage());
			e.printStackTrace();
		}

	}

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