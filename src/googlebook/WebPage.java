package googlebook;

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

/*  Note: when a task stopped, geckodriver.exe process is still running, so after multiple running,
 *  there are many geckodriver.exe processes running on Windows. To kill all the processes, type
 *  this command:
 *       taskkill /F /IM geckodriver.exe
 */
public class WebPage {
	public WebDriver driver;

	static {
    	System.setProperty("webdriver.gecko.driver","C:\\software\\Selenium\\geckodriver.exe");
	}

	public WebPage() {
		init("FireFox");
	}

	public WebPage(String browserName) {
		init(browserName);
	}

	void init(String browserName) {
		if(browserName.equalsIgnoreCase("FireFox"))
			driver = new FirefoxDriver();
	}
	public WebPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebPage(WebPage page) {
		driver = page.driver;
	}

	public void maximize() {
		driver.manage().window().maximize();
	}
	
	public boolean saveScreenShot(String filename) {
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
        	// now copy the  screenshot to desired location using copyFile //method
        	FileUtils.copyFile(src, new File(filename));
        	return true;
        }
        catch (IOException e)
         {
        	System.out.println(e.getMessage());
         }
        return false;
	}
	
	public boolean get(String url) {
		try {
			driver.get(url);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	WebElement xpath_element(String xpath) {
		try {
			WebElement ele = driver.findElement(By.xpath(xpath));
			return ele;
		}
		catch (Exception e) {
			return null;
		}
	}

	WebElement xpath_element(WebElement ele_base,String xpath) {
		try {
			WebElement ele=ele_base.findElement(By.xpath(xpath));
			return ele;
		}
		catch (Exception e) {
			return null;
		}
	}

	public WebElement ele_nameInList(WebElement ele_base,String path,String name) {
		for(int i=1;;i++) {
			try {
				WebElement ele=ele_base.findElement(By.xpath(path+"["+i+"]"));
				String text=ele.getText();
				System.out.println("#"+i+": "+text);
				if(text.contains(name))
					return ele;
			}
			catch (Exception e) {
				return null;
			}
		}
	}

	WebElement id_element(String id) {
		try {
			WebElement ele = driver.findElement(By.id(id));
			return ele;
		}
		catch (Exception e) {
			return null;
		}
	}

	boolean confirmed() {
		return false;
	}

	// confirm the page in a time
	boolean confirm(int millisecond) {
		int period=300;
    	int retry=millisecond;
        try {
    		while(retry>0) {
    			if(confirmed())
    				return true;
    			if(retry>period) {
    				Thread.sleep(period);
    				retry-=period;
    			}
    			else {
    				Thread.sleep(retry);
    				return confirmed();
    			}
    		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void sleep(int millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollToView(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	// show page sections and get the text of last non-empty section text
	public String showSections(int from) {
		String text="";
		WebElement ele_body=xpath_element("html/body");
		if(ele_body!=null) {
			for(int i=from;;i++) {
				WebElement ele=xpath_element(ele_body,"div["+i+"]");
				if(ele==null)
					break;
				String t=ele.getText();
				System.out.println("Section["+i+"]="+t.replace("\n", "|"));
				if(t.length()>0)
					text=t;
			}
		}
		return text;
	}


	public boolean matchesAllWords(String text, String[] words) {
		boolean match=true;
		for(int w=0;w<words.length;w++) {
			if(!text.contains(words[w])) {
				match=false;
				break;
			}
		}
		return match;
	}
	//find a section with words
	public WebElement findSection(String[] words,int from) {
		WebElement ele_body=xpath_element("html/body");
		if(ele_body!=null) {
			for(int i=from;;i++) {
				WebElement ele=xpath_element(ele_body,"div["+i+"]");
				if(ele==null)
					return null;
				String text=ele.getText();
				System.out.println("text of Section #"+i+": "+text);
				if(matchesAllWords(text,words) )
					return ele;
			}
		}
		return null;
	}


	// try multiple clicks on an xpath until this path disappear
	public int clickUntilClosed(String xpath,int nofTry) {
		int count=0;
		WebElement link=xpath_element(xpath);
		if(link==null)
			return -1;
		for(int t=0;t<nofTry;t++) {
			System.out.println("OK button xpath:"+xpath);
			if(link!=null) {
				count++;
				link.click();
				sleep(100);
				link=xpath_element(xpath);
				if(link==null)
					return count;
				int pos=xpath.lastIndexOf("/");
				xpath=xpath.substring(0,pos);
				link=xpath_element(xpath);
			}
		}
		return -1;
	}

	public String showSections() {
		return showSections(1);
	}
	public void close() {
		driver.close();
	}
}
