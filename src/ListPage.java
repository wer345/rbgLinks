import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListPage {
	WebDriver driver;

	ListPage(WebDriver driver) {
		this.driver=driver;
	}

	List<String> getLinks(String[] matches) {
        List<WebElement> eles = driver.findElements(By.tagName("a"));
        List<String> links= new ArrayList<String>();
        if(eles!=null) {
        	for(WebElement ele:eles) {
        		String url = ele.getAttribute("href");
        		String title = ele.getAttribute("title");
        		if(url==null)
        			continue;
        		if(title!=null) {
        			for(String match:matches) {
        				if(title.toLowerCase().contains(match.toLowerCase())) {
        				//System.out.println("title="+title);
        				//System.out.println("link="+url);
        				links.add(url);
	        			}
	        		}
	        	}
	        }
        }
        return links;
	}
}
