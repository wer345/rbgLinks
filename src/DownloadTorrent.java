import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Utils.HTTPDownloader;

public class DownloadTorrent {
	static String saveDir = "C:\\Temp";
	static String dataFolder="data";
	static String fnFound="found.txt";
	static String fnNew="new.txt";
	static String fnLoaded="loaded.txt";
	static String fnMagnet="magnet.txt";
	static String path=saveDir+"\\"+dataFolder+"\\";
	static MapData<BtInfo> mapFound;
	static MapData<BtInfo> mapNew;
	static MapData<BtInfo> mapLoaded;
	static MapData<MagnetInfo> mapMagnet;
	
	static HTTPDownloader downloader= new	HTTPDownloader();
    
	static {
		mapFound=new MapData<BtInfo>(path+fnFound,BtInfo.class);
		mapNew=new MapData<BtInfo>(path+fnNew,BtInfo.class);
		mapLoaded=new MapData<BtInfo>(path+fnLoaded,BtInfo.class);
		mapMagnet=new MapData<MagnetInfo>(path+fnMagnet,MagnetInfo.class);
	}
	
	static int indexOf(byte[] data, byte[] lookfor,int from) {
		int p=from;
		while(p<data.length) {
			boolean match=true;
			for(int i=0;i<lookfor.length && p+i<data.length;i++) {
				if(data[p+i]!=lookfor[i]) {
					match=false;
					break;
				}
			}
			if(match)
				return p;
			p++;
		}
		return -1;
	}

	static boolean headerMatches(byte[] data, byte[] header) {
		boolean match=true;
		for(int i=0;i<header.length;i++) {
			if(header[i]!=data[i]) {
				match=false;
				break;
			}
		}
		return match;
	}
	
	static void download(BtInfo bt) {
		String id=bt.id;
		String name=bt.name;
		String addedTime=bt.addedTime;
		
		try {
    		if(mapFound.contains(id) || mapLoaded.contains(id)) {
    			System.out.println("Old one: "+id+":"+name);
    			return;
    		}
    		mapNew.put(bt);
			
    		String url="http://rarbg.to/download.php?id="+id+"&f="+
    				name+"-[rarbg.to].torrent";
			
			byte[] content= downloader.get(url);
	    	if(content==null)
	    		return;

	        String torrentHeader="d8:announce"; // The header of Torrent file
	        String htmlHeader="<!DOCTYPE html";
			if(headerMatches(content,torrentHeader.getBytes())) {
            	String time="["+addedTime.trim().replace(':','.')+"] ";
            	String saveName=time +name+" - "+id+".torrent";
				
				System.out.println("Save Torrent file to "+saveName);
		        String saveFilePath = saveDir + File.separator + saveName;
		        // opens an output stream to save into file
		        FileOutputStream outputStream = new FileOutputStream(saveFilePath);
		        outputStream.write(content, 0, content.length);
		        outputStream.close();
		        mapLoaded.put(bt);
			}
			else if(headerMatches(content,htmlHeader.getBytes())) {
				System.out.println("Get an html file, finding magnet");
				
				String magnetHeader="<a href=\"magnet:?xt=";
				int pos=indexOf(content,magnetHeader.getBytes(),0);
				if(pos>0) {
					int posEnd=indexOf(content,"\">".getBytes(),pos);
					int magLen=posEnd-pos-9;
					byte[] magBytes= new byte[magLen];
					System.arraycopy(content,pos+9,magBytes,0,magLen);
					String mag=new String(magBytes);
					int p1=mag.indexOf("xt=urn:btih:");
					int p2=mag.indexOf("&dn=",p1);
					int p3=mag.indexOf("&tr=",p2);
					if(p1>0 && p2>0 && p3>0) {
						MagnetInfo mi= new MagnetInfo();
						mi.id=id;
						mi.addedTime=addedTime;
						mi.hashInfo=mag.substring(p1+12, p2);
						mi.name=mag.substring(p2+4,p3);
						System.out.println("found magnet "+id+", "+mi.name);
						mapMagnet.put(mi);
					}
				}
			}
			else {
				System.out.println("unknow response");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void flush() {
		mapNew.saveToFile(path+fnNew);
		mapLoaded.saveToFile(path+fnLoaded);
		mapMagnet.saveToFile(path+fnMagnet);
	}
}
