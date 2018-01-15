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

	void getTable() {
		// /html/body/table[3]
		WebElement ele = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table[2]"));

		if(ele!=null) {
			//System.out.println("Table: "+ele.getText());
			List<WebElement> rows=ele.findElements(By.xpath("tbody/tr"));
			int irow=1;
//			for(WebElement row:rows) {
//				System.out.println("Row: "+irow+" "+row.getText());
//				irow++;
//			}

			irow=1;
			while(irow<rows.size()) {
				WebElement fileEle=rows.get(irow);
				System.out.println("Row: "+irow+" "+fileEle.getText());
				List<WebElement> colEles = fileEle.findElements(By.xpath("td"));
				int icol=1;
				while(icol<colEles.size()) {
					WebElement cell=colEles.get(icol);
					System.out.println("  Col: "+icol+" "+cell.getText());
					if(icol==1) {
						WebElement link = cell.findElement(By.xpath("a")); 
						String text=link.getText();
						String url=link.getAttribute("href");
						System.out.println("Title "+text+" :"+url);
					}
					icol++;
				}
				irow++;
			}

		}
//        List<WebElement> eles = driver.findElements(By.tagName("table"));
//        if(eles!=null) {
//			System.out.println("There are "+eles.size()+" tables");
//        	for(WebElement ele:eles) {
//	        }
//        }

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
