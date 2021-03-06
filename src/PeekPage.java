
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class PeekPage {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "http://demo.guru99.com/selenium/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        String lastUrl="";
        String lastPage="";
        while(true) {
        	String url=driver.getCurrentUrl();
        	if(!url.equals(lastUrl))
        	try {
        		String page=driver.getPageSource();
        		if(page!=lastPage) {
        			System.out.println("URL:"+url);
        			//System.out.println(page);
        			String content = driver.findElement(By.tagName("body")).getText();
        			System.out.println(content);
        			lastPage=page;
        		}
        		lastUrl=url;
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //close Fire fox
        //driver.close();

    }

}