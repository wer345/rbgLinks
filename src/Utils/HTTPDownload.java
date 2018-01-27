package Utils;
//package net.codejava.networking;
//code from http://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility that downloads a file from a URL.
 * @author www.codejava.net
 *
 */
public class HTTPDownload {
    private static final int BUFFER_SIZE = 4096;

    public static byte[] downloadFileData(String fileURL) throws IOException
    {
    	int contentLength=0;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0");
        int responseCode = httpConn.getResponseCode();
        byte[] content=null;

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String contentType = httpConn.getContentType();
            contentLength = httpConn.getContentLength();

            // "application/x-bittorrent" for torrent
            // When no more torrent , the type is "text/html" witn contentLength -1
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Length = " + contentLength);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            int bytesRead = -1;
            if(contentLength>0) {
	            content = new byte[contentLength];
	            int contentRead=0;
	            int spaceLeft=contentLength;
	            while (spaceLeft>0 && (bytesRead = inputStream.read(content,contentRead,spaceLeft)) != -1) {
	            	contentRead+=bytesRead;
	            	spaceLeft-=bytesRead;
	            }
	            if(spaceLeft==0)
	            	System.out.println("File downloaded");
	            else
	            	content=null;
            }
            else {
            	ByteArrayOutputStream c= new ByteArrayOutputStream();

            	byte[] buffer= new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	            	c.write(buffer,0,bytesRead);
	            }
	            c.flush();
	            content=c.toByteArray();
            }

            inputStream.close();

        } else {
            System.out.println("**ERROR: Server replied HTTP code: " + responseCode+" for url "+fileURL);
        }
        httpConn.disconnect();
        return content;
    }
    /**
     * Downloads a file from a URL
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static int downloadFile(String fileURL, String saveDir,String saveName)
            throws IOException {
    	byte[] content = downloadFileData(fileURL);
    	if(content==null)
    		return 0;

        String szHeader="d8:announce";
        byte[] header=szHeader.getBytes();
		boolean headerVerified=true;
		for(int i=0;i<header.length;i++) {
			if(header[i]!=content[i]) {
				headerVerified=false;
				System.out.println("BT header check failed");
				break;
			}
		}

        String saveFilePath = saveDir + File.separator + saveName;

        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);

        outputStream.write(content, 0, content.length);
            outputStream.close();
       return content.length;
    }
}