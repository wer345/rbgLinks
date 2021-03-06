
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class DownloadPage_Test {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "file:///C:/codeEclipse460/Selenium/pages/Sample1.htm";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);


        DownloadPage list= new DownloadPage(driver);

        list.download();

//        String [] matches={"1080P"};
//        List<String> links=list.getLinks(matches);
//        System.out.println("Links:"+links);
        //close Fire fox
        //driver.close();

    }

}