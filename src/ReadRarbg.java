
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HTTPDownload;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ReadRarbg {

    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "http://demo.guru99.com/selenium/newtours/";
        baseUrl="http://rarbg.to/torrent/gzhqyte";
        baseUrl="http://rarbg.to/torrents.php?category=1;4";
//http://rarbg.to/torrents.php?category=1%3B4&page=2
        
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        String lastUrl="";

        String nextUrl=null;
        while(true) {
        	String url="";
        	try {
        		url=driver.getCurrentUrl();
        	}
        	catch (Exception e) {
        		try {
        			System.out.println("Failed to get current URL");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
        		continue;
        	}
			System.out.println("URL:"+url);
			if(!url.contains("rarbg.to")) {
				DownloadTorrent.flush();
				break;
			}
			nextUrl=null;
        	if(!url.equals(lastUrl)) {
        		System.out.println("Detected new page: "+url);
        		int pos=url.indexOf("category=1%3B4");
        		boolean isRightPage=false;
        		if(pos>0) {
        			int posPage=url.indexOf("page=",pos);
        			int posEnd=url.indexOf("&",posPage);
        			if(posPage>0) {
        				isRightPage=true;
        				String szPage;
        				String tail="";
        				if(posEnd<posPage) 
        					szPage=url.substring(posPage+5);
        				else {
        					szPage=url.substring(posPage+5,posEnd);
        					tail=url.substring(posEnd);
        				}
        				int idxPage=Integer.parseInt(szPage);
        				if(idxPage>=2)
        					nextUrl=url.substring(0,posPage)+"page="+(idxPage+1)+tail;
        			}
        		}
        		if(isRightPage) {
	                ListPage list= new ListPage(driver);
	
	                List<BtInfo> bts=list.getTable();
	                if(bts!=null) {
	
	                	for(BtInfo bt:bts) {
	                		DownloadTorrent.download(bt);
	                	}
	            		DownloadTorrent.flush();                		
	                }
        		}
	        	lastUrl=url;
                if(nextUrl!=null)
                	driver.get(nextUrl);
        	}

        	try {
        		Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("Done");
    }

}