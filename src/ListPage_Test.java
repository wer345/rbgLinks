
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
		String xpath_list="/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table[2]";

        List<BtInfo> bts=list.getTable(xpath_list);
        if(bts!=null) {
        	for(BtInfo bt:bts) {
        		String url=ListPage.TorPath+bt.id;
        		System.out.println("Get page: "+url);
        		driver.get(url);
        		for(int i=0;i<50;i++){
	        		try {
		        		if(url.equals(driver.getCurrentUrl())) {
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