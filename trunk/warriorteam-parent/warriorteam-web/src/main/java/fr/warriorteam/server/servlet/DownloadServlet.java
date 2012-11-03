package fr.warriorteam.server.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.warriorteam.server.utils.PropertiesUtils;
import fr.warriorteam.server.utils.ZipFileWriter;

public class DownloadServlet extends WTServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8841695241975408617L;

	private static final int BUFSIZE = 2048;

	private final Logger logger = Logger.getLogger(DownloadServlet.class);

	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String categorieName = request.getParameter("path");
		// String filename = creerZip(categorieName);

		categorieName = PropertiesUtils.getProperties("path_file_images")
				+ categorieName;

		try {
			doDownload(request, response, categorieName, categorieName + ".zip");

		} catch (Exception e) {
			logger.error("Erreur lors du download du fichier "
					+ e.getStackTrace());
			throw new RuntimeException(e);
		}

	}

	private String creerZip(String pathName) {

		String zipName = pathName + "_zip.zip";

		try {
			ZipFileWriter zip = new ZipFileWriter(
					PropertiesUtils.getProperties("path_file_images") + zipName);

			File file = new File(
					PropertiesUtils.getProperties("path_file_images")
							+ pathName);
			for (File f : file.listFiles()) {
				if (!f.getName().endsWith("resize")) {
					zip.addFile(PropertiesUtils
							.getProperties("path_file_images")
							+ pathName
							+ "/"
							+ f.getName());
				}
			}

			zip.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zipName;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Sends a file to the ServletResponse output stream. Typically you want the
	 * browser to receive a different name than the name the file has been saved
	 * in your local database, since your local names need to be unique.
	 * 
	 * @param req
	 *            The request
	 * @param resp
	 *            The response
	 * @param filename
	 *            The name of the file you want to download.
	 * @param original_filename
	 *            The name the browser should receive.
	 */
	private void doDownload(HttpServletRequest req, HttpServletResponse resp,
			String filename, String original_filename) throws IOException {
		File f = new File(filename);
		int length = 0;
		ServletOutputStream op = resp.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(filename);

		//
		// Set the response and go!
		//
		//
		resp.setContentType((mimetype != null) ? mimetype
				: "application/octet-stream");
		resp.setContentLength((int) f.length());
		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ original_filename + "\"");

		//
		// Stream to the requester.
		//
		byte[] bbuf = new byte[BUFSIZE];
		DataInputStream in = new DataInputStream(new FileInputStream(f));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();
	}
}
