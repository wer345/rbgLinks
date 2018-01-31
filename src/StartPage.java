
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HTTPDownload;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class StartPage {

	static String getCurrentUrl(WebDriver driver, int timeout) {
    	String url="";
    	try {
    		url=driver.getCurrentUrl();
    		return url;
    	}
    	catch (Exception e) {
    		try {
    			//System.out.println("Failed to get current URL");
				Thread.sleep(timeout);
			} catch (InterruptedException e1) {
			}
    	}
    	return null;
	}
	
	public static WebDriver start() {
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

        String baseUrl = "http://rarbg.to/torrents.php?category=1;4";
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        
        while(true) {
        	String url=getCurrentUrl(driver,200);
        	if(url!=null) {
				System.out.println("URL:"+url);
				if(!url.contains("rarbg.to")) {
					break;
				}
				if(UrlTools.isHomePage(url)) {
					return driver;
				}
				if(url.contains("threat_defence.php?defence=nojc")) {
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
					
				}
        	}
        }
        System.out.println("Done");
		return null;
	}
	
	
    public static void main(String[] args) {
    	WebDriver driver=StartPage.start();
    }

}