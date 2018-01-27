
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HTTPDownload;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ReadTop100 {

    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.rarbg.to/top100.php?category[]=4";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        String lastUrl="";

        
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
        	if(!url.equals(lastUrl)) {
        		System.out.println("Detected new page: "+url);
        		int pos=url.indexOf("top100.php?category[]=4");
        		if(pos>0) {
	                ListPage list= new ListPage(driver);
	        		String xpath_top100="/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table";

	                List<BtInfo> bts=list.getTable(xpath_top100);
	                if(bts!=null) {
	                	for(BtInfo bt:bts) {
	                		DownloadTorrent.download(bt);
	                	}
	            		DownloadTorrent.flush();
	            		break;
	                }
        		}
	        	lastUrl=url;
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