package Utils;
//package net.codejava.networking;
//code from http://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A utility that downloads a file from a URL.
 * @author www.codejava.net
 *
 */
public class HTTPDownload {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static int downloadFile(String fileURL, String saveDir,String saveName)
            throws IOException {
    	int contentLength=0;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0");
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = saveName;
            String contentType = httpConn.getContentType();
            contentLength = httpConn.getContentLength();
            
            if(saveName==null) {
                String disposition = httpConn.getHeaderField("Content-Disposition");
	            if (disposition != null) {
	                // extracts file name from header field
	                int index = disposition.indexOf("filename=");
	                if (index > 0) {
	                    fileName = disposition.substring(index + 10,
	                            disposition.length() - 1);
	                }
	            } else {
	                // extracts file name from URL
	                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
	                        fileURL.length());
	            }
            }

            System.out.println("Content-Type = " + contentType);
//            System.out.println("Content-Disposition = " + disposition);
//            System.out.println("Content-Length = " + contentLength);
//            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            boolean checkHeader=true;
            String szHeader="d8:announce";
            byte[] header=szHeader.getBytes();
            boolean headerVerified=false;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
            	if(checkHeader && bytesRead>=header.length) {
            		headerVerified=true;
            		for(int i=0;i<header.length;i++) {
            			if(header[i]!=buffer[i]) {
            				headerVerified=false;
            				System.out.println("BT header check failed");
            				break;
            			}
            		}
            	}
            	checkHeader=false;
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();
            if(headerVerified)
            	System.out.println("File downloaded");
            else contentLength=0;
            	
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        return contentLength;
    }
}