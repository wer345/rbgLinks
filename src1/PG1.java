
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class PG1 {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
    	FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled", true);
        profile.setPreference("network.cookie.cookieBehavior",2);
        
    	WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String baseUrl = "http://demo.guru99.com/selenium/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        baseUrl="http://rarbg.to/torrent/gzhqyte";
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        //close Fire fox
        //driver.close();

    }

}