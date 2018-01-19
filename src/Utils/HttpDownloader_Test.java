package Utils;
//package net.codejava.networking;

import java.io.IOException;

public class HttpDownloader_Test {

    public static void main(String[] args) {
        String fileURL = "https://www.google.com/logos/doodles/2018/katy-jurados-94th-birthday-5562889569042432-l.png";
        String saveDir = "C:/Temp";
        fileURL="http://rarbg.to/download.php?id=qzdb4ls&f=VickyAtHome.17.05.05.My.Holes.XXX.1080p.MP4-KTR-[rarbg.to].torrent";
        try {
            HTTPDownload.downloadFile(fileURL, saveDir,"t.torrent");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}