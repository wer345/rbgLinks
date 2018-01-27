package tools;

import java.io.File;

public class ScanBtFiles {

	public void scan(String szPath) {
		File path = new File(szPath);
	    File [] files = path.listFiles();
	    for (int i = 0; i < files.length; i++){
	        if (files[i].isFile()){ //this line weeds out other directories/folders
	            System.out.println("File:"+files[i]);
	        }
	        else {
	        	if(files[i].isDirectory()) {
	        		System.out.println("Dir:"+files[i]);
	        		scan(""+files[i]);
	        	}
	        	
	        }
	    }		
		
	}
	
	public static void main(String[] args) {
		ScanBtFiles s = new ScanBtFiles();
		s.scan("C:\\Temp\\BT");
	}
}
