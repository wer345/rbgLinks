
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ReadRarbg {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
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
	        	String content = driver.findElement(By.tagName("body")).getText();
	        	System.out.println(content);
        		if(url.contains("page=")) {
//        			driver.get("http://rarbg.to/torrent/rog1utj");
        			driver.get("http://rarbg.to/download.php?id=rog1utj&f=Acts.of.Violence.2018.1080p.WEB-DL.DD5.1.H264-FGT-[rarbg.to].torrent");
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