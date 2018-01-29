
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			boolean checkEnd=true;
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
        		boolean foundList=false;
        		if(isRightPage) {
	                ListPage list= new ListPage(driver);
	        		String xpath_list="/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table[2]";

	                List<BtInfo> bts=list.getTable(xpath_list);
	                if(bts!=null) {
	                	int cnt=0;
	                	int cntFailed=0;
	                	int cntOlds=0;
	                	for(BtInfo bt:bts) {
	                		cnt++;
	                		int code=DownloadTorrent.download(bt);
	                		if(code==0)
	                			cntFailed++;
	                		if(code==3)
	                			cntOlds++;

	                		foundList=true;
	                	}
	                	if(cnt!=cntFailed)
	                		DownloadTorrent.flush();
	                	if(cnt==cntOlds)
	                	{
	                		System.out.println("All bts in this page already got");
	                	}
	                }
        		}

        		if(!foundList){
        			System.out.println("No Bt file found");
        			checkEnd=true;
        		}

	        	lastUrl=url;
                if(nextUrl!=null)
                	driver.get(nextUrl);
        	}
    		if(checkEnd)
    		{
    			// /
    			try {
	    			WebElement ele= driver.findElement(By.xpath("html/body/div/div/a"));
	    			String txt=ele.getText();
	    			System.out.println("text:"+txt);
	    			if(txt.contains("Click here")) {
	    				ele.click();
	    				continue;
	    			}
    			}
    			catch (Exception e) {
    			}

    			try {
	    			WebElement ele= driver.findElement(By.xpath("/html/body"));
	    			String txt=ele.getText();
//	    			System.out.println("text:"+txt);
	    			if(txt.contains("Im sorry but we cannot process your request right now."))
	    				return;
    			}
    			catch (Exception e) {
    			}
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