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

	List<BtInfo> getTable() {
		List<BtInfo> bts=null;
		try {
			WebElement ele = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr/td[2]/div/table/tbody/tr[2]/td/table[2]"));

			if(ele!=null) {
				//System.out.println("Table: "+ele.getText());
				List<WebElement> rows=ele.findElements(By.xpath("tbody/tr"));
				int irow=1;
				while(irow<rows.size()) {
					WebElement fileEle=rows.get(irow);
//					System.out.println("Row: "+irow+" "+fileEle.getText());
					List<WebElement> colEles = fileEle.findElements(By.xpath("td"));
					int icol=1;
					BtInfo bt= new BtInfo();
					while(icol<colEles.size()) {
						WebElement cell=colEles.get(icol);
//						System.out.println("  Col: "+icol+" "+cell.getText());
						if(icol==1) {
							WebElement link = cell.findElement(By.xpath("a"));
							bt.name=link.getText();
							bt.url=link.getAttribute("href");
							//System.out.println("Title "+text+" :"+url);
						}
						else if(icol==2) {
							bt.addedTime=cell.getText();
						}
						else if(icol==3) {
							String[] lsize=cell.getText().split(" ");
							if(lsize.length==2) {
								float v=Float.parseFloat(lsize[0]);
								if(lsize[1].equals("MB"))
									bt.size=v;
								else if(lsize[1].equals("GB"))
									bt.size=1000*v;
								if(lsize[1].equals("KB"))
									bt.size=0.001f*v;
							}
						}
						else if(icol==4) {
							WebElement font = cell.findElement(By.xpath("font"));
							String seeder=font.getText();
							bt.seeder=Integer.parseInt(seeder);
						}
						else if(icol==5) {
							bt.leech=Integer.parseInt(cell.getText());
						}

						icol++;
					}
					if(bts==null)
						bts=new ArrayList<BtInfo>();
					bts.add(bt);
					irow++;
				}

			}
		}
		catch(Exception e) {
			//System.out.println("Exception:"+e.toString());
			bts=null;
		}
//        List<WebElement> eles = driver.findElements(By.tagName("table"));
//        if(eles!=null) {
//			System.out.println("There are "+eles.size()+" tables");
//        	for(WebElement ele:eles) {
//	        }
//        }
		return bts;
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
