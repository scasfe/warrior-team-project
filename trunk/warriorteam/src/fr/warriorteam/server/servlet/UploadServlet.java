package fr.warriorteam.server.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import fr.warriorteam.rpc.impl.FileUploadServiceImpl;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8841695241975408617L;
	
	private final Logger logger = Logger.getLogger(UploadServlet.class);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException { 
        ServletFileUpload upload = new ServletFileUpload(); 
 
        try{ 
            FileItemIterator iter = upload.getItemIterator(request); 
 
            while (iter.hasNext()) { 
                FileItemStream item = iter.next(); 
                
                String name = item.getFieldName(); 
                InputStream stream = item.openStream(); 
 
                if(!item.getName().matches("^.*\\.zip$")){
 
                	logger.info("Ajout du fichier : "+item.getName());
                	
                File file = new File("../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"+item.getName());
                // File a laquelle rattacher le output stream
                FileOutputStream fos = new FileOutputStream(file);

                int len; 
                byte[] buffer = new byte[8192]; 
                while ((len = stream.read(buffer, 0, buffer.length)) != -1) { 
                	fos.write(buffer, 0, len); 
                } 
                
                }else{
                	logger.info("Ajout du fichier ZIP: "+item.getName());
                	BufferedOutputStream dest = null;
                	byte[] BUFFER = new byte[8192]; 
                	ZipInputStream zis = new ZipInputStream(stream); 
                	   ZipEntry entree; 
                	   int count;
                	   while((entree = zis.getNextEntry()) != null)
                	   {
                		   logger.info("Ajout du fichier contenu dans le ZIP : "+entree.toString());
                	   
                	   
                	    
                	    File file = new File("../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"+entree.toString());
                        // File a laquelle rattacher le output stream
                        FileOutputStream fos = new FileOutputStream(file);

                        int len; 
                        byte[] buffer = new byte[8192]; 
                        while ((len = zis.read(buffer, 0, buffer.length)) != -1) { 
                        	fos.write(buffer, 0, len); 
                        }
                        
//                        zis.close();
//                        fos.close();

                	   }

                	
                }
                
                
            } 
        } 
        catch(Exception e){ 
        	logger.error("Erreur lors de l'upload du fichier "+e.getStackTrace());
            throw new RuntimeException(e); 
        } 
 
    } 


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

}