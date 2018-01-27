package googlebook;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HTTPDownload;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class ReadBook {

    public static void main(String[] args) {


//    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
		WebPage page= new WebPage();
		page.maximize();


        String baseUrl = "https://books.google.com/books?id=tI9AuKexGLgC&pg=PA42&lpg=PA42&dq=prt+patents&source=bl&ots=mZIp9KnY5G&sig=R16fi_c3AWOcna_7an3DLmUvPq4&hl=en&sa=X&ved=0ahUKEwi3-Of4svTYAhVCzWMKHVimBswQ6AEIQzAI#v=onepage&q=prt%20patents&f=false";

        for(int pageidx=301;pageidx<=400;pageidx++) {
	        baseUrl = "https://books.google.com/books?id=tI9AuKexGLgC&pg=PA"+pageidx;

	        if(!page.get(baseUrl))
	        	continue;
	        WebElement full= page.xpath_element("/html/body/div[4]/div[1]/div/div[6]/img");
	        if(full==null)
	        	continue;
	        full.click();
	        page.sleep(100);
	        WebElement zoom= page.xpath_element("/html/body/div[4]/div[1]/div/div[1]/img");
	        for(int r=0;r<3;r++) {
		        page.sleep(100);
	        	zoom.click();
	        }
//	        WebElement pane= page.xpath_element("/html/body/div[8]/div[2]/div/div[3]/div/div[1]/div");
//	        if(pane!=null)
//	        	pane.click();
//	        JavascriptExecutor jse = (JavascriptExecutor)page.driver;
//	        jse.executeScript("window.scrollBy(0,-20)", "");

	        Robot robot;
			try {
				robot = new Robot();
		        for(int r=0;r<15;r++) {
			        robot.keyPress(KeyEvent.VK_UP);
			        robot.keyRelease(KeyEvent.VK_UP);
		        }
			} catch (AWTException e) {

				e.printStackTrace();
			}
	        page.sleep(100);
	        String fn=String.format("C:/selenium/page_%04d.png", pageidx);
	        boolean done=page.saveScreenShot(fn);
	        if(done) {
	        	System.out.println("Saved page #"+pageidx);
	        }
        }
        page.close();

    }

}