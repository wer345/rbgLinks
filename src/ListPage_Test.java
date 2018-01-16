
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ListPage_Test {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "file:///C:/codeEclipse460/Selenium/pages/Page.htm";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);


        ListPage list= new ListPage(driver);

        List<BtInfo> bts=list.getTable();
        if(bts!=null) {
        	for(BtInfo bt:bts) {
        		System.out.println("Get page: "+bt.url);
        		driver.get(bt.url);
        		for(int i=0;i<50;i++){
	        		try {
		        		if(bt.url.equals(driver.getCurrentUrl())) {
			        		Thread.sleep(1000);
			                DownloadPage dl= new DownloadPage(driver);
			                System.out.println("Downloading");
			                dl.download();
			        		break;
		        		}
							Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}

        }
    }

}