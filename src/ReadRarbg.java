
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        baseUrl="http://rarbg.to";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        String lastUrl="";
        while(true) {
        	String url=driver.getCurrentUrl();
			System.out.println("URL:"+url);
        	if(!url.equals(lastUrl)) {
                ListPage list= new ListPage(driver);

                List<BtInfo> bts=list.getTable();
                if(bts!=null) {
                	for(BtInfo bt:bts) {
                		System.out.println(""+bt.name+"; "+bt.addedTime+"; "+bt.url+"; "+bt.size+" MB;"+bt.seeder+"/"+bt.leech);
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
        //close Fire fox
        //driver.close();

    }

}