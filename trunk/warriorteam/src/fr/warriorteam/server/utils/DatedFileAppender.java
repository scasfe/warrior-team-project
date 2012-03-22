package fr.warriorteam.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.RollingFileAppender;

public class DatedFileAppender extends RollingFileAppender {

	private String datePattern = "yyyy-MM-dd";

	public void setFile(String strFile) {
		String strDate;
		SimpleDateFormat sdf;

		if (datePattern != null && strFile != null) {
			sdf = new SimpleDateFormat(datePattern);
			strDate = sdf.format(new Date());
			fileName = strFile.replaceAll("%date%", strDate);
		} else {
			System.err
					.println("Either File or DatePattern options are not set for appender ["
							+ name + "].");
		}
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
}