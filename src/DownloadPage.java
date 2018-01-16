import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadPage {
	WebDriver driver;

	DownloadPage(WebDriver driver) {
		this.driver=driver;
	}

	void download() {
		List<BtInfo> bts=null;
		try {
			WebElement ele = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr/td[2]/div/div/table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[2]/a[1]"));

			if(ele!=null) {
				String url=ele.getAttribute("href");
				if(url!=null) {
					driver.get(url);
				}
			}
		}
		catch(Exception e) {
			//System.out.println("Exception:"+e.toString());
			bts=null;
		}
	}

}
