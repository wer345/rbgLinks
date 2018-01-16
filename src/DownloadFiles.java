
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class DownloadFiles {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\software\\Selenium\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");

    	FirefoxProfile fxProfile = new FirefoxProfile();

    	fxProfile.setPreference("browser.download.folderList",2);
    	fxProfile.setPreference("browser.download.manager.showWhenStarting",false);
    	fxProfile.setPreference("browser.download.panel.shown",false);
    	fxProfile.setPreference("browser.download.dir","c:\\mydownloads");
    	fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","Application/BitComet File");
    	
    	WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

        String[] lstUrl = {
        	"http://rarbg.to/download.php?id=qzdb4ls&f=VickyAtHome.17.05.05.My.Holes.XXX.1080p.MP4-KTR-[rarbg.to].torrent",
        	"http://rarbg.to/download.php?id=wc42l1q&f=VickyAtHome.17.04.28.Lexi.Luna.Show.Me.Your.Cock.XXX.1080p.MP4-KTR-[rarbg.to].torrent",
        	"http://rarbg.to/download.php?id=y7jt6qo&f=AdultAuditions.E110.Sex.Goddess.My.First.Hardcore.Sex.Swallow.Video.XXX.720p.MP4-KTR-[rarbg.to].torrent",
        	"http://rarbg.to/download.php?id=7gmqdnh&f=MariskaX.18.01.16.Mariska.And.Hete.Tina.Very.Intimate.Interview.With.Tina.XXX.1080p.MP4-KTR-[rarbg.to].torrent"
        };

        // launch Fire fox and direct it to the Base URL
        for(String url:lstUrl) {
        	System.out.printf("Download "+url);
        	driver.get(url);
        	System.out.printf("Download donw");
        	//driver.navigate().to(url);
        }


    }

}