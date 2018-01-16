//package net.codejava.networking;

import java.io.IOException;

public class HttpDownloader {

    public static void main(String[] args) {
        String fileURL = "https://www.google.com/logos/doodles/2018/katy-jurados-94th-birthday-5562889569042432-l.png";
        String saveDir = "C:/Temp";
        try {
            HTTPDownload.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}