package Utils;
//package net.codejava.networking;

import java.io.FileOutputStream;
import java.io.IOException;

public class HttpDownloader_Test {

    public static void main(String[] args) {
        String fileURL = "https://www.google.com/logos/doodles/2018/katy-jurados-94th-birthday-5562889569042432-l.png";
        String saveDir = "C:/Temp";
        // why these urls get response code 400?
        fileURL="http://rarbg.to/download.php?id=7bls2wq&f=MomsInControl - Aaliyah Hadid, Courtney Taylor - Showing Her The Ropes mp4-[rarbg.to].torrent";
        fileURL="http://rarbg.to/download.php?id=nrm95lg&f=AssParade - Abella Danger - Takes 2 Dicks in Her Ass mp4-[rarbg.to].torrent";
        try {
        	String saveFilePath="t.torrent";
        	HTTPDownloader downloader= new	HTTPDownloader();
        	byte[] content=downloader.get(fileURL);
	        FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	        outputStream.write(content, 0, content.length);
	        outputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}